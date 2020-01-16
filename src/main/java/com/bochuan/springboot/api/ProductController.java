package com.bochuan.springboot.api;

import com.bochuan.springboot.modal.Product;
import com.bochuan.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

// API layer / Controller layer
@RequestMapping("api/v1/product")
@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public UUID addProduct(@Valid @NotNull @RequestBody Product product) {
        return productService.addProduct(product);

    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(path = "{uuid}")
    public Product getProductById(@PathVariable("uuid") UUID uuid) {
        return productService.getProductById(uuid);
    }

    @DeleteMapping(path = "{uuid}")
    public Product deleteProduct(@PathVariable("uuid") UUID uuid) {
        return productService.deleteProduct(uuid);
    }

    @PutMapping(path = "{uuid}")
    public Product updateProduct(@PathVariable("uuid") UUID uuid, @Valid @NotNull @RequestBody Product product) {
        return productService.updateProduct(uuid, product);
    }
}
