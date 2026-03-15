package com.example.nobsv2.product.services;

import com.example.nobsv2.Command;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.model.ProductMapper;
import com.example.nobsv2.product.model.UpdateProductCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductService implements Command<UpdateProductCommand, ProductDTO> {

    private final ProductMapper productMapper;
    private ProductRepository productRepository;

    public UpdateProductService(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand command) {
        Optional<Product> productOptional = productRepository.findById(command.getId());
        if(productOptional.isPresent()) {
            Product product = command.getProduct();
            product.setId(command.getId());
            //validate it before saving
            // ProductValidator.execute(product);
            productRepository.save(product);
            return ResponseEntity.ok(productMapper.productToDto(product));
        }
        throw new ProductNotFoundException();

    }
}
