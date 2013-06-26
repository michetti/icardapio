package br.com.icardapio.multitenancy;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.LockModeRepositoryPostProcessor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryMetadata;

public class MultiTenantJpaRepositoryFactory extends JpaRepositoryFactory {

	private final CurrentTenantResolver<Long> currentTenantResolver;

	public MultiTenantJpaRepositoryFactory(EntityManager entityManager, CurrentTenantResolver<Long> currentTenantResolver) {
		super(entityManager);
		this.currentTenantResolver = currentTenantResolver;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected JpaRepository<?, ?> getTargetRepository(RepositoryMetadata metadata, EntityManager entityManager) {
		final JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType());
		final SimpleJpaRepository<?, ?> repo = new MultiTenantSimpleJpaRepository(entityInformation, entityManager, currentTenantResolver);
		
		repo.setLockMetadataProvider(LockModeRepositoryPostProcessor.INSTANCE.getLockMetadataProvider());
		
		return repo;
	}

	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		return MultiTenantSimpleJpaRepository.class;
	}

}
