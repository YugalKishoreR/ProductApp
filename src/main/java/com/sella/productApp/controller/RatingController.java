package com.sella.productApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sella.productApp.dto.ProductRatingDTO;
import com.sella.productApp.exception.ProductException;
import com.sella.productApp.exception.Response;
import com.sella.productApp.logger.ProductLogger;
import com.sella.productApp.service.ReviewService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	ReviewService reviewService;

	private ProductLogger log = new ProductLogger(RatingController.class);

	@GetMapping
	public Response<List<ProductRatingDTO>> getReviews() {
		try {
			log.debug("ReviewController >> getReviews");
			List<ProductRatingDTO> proRatingDTO = reviewService.getReviews();
			if (proRatingDTO.isEmpty()) {
				return new Response<>("No Reviews found");
			}
			return new Response<>("Success", "Fetched Reviews", proRatingDTO);
		} catch (Exception e) {
			return new Response<>("Error", e.getMessage(), null);

		}
	}

	@PostMapping
	public Response<ProductRatingDTO> addReview(@RequestBody ProductRatingDTO productRatingDTO) {
		ProductRatingDTO savedProductRatingDTO = new ProductRatingDTO();
		log.debug("ProductRatingDTO", productRatingDTO);
		try {

			if (productRatingDTO.getRating() <= 0) {
				throw new ProductException("Rating is required");
			}
			savedProductRatingDTO = reviewService.addReview(productRatingDTO);
			return new Response<>("Success", "Review Added", savedProductRatingDTO);
		} catch (ProductException e) {
			return new Response<>("Error", "Failed to add review: ", null);
		}
	}

	@PutMapping("/{id}")
	public Response<ProductRatingDTO> updateRating(@PathVariable int id, @RequestBody ProductRatingDTO productRatingDTO) {
		try {

			productRatingDTO.setId(id);
			ProductRatingDTO updatedProRatingDTO = reviewService.updateProRating(productRatingDTO);
			return new Response<>("success", "review updated successfully", updatedProRatingDTO);
		} catch (Exception e) {
			return new Response<>("error", "Failed to add review: ", null);
		}
	}

	@DeleteMapping("/{id}")
	public Response deleteRating(@PathVariable int id) {
		try {
			reviewService.deleteProRatingById(id);
			return new Response<>("Success", "Review Deleted", null);
		} catch (Exception e) {
			return new Response<>("Error", "Failed to delete the review: ", null);
		}
	}

}
