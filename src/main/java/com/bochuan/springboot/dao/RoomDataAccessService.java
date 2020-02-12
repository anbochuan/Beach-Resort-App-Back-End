package com.bochuan.springboot.dao;

import com.bochuan.springboot.modal.Room;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    RedisTemplate<String, String> redisTemplate;


    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public void sendMessage(String msg) {
        rabbitTemplate.convertAndSend("mailer", msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "mailer")
    public void receiveMessage(String msg) {
        System.out.println("收到消息: " + msg);
    }

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
        List<Room> featuredRooms = null;
        ObjectMapper objectMapper = new ObjectMapper();
        // 从redis缓存中获取数据
        String featuredRoomListJson = redisTemplate.boundValueOps("featured_rooms").get();
        // 判断redis缓存中是否存在所要获取的数据
        if(featuredRoomListJson == null) {
            // redis缓存中没有，从数据库中获取
            Query query = new Query(Criteria.where("featured").is(true));
            featuredRooms = mongoTemplate.find(query, Room.class, "rooms");
            // 将List集合转换成JSON格式的字符串
            try {
                featuredRoomListJson = objectMapper.writeValueAsString(featuredRooms);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            // 先将数据库查询结果存入redis缓存
            redisTemplate.boundValueOps("featured_rooms").set(featuredRoomListJson);
            System.out.println("从数据库中获取");
        } else {
            System.out.println("从redis缓存中获取");
            System.out.println(featuredRoomListJson);

            try {
                featuredRooms = objectMapper.readValue(featuredRoomListJson, new TypeReference<List<Room>>(){});
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return featuredRooms;
    }

    @Override
    public List<Room> selectAllRoom() {
        List<Room> allRooms = null;
        ObjectMapper objectMapper = new ObjectMapper();
        // 从redis缓存中获取数据
        String roomListJson = redisTemplate.boundValueOps("all_rooms").get();
        // 判断redis缓存中是否存在所要获取的数据
        if(roomListJson == null) {
            // redis缓存中没有，从数据库中获取
            allRooms = mongoTemplate.findAll(Room.class, "rooms");
            // 将List集合转换成JSON格式的字符串
            try {
                roomListJson = objectMapper.writeValueAsString(allRooms);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            // 先将数据库查询结果存入redis缓存
            redisTemplate.boundValueOps("all_rooms").set(roomListJson);
            System.out.println("从数据库中获取");
        } else {
            System.out.println("从redis缓存中获取");
            System.out.println(roomListJson);
            try {
                allRooms = objectMapper.readValue(roomListJson, new TypeReference<List<Room>>() {});
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return allRooms;

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
