package com.example.nobsv2.product.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO productToDto(Product product);

    Product dtoToProduct(ProductDTO productDTO);

    // Para listas
    List<ProductDTO> toDtoList(List<Product> products);
}
