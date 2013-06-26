package br.com.icardapio.multitenancy;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.LockMetadataProvider;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

public class MultiTenantSimpleJpaRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> {
    private final CurrentTenantResolver<Long> tenantResolver;
    private final EntityManager em;
 
    public MultiTenantSimpleJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager em, CurrentTenantResolver<Long> tenantResolver) {
        super(entityInformation, em);
        this.tenantResolver = tenantResolver;
        this.em = em;
    }
 
    public MultiTenantSimpleJpaRepository(Class<T> domainClass, EntityManager em, CurrentTenantResolver<Long> tenantResolver) {
        super(domainClass, em);
        
        this.tenantResolver = tenantResolver;
        this.em = em;
    }
 
    protected void setCurrentTenant() {
        em.setProperty(PersistenceUnitProperties.MULTITENANT_PROPERTY_DEFAULT, tenantResolver.getCurrentTenantId().intValue());
    }
 
    @Override
    public <S extends T> S save(S entity) {
        setCurrentTenant();
        return super.save(entity);
    }

	@Override
	public long count() {
		setCurrentTenant();
		return super.count();
	}

	@Override
	public long count(Specification<T> spec) {
		setCurrentTenant();
		return super.count(spec);
	}

	@Override
	@Transactional
	public void delete(ID id) {
		setCurrentTenant();
		super.delete(id);
	}

	@Override
	@Transactional
	public void delete(Iterable<? extends T> arg0) {
		setCurrentTenant();
		super.delete(arg0);
	}

	@Override
	@Transactional
	public void delete(T entity) {
		setCurrentTenant();
		super.delete(entity);
	}

	@Override
	@Transactional
	public void deleteAll() {
		setCurrentTenant();
		super.deleteAll();
	}

	@Override
	@Transactional
	public void deleteAllInBatch() {
		setCurrentTenant();
		super.deleteAllInBatch();
	}

	@Override
	@Transactional
	public void deleteInBatch(Iterable<T> entities) {
		setCurrentTenant();
		super.deleteInBatch(entities);
	}

	@Override
	public boolean exists(ID arg0) {
		setCurrentTenant();
		return super.exists(arg0);
	}

	@Override
	public List<T> findAll() {
		setCurrentTenant();
		return super.findAll();
	}

	@Override
	public List<T> findAll(Iterable<ID> ids) {
		setCurrentTenant();
		return super.findAll(ids);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		setCurrentTenant();
		return super.findAll(pageable);
	}

	@Override
	public List<T> findAll(Sort sort) {
		setCurrentTenant();
		return super.findAll(sort);
	}

	@Override
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		setCurrentTenant();
		return super.findAll(spec, pageable);
	}

	@Override
	public List<T> findAll(Specification<T> spec, Sort sort) {
		setCurrentTenant();
		return super.findAll(spec, sort);
	}

	@Override
	public List<T> findAll(Specification<T> spec) {
		setCurrentTenant();
		return super.findAll(spec);
	}

	@Override
	public T findOne(ID id) {
		setCurrentTenant();
		return super.findOne(id);
	}

	@Override
	public T findOne(Specification<T> arg0) {
		setCurrentTenant();
		return super.findOne(arg0);
	}

	@Override
	@Transactional
	public void flush() {
		setCurrentTenant();
		super.flush();
	}

	@Override
	@Transactional
	public <S extends T> List<S> save(Iterable<S> arg0) {
		setCurrentTenant();
		return super.save(arg0);
	}

	@Override
	@Transactional
	public T saveAndFlush(T entity) {
		setCurrentTenant();
		return super.saveAndFlush(entity);
	}

	@Override
	public void setLockMetadataProvider(LockMetadataProvider lockMetadataProvider) {
		super.setLockMetadataProvider(lockMetadataProvider);
	}
    
}
