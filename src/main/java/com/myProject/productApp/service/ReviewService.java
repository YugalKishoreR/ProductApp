package com.sella.productApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sella.productApp.dao.ReviewDao;
import com.sella.productApp.dto.ProductRatingDTO;
import com.sella.productApp.logger.ProductLogger;

@Service
public class ReviewService {
	
	@Autowired
	ReviewDao reviewDao;
	
	private ProductLogger log = new ProductLogger(ReviewService.class);
	
	public List<ProductRatingDTO> getReviews() {
		log.debug("ReviewService-getAllProducts");
		return reviewDao.getAllReviews();
	}
	
	public ProductRatingDTO addReview(ProductRatingDTO proRatingDTO) {
		log.debug("ReviewService-addReview");
		return reviewDao.addReview(proRatingDTO);
	}
	
	public ProductRatingDTO updateProRating(ProductRatingDTO proRatingDTO) {
        return reviewDao.updateProRating(proRatingDTO);
    }
	
	public void deleteProRatingById(int proRatingId) {
		reviewDao.deleteById(proRatingId);
    }

}
