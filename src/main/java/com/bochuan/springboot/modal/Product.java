package com.bochuan.springboot.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

// DB Modal
@Document(collection = "products")
public class Product {

    private UUID uuid;
    private String title;
    private String img;
    private double price;
    private String company;
    private String info;
    private Boolean inCart;
    private int count;
    private int total;

    public Product(@JsonProperty("uuid") UUID uuid,
                   @JsonProperty("title") String title,
                   @JsonProperty("img") String img,
                   @JsonProperty("price") double price,
                   @JsonProperty("company") String company,
                   @JsonProperty("info") String info,
                   @JsonProperty("inCart") Boolean inCart,
                   @JsonProperty("count") int count,
                   @JsonProperty("total") int total) {
        this.uuid = uuid;
        this.title = title;
        this.img = img;
        this.price = price;
        this.company = company;
        this.info = info;
        this.inCart = inCart;
        this.count = count;
        this.total = total;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getImg() {
        return img;
    }

    public double getPrice() {
        return price;
    }

    public String getCompany() {
        return company;
    }

    public String getInfo() {
        return info;
    }

    public Boolean getInCart() {
        return inCart;
    }

    public int getCount() {
        return count;
    }

    public int getTotal() {
        return total;
    }


}
