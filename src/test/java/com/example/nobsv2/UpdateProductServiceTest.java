package com.example.nobsv2;

import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.model.UpdateProductCommand;
import com.example.nobsv2.product.services.UpdateProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UpdateProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private UpdateProductService updateProductService;

    @BeforeEach
    public void Setup(){
        //Inicializa o repositório e o serviço
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_update_product_service_return_product_dto(){
       //Given
        Product product = new Product();
        product.setId(1);
        product.setName("Product name");
        product.setPrice(1999.99);
        product.setDescription("Description that fits the blablablablabal 20 chars");
        when(productRepository.findById(1)).thenReturn(Optional.of(product)); // it says when but it's part of given, it's setting up

        //When
        ResponseEntity<ProductDTO> response = updateProductService.execute(new UpdateProductCommand(1, product));

        //Then
        assertEquals(ResponseEntity.ok(new ProductDTO(product)), response);
        //asserts productRepository was only called once
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    public void given_product_does_not_exist_when_update_product_service_throws_product_not_found_exception() {
        //Given
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        //When and then
        assertThrows(ProductNotFoundException.class, () -> updateProductService.execute(new UpdateProductCommand(1, new Product())));
        //asserts productRepository was only called once
        verify(productRepository, times(1)).findById(1);
    }

}
