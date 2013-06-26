package br.com.icardapio.multitenancy;

import java.io.Serializable;

public interface CurrentTenantResolver<T extends Serializable> {
	T getMasterTenantId();
	
	T getCurrentTenantId();
}
