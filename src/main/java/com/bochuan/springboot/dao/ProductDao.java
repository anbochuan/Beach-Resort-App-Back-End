package com.bochuan.springboot.dao;

import com.bochuan.springboot.modal.Product;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductDao {

    UUID insertProduct(UUID uuid, Product product);

//    default int insertProduct(Product product) {
//        UUID uuid = UUID.randomUUID();
//        return insertProduct(uuid, product);
//    }

    Product selectProductById(UUID id);

    List<Product> selectAllProduct();

    Product deleteProductById(UUID id);

    Product updateProductById(UUID id, Product product);
}
