package br.com.icardapio.multitenancy;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.icardapio.repositories.RestaurantsRepository;

public class ICardapioTenantResolver implements CurrentTenantResolver<Long> {
	
	private static String MASTER_TENANT_SUBDOMAIN = "www";
	
	@Autowired
	private RestaurantsRepository restaurantsRepository;
	
	public Long getCurrentTenantFromSubdomain() {
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		String subdomain = attr.getRequest().getServerName().split("\\.")[0];
		return restaurantsRepository.getBySubdomain(subdomain).getTenantId();
	}
	
	public Long getCurrentTenantFromSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		return (Long)session.getAttribute("tenantId");
	}

	@Override
	public Long getCurrentTenantId() {
		Long tenantFromSubdomain = getCurrentTenantFromSubdomain();
		
//		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
//			Long tenantFromSession = getCurrentTenantFromSession();
//			
//			if (tenantFromSession.equals(tenantFromSubdomain)) {
//				throw new TenantException("Usuário não pertence a esse Tenant");
//			}
//		}
		
		return tenantFromSubdomain;
	}

	@Override
	public Long getMasterTenantId() {
		return restaurantsRepository.getBySubdomain(MASTER_TENANT_SUBDOMAIN).getTenantId();
	}

}
