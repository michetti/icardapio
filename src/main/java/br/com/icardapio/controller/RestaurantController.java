package br.com.icardapio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.icardapio.entity.Category;
import br.com.icardapio.entity.Product;
import br.com.icardapio.entity.Restaurant;
import br.com.icardapio.multitenancy.CurrentTenantResolver;
import br.com.icardapio.service.RestaurantFacade;

@Controller
@Transactional(propagation=Propagation.REQUIRED)
public class RestaurantController {

	@Autowired
	private RestaurantFacade facade;
	
	@Autowired
	private CurrentTenantResolver<Long> tenantResolver;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@ModelAttribute("tenant") Restaurant tenant, Model model) {
		// add information about current tenant
		Restaurant restaurant = facade.getRestaurant();
		model.addAttribute("restaurant", restaurant);
		
		if (tenantResolver.isMasterTenant()) {
			model.addAttribute("tenant", tenant);
			
			return "landing";
			
		} else {
			List<Category> categories = facade.getAllCategories();
			model.addAttribute("categories", categories);
			
			return "home";
		}
	}

	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product, Model model, final RedirectAttributes redirectAttributes) {
		product = facade.addProduct(product);
		model.addAttribute("product", product);
		
		redirectAttributes.addFlashAttribute("message", "Produto adicionado com sucesso");
		return "redirect:/";
	}
	
	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = "/removeProduct", method = RequestMethod.GET)
	public String removeProduct(@ModelAttribute("categoryId") Long categoryId, @ModelAttribute("productId") Long productId, final RedirectAttributes redirectAttributes) {
		facade.removeProduct(categoryId, productId);
		
		redirectAttributes.addFlashAttribute("message", "Produto removido com sucesso");
		return "redirect:/";		
	}
	
	@RequestMapping(value = "/addRestaurant", method = RequestMethod.POST)
	public String addRestaurant(@ModelAttribute("tenant") Restaurant restaurant, Model model, final RedirectAttributes redirectAttributes) {
		restaurant = facade.addRestaurant(restaurant);
		return "redirect:" + getRestaurantFullUrl(restaurant);
	}
	
	@RequestMapping(value = "/createMasterData", method = RequestMethod.GET)
	public String createMasterData(final RedirectAttributes redirectAttributes) {
		facade.createMasterData();
		
		redirectAttributes.addFlashAttribute("message", "Dados de teste criados com sucesso");
		return "redirect:/";		
	}
	
	 @ModelAttribute("tenant")
	 public Restaurant getRestaurantObject() {
		 return new Restaurant();
	 }	

	 @ModelAttribute("product")
	 public Product getProductObject() {
		 return new Product();
	 }
	 
	 protected String getRestaurantFullUrl(Restaurant restaurant) {
		 return "http://" + restaurant.getSubdomain() + ".lvh.me:8080/icardapio";
	 }

}