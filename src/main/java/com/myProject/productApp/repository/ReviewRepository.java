package com.sella.productApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sella.productApp.entity.ProductRating;

@Repository
public interface ReviewRepository extends JpaRepository<ProductRating, Integer>, JpaSpecificationExecutor<ProductRating> {

	

}
