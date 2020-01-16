package com.bochuan.springboot.service;


import com.bochuan.springboot.dao.ProductDao;
import com.bochuan.springboot.modal.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

// Service layer / Business logic layer
@Service
public class ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductService(@Qualifier("ProductDao") ProductDao productDao) {
        this.productDao = productDao;
    }

    public UUID addProduct(Product product) {
        UUID uuid = UUID.randomUUID();
        productDao.insertProduct(uuid, product);
        return uuid;
    }

    public List<Product> getAllProducts() {
        return productDao.selectAllProduct();
    }

    public Product getProductById(UUID uuid) {
        return productDao.selectProductById(uuid);
    }

    public Product deleteProduct(UUID uuid) {
        return productDao.deleteProductById(uuid);
    }

    public Product updateProduct(UUID uuid, Product product) {
        return productDao.updateProductById(uuid, product);
    }
}
