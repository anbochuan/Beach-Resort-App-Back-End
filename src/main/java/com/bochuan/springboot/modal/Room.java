package com.bochuan.springboot.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

// DB Modal
// Mongodb Annotation
@Document(collection = "rooms")
// Cassandra Annotation
@Table("rooms")
public class Room {
    // Cassandra Annotation
//    private int id;
    @PrimaryKey
    private UUID uuid;
    @Column(value = "name")
    private String name;
    @Column(value = "slug")
    private String slug;
    @Column(value = "type")
    private String type;
    @Column(value = "description")
    private String description;
//    @Column(value = "extras")
//    private String[] extras;
//    @Column(value = "images")
//    private String[] images;
    @Column(value = "extras")
    private List<String> extras;
    @Column(value = "images")
    private List<String> images;
    @Column(value = "price")
    private double price;
    @Column(value = "size")
    private double size;
    @Column(value = "capacity")
    private int capacity;
    @Column(value = "breakfast")
    private boolean breakfast;
    @Column(value = "pets")
    private boolean pets;
    @Column(value = "featured")
    private boolean featured;

    public Room(
            @JsonProperty("uuid") UUID uuid,
            @JsonProperty("name") String name,
            @JsonProperty("slug") String slug,
            @JsonProperty("type") String type,
            @JsonProperty("description") String description,
//            @JsonProperty("extras") String extras[],
//            @JsonProperty("images") String images[],
            @JsonProperty("extras") List<String> extras,
            @JsonProperty("images") List<String> images,
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

//    public int getId() {
//        return id;
//    }

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

//    public String[] getExtras() {
//        return extras;
//    }
    public List<String> getExtras() {
    return extras;
}

//    public String[] getImages() {
//        return images;
//    }

    public List<String> getImages() {
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

//    public void setId(int id) {
//        this.id = id;
//    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public void setExtras(String[] extras) {
//        this.extras = extras;
//    }
//
//    public void setImages(String[] images) {
//        this.images = images;
//    }

    public void setExtras(List<String> extras) {
        this.extras = extras;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public void setPets(boolean pets) {
        this.pets = pets;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }
}
