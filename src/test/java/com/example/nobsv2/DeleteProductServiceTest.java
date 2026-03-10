package com.example.nobsv2;

import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.services.DeleteProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeleteProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DeleteProductService deleteProductService;

    @BeforeEach
    public void Setup(){
        //Inicializa o repositório e o serviço
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_delete_product_service_return_http_status(){
        //Given
        Product product = new Product();
        product.setId(1);
        product.setName("Product name");
        product.setPrice(1999.99);
        product.setDescription("Description that fits the blablablablabal 20 chars");
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        //When
        ResponseEntity<Void> response = deleteProductService.execute(1);
        //Then
        assertEquals(ResponseEntity.status(HttpStatus.NO_CONTENT).build(), response);

    }

    @Test
    public void given_product_does_not_exist_when_delete_product_service_throws_product_not_found_exception(){
        //Given
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        //When and then
        assertThrows(ProductNotFoundException.class, () -> deleteProductService.execute(1));
        verify(productRepository, times(1)).findById(1);
    }
}
