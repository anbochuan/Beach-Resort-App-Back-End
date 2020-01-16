package com.bochuan.springboot.modal;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// DB Modal
// letting MongoDB knows that this object should be used as a MongoDB document and stored in a collection and optionally
// we can pass in the collection name
@Document(collection = "people")
public class Person {

    private final UUID uuid;
    @NotBlank
    private final String name;

    public Person(@JsonProperty("id") UUID uuid,
                  @JsonProperty("name") String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getId() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}
