package br.com.icardapio.controller;

import java.util.List;
import java.util.Locale;

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
import br.com.icardapio.service.RestaurantFacade;

@Controller
@Transactional(propagation=Propagation.REQUIRED)
public class RestaurantController {

	@Autowired
	private RestaurantFacade facade;
	
	 @ModelAttribute("product")
	 public Product getProductObject() {
		 return new Product();
	 }	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Restaurant restaurant = facade.getRestaurant();
		List<Category> categories = facade.getAllCategories();
		
		model.addAttribute("restaurant", restaurant);
		model.addAttribute("categories", categories);
		
		return "home";
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
	
	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = "/createTestData", method = RequestMethod.GET)
	public String createTestData(final RedirectAttributes redirectAttributes) {
		facade.createTestData();
		
		redirectAttributes.addFlashAttribute("message", "Dados de teste criados com sucesso");
		return "redirect:/";		
	}

}