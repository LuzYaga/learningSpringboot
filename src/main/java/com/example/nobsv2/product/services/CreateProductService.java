package com.example.nobsv2.product.services;

import com.example.nobsv2.Command;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.model.ProductMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService implements Command<Product, ProductDTO> {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public CreateProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Product product) {
        //validate it before saving
        // ProductValidator.execute(product);

        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.productToDto(savedProduct));
    }


}
