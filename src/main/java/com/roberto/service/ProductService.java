package com.roberto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roberto.dto.ProductDTO;
import com.roberto.model.Product;
import com.roberto.repository.ProductRepository;

/**
 * Created by roberto on 02/08/17.
 */
@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public ProductDTO save(ProductDTO productDTO) {
		Product product = repository.save( productDTO.toEntity() );
		return ProductDTO.of( product );
	}

	public ProductDTO update(Long productId, ProductDTO productDTO) {
		Product product = productDTO.toEntity();
		product.setId( productId );
		Product updatedProduct = repository.save( product );
		return ProductDTO.of( updatedProduct );
	}

	public void delete(Long productId) {
		repository.deleteById( productId );
	}

	public ProductDTO findOne(Long productId) {
		return repository.findById( productId )
				.map( ProductDTO::of )
				.orElse( null );
	}

	public List<ProductDTO> findByName(String name) {
		List<Product> products = repository.findByName( name );
		return products.stream()
				.map( ProductDTO::of )
				.collect( Collectors.toList() );
	}
}