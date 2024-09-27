package com.sella.productApp.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sella.productApp.dto.ProductRatingDTO;
import com.sella.productApp.entity.Product;
import com.sella.productApp.entity.ProductRating;
import com.sella.productApp.exception.ProductException;
import com.sella.productApp.logger.ProductLogger;
import com.sella.productApp.repository.ProductRepository;
import com.sella.productApp.repository.ReviewRepository;
import com.sella.productApp.service.ReviewService;

@Repository
public class ReviewDao {

	@Autowired
	ReviewRepository reviewRepo;
	
	@Autowired
	ProductRepository productRepo;

	private ProductLogger log = new ProductLogger(ReviewService.class);

	public List<ProductRatingDTO> getAllReviews() {
		log.debug("ProRatingDTO-getAllReviews");
		List<ProductRating> review = reviewRepo.findAll();
		List<ProductRatingDTO> productDTOs = review.stream().map(this::mapToProRatingDTO).collect(Collectors.toList());
		return productDTOs;
	}
	
	public ProductRatingDTO addReview(ProductRatingDTO proRatingDTO) throws ProductException {
	    // Convert DTO to entity
	    ProductRating proRating = mapToProRating(proRatingDTO);
	    // Save the entity
	    ProductRating savedProRating = reviewRepo.save(proRating);
	    // Convert saved entity back to DTO and return
	    return mapToProRatingDTO(savedProRating);
	}

	public ProductRatingDTO updateProRating(ProductRatingDTO proRatingDTO) {
		ProductRating proRating = mapToProRating(proRatingDTO);
		ProductRating updatedProRating = reviewRepo.save(proRating);
		ProductRatingDTO updatedProRatingDTO = mapToProRatingDTO(updatedProRating);
		return updatedProRatingDTO;
	}

	public void deleteById(int proRatingId) {
		reviewRepo.deleteById(proRatingId);
	}

	private ProductRatingDTO mapToProRatingDTO(ProductRating review) {
		ProductRatingDTO proRatingDTO = new ProductRatingDTO();
		proRatingDTO.setId(review.getId());
		proRatingDTO.setPid(review.getProduct().getPid());
		proRatingDTO.setRating(review.getRating());
		return proRatingDTO;
	}
	
	private ProductRating mapToProRating(ProductRatingDTO proRatingDTO) {
		log.debug("proRatingDTO----", proRatingDTO);
		ProductRating proRating = new ProductRating();
		proRating.setId(proRatingDTO.getId());
		Product product = new Product();
		product.setPid(proRatingDTO.getPid());
		proRating.setProduct(product);
		proRating.setRating(proRatingDTO.getRating());
		return proRating;
	}
}
