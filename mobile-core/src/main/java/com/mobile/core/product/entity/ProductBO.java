package com.mobile.core.product.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.mobile.ApplicationProperties;

@Service
public interface ProductBO extends JpaRepository<Product, Long> {

	@Cacheable(cacheName = ApplicationProperties.CACHE_FIVE_MINUTE)
	public List<Product> findAll();

	@Cacheable(cacheName = ApplicationProperties.CACHE_FIVE_MINUTE)
	@Query("SELECT p FROM Product p WHERE p.id IN :ids")
    List<Product> findByIdList(@Param("ids") List<Long> ids);
}
