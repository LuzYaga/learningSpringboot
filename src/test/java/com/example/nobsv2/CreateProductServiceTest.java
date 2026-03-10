package com.example.nobsv2;

import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.CreateProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class CreateProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    CreateProductService createProductService;

    @BeforeEach
    public void Setup(){
        //Inicializa o repositório e o serviço
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_create_product_service_return_http_status(){
        //Given
        Product product = new Product();

        product.setName("Product name");
        product.setPrice(1999.99);
        product.setDescription("Description that fits the blablablablabal 20 chars");

        Product savedProduct = new Product();
        savedProduct.setId(1);
        savedProduct.setName(product.getName());
        savedProduct.setPrice(product.getPrice());
        savedProduct.setDescription(product.getDescription());

        // Mockando o comportamento do save
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        //When
        ResponseEntity<ProductDTO> response = createProductService.execute(product);
        //Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getId());
        //asserts productRepository was only called once
        verify(productRepository, times(1)).save(any(Product.class));

    }

}
