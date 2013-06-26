package br.com.icardapio.multitenancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.icardapio.entity.Restaurant;
import br.com.icardapio.repositories.RestaurantsRepository;

public class ICardapioTenantResolver implements CurrentTenantResolver<Long> {
	
	private static String MASTER_TENANT_SUBDOMAIN = "www";
	
	@Autowired
	private RestaurantsRepository restaurantsRepository;
	
	protected Long getCurrentTenantFromSubdomain() {
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		String subdomain = attr.getRequest().getServerName().split("\\.")[0];
		
		Restaurant restaurant = restaurantsRepository.getBySubdomain(subdomain);
		return restaurant != null ? restaurant.getTenantId() : 0;
	}
	
	@Override
	public Long getMasterTenantId() {
		return restaurantsRepository.getBySubdomain(MASTER_TENANT_SUBDOMAIN).getTenantId();
	}

	@Override
	public boolean isMasterTenant() {
		Long masterTenant = getMasterTenantId();
		Long tenantFromSubdomain = getCurrentTenantFromSubdomain();
		
		return masterTenant.equals(tenantFromSubdomain);
	}
	
	@Override
	public Long getCurrentTenantId() {
		return getCurrentTenantFromSubdomain();
	}	

}
