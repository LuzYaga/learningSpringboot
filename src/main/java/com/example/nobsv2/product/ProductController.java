package com.example.nobsv2.product;

import com.example.nobsv2.product.services.*;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.model.UpdateProductCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ("/api")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
public class ProductController {

    //Injeção de dependências para injetar a classe serviço
    private final CreateProductService createProductService;
    private final DeleteProductService deleteProductService;
    private final UpdateProductService updateProductService;
    private final GetProductsService getProductsService;
    private final GetProductService getProductService;
    private final SearchProductService searchProductService;

    public ProductController(CreateProductService createProductService, DeleteProductService deleteProductService, UpdateProductService updateProductService, GetProductsService getProductsService, GetProductService getProductService, SearchProductService searchProductService) {
        this.createProductService = createProductService;
        this.deleteProductService = deleteProductService;
        this.updateProductService = updateProductService;
        this.getProductsService = getProductsService;
        this.getProductService = getProductService;
        this.searchProductService = searchProductService;
    }


    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product){
        return createProductService.execute(product);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return getProductsService.execute(null);

    }

    @GetMapping("/product/search")
    public ResponseEntity<List<ProductDTO>> searchProductByName(@RequestParam String name) {
        return searchProductService.execute(name);
    }

    //new get mapping for find by Id
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        return getProductService.execute(id);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody Product product){
        return updateProductService.execute(new UpdateProductCommand(id, product));
    }

    @DeleteMapping("/product/{id}") // id here must match id in the next line
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        return deleteProductService.execute(id);
    }
}

