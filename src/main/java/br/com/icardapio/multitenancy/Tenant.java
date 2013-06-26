package br.com.icardapio.multitenancy;

public interface Tenant<T> {
	
	T getTenantId();

}
