package com.bochuan.springboot.dao;

import com.bochuan.springboot.modal.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import sun.tools.jconsole.JConsole;

import java.util.List;
import java.util.UUID;

// Data Access layer
@Repository("RoomDao")
// @Repository：使用@Repository注解可以确保DAO或者repositories提供异常转译，
// 这个注解修饰的DAO或者repositories类会被ComponentScan发现并配置，同时也不需要为它们提供XML配置项。
public class RoomDataAccessService implements RoomDao {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public UUID insertRoom(UUID uuid, Room room) {
        mongoTemplate.insert(new Room(uuid, room.getName(), room.getSlug(), room.getType(), room.getDescription(),
                room.getExtras(), room.getImages(), room.getPrice(), room.getSize(), room.getCapacity(),
                room.isBreakfast(), room.isPets(), room.isFeatured()), "rooms");
        return uuid;
    }

    @Override
    public Room selectRoomById(UUID uuid) {
        Query query = new Query(Criteria.where("uuid").is(uuid));
        return mongoTemplate.findOne(query, Room.class, "rooms");
    }

    @Override
    public List<Room> filterRoom(String roomType, int roomCapacity, double roomPrice,
                                 double minSize, double maxSize, boolean isPets, boolean isBreakfast) {
        if(!roomType.equals("all")) {
            if(!isPets && !isBreakfast) {
                Query query = new Query(Criteria.where("type").is(roomType)
                        .andOperator(Criteria.where("capacity").gte(roomCapacity)
                                .andOperator(Criteria.where("price").lte(roomPrice)
                                        .andOperator(Criteria.where("size").gte(minSize).lte(maxSize))
                                )
                        )
                );
                return mongoTemplate.find(query, Room.class, "rooms");
            } else if(isPets && !isBreakfast) {
                Query query = new Query(Criteria.where("type").is(roomType)
                        .andOperator(Criteria.where("capacity").gte(roomCapacity)
                                .andOperator(Criteria.where("price").lte(roomPrice)
                                        .andOperator(Criteria.where("size").gte(minSize).lte(maxSize)
                                                .andOperator(Criteria.where("pets").is(true))
                                        )
                                )
                        )
                );
                return mongoTemplate.find(query, Room.class, "rooms");
            } else if(!isPets && isBreakfast) {
                Query query = new Query(Criteria.where("type").is(roomType)
                        .andOperator(Criteria.where("capacity").gte(roomCapacity)
                                .andOperator(Criteria.where("price").lte(roomPrice)
                                        .andOperator(Criteria.where("size").gte(minSize).lte(maxSize)
                                                .andOperator(Criteria.where("breakfast").is(true))
                                        )
                                )
                        )
                );
                return mongoTemplate.find(query, Room.class, "rooms");
            } else {
                Query query = new Query(Criteria.where("type").is(roomType)
                        .andOperator(Criteria.where("capacity").gte(roomCapacity)
                                .andOperator(Criteria.where("price").lte(roomPrice)
                                        .andOperator(Criteria.where("size").gte(minSize).lte(maxSize)
                                                .andOperator(Criteria.where("pets").is(true)
                                                        .andOperator(Criteria.where("breakfast").is(true))
                                                )
                                        )
                                )
                        )
                );
                return mongoTemplate.find(query, Room.class, "rooms");
            }
        } else {
            if(!isPets && !isBreakfast) {
                Query query = new Query(Criteria.where("capacity").gte(roomCapacity)
                                        .andOperator(Criteria.where("price").lte(roomPrice)
                                            .andOperator(Criteria.where("size").gte(minSize).lte(maxSize))
                                        )
                );
                return mongoTemplate.find(query, Room.class, "rooms");
            } else if(isPets && !isBreakfast) {
                Query query = new Query(Criteria.where("capacity").gte(roomCapacity)
                                        .andOperator(Criteria.where("price").lte(roomPrice)
                                                .andOperator(Criteria.where("size").gte(minSize).lte(maxSize)
                                                        .andOperator(Criteria.where("pets").is(true))
                                                )
                                        )
                );
                return mongoTemplate.find(query, Room.class, "rooms");
            } else if(!isPets && isBreakfast) {
                Query query = new Query(Criteria.where("capacity").gte(roomCapacity)
                                        .andOperator(Criteria.where("price").lte(roomPrice)
                                                .andOperator(Criteria.where("size").gte(minSize).lte(maxSize)
                                                        .andOperator(Criteria.where("breakfast").is(true))
                                                )
                                        )
                );
                return mongoTemplate.find(query, Room.class, "rooms");
            } else {
                Query query = new Query(Criteria.where("capacity").gte(roomCapacity)
                                        .andOperator(Criteria.where("price").lte(roomPrice)
                                                .andOperator(Criteria.where("size").gte(minSize).lte(maxSize)
                                                        .andOperator(Criteria.where("pets").is(true)
                                                                .andOperator(Criteria.where("breakfast").is(true))
                                                        )
                                                )
                                        )
                );
                return mongoTemplate.find(query, Room.class, "rooms");
            }
        }
    }

    @Override
    public List<Room> selectFeaturedRooms() {
        Query query = new Query(Criteria.where("featured").is(true));
        return mongoTemplate.find(query, Room.class, "rooms");
    }

    @Override
    public List<Room> selectAllRoom() {
        return mongoTemplate.findAll(Room.class, "rooms");
    }

    @Override
    public Room deleteRoomById(UUID uuid) {
        Query query = new Query(Criteria.where("uuid").is(uuid));
        return mongoTemplate.findAndRemove(query, Room.class, "rooms");
    }

    @Override
    public Room updateRoomById(UUID uuid, Room room) {
        Query query = new Query(Criteria.where("uuid").is(uuid));
        Update update = new Update();
        update.set("name", room.getName())
                .set("slug", room.getSlug())
                .set("type", room.getType())
                .set("description", room.getDescription())
                .set("extras", room.getExtras())
                .set("images", room.getImages())
                .set("price", room.getPrice())
                .set("size", room.getSize())
                .set("capacity", room.getCapacity())
                .set("breakfast", room.isBreakfast())
                .set("pets", room.isPets())
                .set("featured", room.isFeatured());
        return mongoTemplate.findAndModify(query, update, Room.class, "rooms");
    }
}
