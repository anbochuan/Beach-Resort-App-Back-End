package com.bochuan.springboot.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

// DB Modal
@Document(collection = "rooms")
public class Room {
    private UUID uuid;
    private String name;
    private String slug;
    private String type;
    private String description;
    private String extras[];
    private String images[];
    private double price;
    private double size;
    private int capacity;
    private boolean breakfast;
    private boolean pets;
    private boolean featured;

    public Room(
            @JsonProperty("uuid") UUID uuid,
            @JsonProperty("name") String name,
            @JsonProperty("slug") String slug,
            @JsonProperty("type") String type,
            @JsonProperty("description") String description,
            @JsonProperty("extras") String extras[],
            @JsonProperty("images") String images[],
            @JsonProperty("price") double price,
            @JsonProperty("size") double size,
            @JsonProperty("capacity") int capacity,
            @JsonProperty("breakfast") boolean breakfast,
            @JsonProperty("pets") boolean pets,
            @JsonProperty("featured") boolean featured) {
        this.uuid = uuid;
        this.name = name;
        this.slug = slug;
        this.type = type;
        this.description = description;
        this.extras = extras;
        this.images = images;
        this.price = price;
        this.size = size;
        this.capacity = capacity;
        this.breakfast = breakfast;
        this.pets = pets;
        this.featured = featured;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String[] getExtras() {
        return extras;
    }

    public String[] getImages() {
        return images;
    }

    public double getPrice() {
        return price;
    }

    public double getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public boolean isPets() {
        return pets;
    }

    public boolean isFeatured() {
        return featured;
    }
}
