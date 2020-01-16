package com.bochuan.springboot.dao;

import com.bochuan.springboot.modal.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Data Access layer
@Repository("ProductDao")
public class ProductDataAccessService implements ProductDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public UUID insertProduct(UUID uuid, Product product) {
        mongoTemplate.insert(new Product(uuid, product.getTitle(), product.getImg(),
                product.getPrice(), product.getCompany(), product.getInfo(), product.getInCart(),
                product.getCount(), product.getTotal()), "products");
        return uuid;
    }

    @Override
    public Product selectProductById(UUID uuid) {
        Query query = new Query(Criteria.where("uuid").is(uuid));
        return mongoTemplate.findOne(query, Product.class);
    }

    @Override
    public List<Product> selectAllProduct() {
        return mongoTemplate.findAll(Product.class);
    }

    @Override
    public Product deleteProductById(UUID uuid) {
        Query query = new Query(Criteria.where("uuid").is(uuid));
        return mongoTemplate.findAndRemove(query, Product.class);
    }

    @Override
    public Product updateProductById(UUID uuid, Product product) {
        Query query = new Query(Criteria.where("uuid").is(uuid));
        Update update = new Update().set("title", product.getTitle())
                .set("img", product.getImg())
                .set("price", product.getPrice())
                .set("company", product.getCompany())
                .set("info", product.getInfo())
                .set("inCart", product.getInCart())
                .set("count", product.getCount())
                .set("total", product.getTotal());
        return mongoTemplate.findAndModify(query, update, Product.class);
    }
}
