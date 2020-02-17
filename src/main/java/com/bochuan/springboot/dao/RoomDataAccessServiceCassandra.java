package com.bochuan.springboot.dao;

import java.util.StringJoiner;
import com.bochuan.springboot.modal.Room;
import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.collect.ImmutableSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.data.cassandra.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Data Access layer
@Repository("RoomCassandraDao")
// @Repository：使用@Repository注解可以确保DAO或者repositories提供异常转译，
// 这个注解修饰的DAO或者repositories类会被ComponentScan发现并配置，同时也不需要为它们提供XML配置项。
public class RoomDataAccessServiceCassandra implements RoomDao {

    @Autowired
    private CassandraOperations cassandraTemplate;

    @Override
    public UUID insertRoom(UUID uuid, Room room) {
        cassandraTemplate.insert(room);
        return uuid;
    }

    @Override
    public Room selectRoomById(UUID uuid) {
        Select select = QueryBuilder.select().from("rooms").allowFiltering();
        select.where(QueryBuilder.eq("uuid", uuid));
//        return cassandraTemplate.selectOneById(uuid, Room.class);
        return cassandraTemplate.selectOne(select, Room.class);
    }

    @Override
    public List<Room> selectAllRoom() {
        return cassandraTemplate.select("SELECT * FROM rooms;", Room.class);
    }

    @Override
    public void deleteRoomByIdCassandra(UUID uuid) {

//        Select select = QueryBuilder.select().from("Spring_Cassandra_DB", "rooms");
//        Select.Where where = select.where(QueryBuilder.eq("uuid", uuid));
//        cassandraTemplate.delete(Room.class);
        String cql = "DELETE FROM rooms WHERE uuid = " + uuid;
        cassandraTemplate.getCqlOperations().execute(cql);
    }

    @Override
    public Room deleteRoomByIdMongo(UUID uuid) {
        return null;
    }

    @Override
    public Room updateRoomByIdMongo(UUID uuid, Room room) {
        return null;
    }

    @Override
    public boolean updateRoomByIdCassandra(UUID uuid, Room room) {
        Query query =Query.query(Criteria.where("uuid").is(uuid));
        Update update = Update.empty().set("name", room.getName())
                                      .set("type", room.getType())
                                      .set("slug", room.getSlug())
                                      .set("description", room.getDescription())
                                      .set("extras", room.getExtras())
                                      .set("images", room.getImages())
                                      .set("price", room.getPrice())
                                      .set("size", room.getSize())
                                      .set("capacity", room.getCapacity())
                                      .set("pets", room.isPets())
                                      .set("breakfast", room.isBreakfast())
                                      .set("featured", room.isFeatured());
        System.out.println(room.getPrice());
        System.out.println(room.getSize());
        return cassandraTemplate.update(query, update, Room.class);
    }

    @Override
    public List<Room> filterRoom(String roomType, int roomCapacity, double roomPrice, double minSize, double maxSize, boolean isPets, boolean isBreakfast) {
        if(!roomType.equals("all")) {
            if(!isPets && !isBreakfast) {
                Query query = Query.query(Criteria.where("type").is(roomType))
                        .and(Criteria.where("capacity").gte(roomCapacity))
                                .and(Criteria.where("price").lte(roomPrice))
                                        .and(Criteria.where("size").gte(minSize))
                                            .and(Criteria.where("size").lte(maxSize)).withAllowFiltering();

                return cassandraTemplate.select(query, Room.class);
            } else if(isPets && !isBreakfast) {
                Query query = Query.query(Criteria.where("type").is(roomType))
                        .and(Criteria.where("capacity").gte(roomCapacity))
                                .and(Criteria.where("price").lte(roomPrice))
                                        .and(Criteria.where("size").gte(minSize))
                                            .and(Criteria.where("size").lte(maxSize))
                                                .and(Criteria.where("pets").is(true)).withAllowFiltering();

                return cassandraTemplate.select(query, Room.class);
            } else if(!isPets && isBreakfast) {
                Query query = Query.query(Criteria.where("type").is(roomType))
                        .and(Criteria.where("capacity").gte(roomCapacity))
                                .and(Criteria.where("price").lte(roomPrice))
                                        .and(Criteria.where("size").gte(minSize))
                                            .and(Criteria.where("size").lte(maxSize))
                                                .and(Criteria.where("breakfast").is(true)).withAllowFiltering();

                return cassandraTemplate.select(query, Room.class);
            } else {
                Query query = Query.query(Criteria.where("type").is(roomType))
                        .and(Criteria.where("capacity").gte(roomCapacity))
                                .and(Criteria.where("price").lte(roomPrice))
                                        .and(Criteria.where("size").gte(minSize))
                                            .and(Criteria.where("size").lte(maxSize))
                                                .and(Criteria.where("pets").is(true))
                                                        .and(Criteria.where("breakfast").is(true)).withAllowFiltering();

                return cassandraTemplate.select(query, Room.class);
            }
        } else {
            if(!isPets && !isBreakfast) {
                Query query = Query.query(Criteria.where("capacity").gte(roomCapacity))
                        .and(Criteria.where("price").lte(roomPrice))
                                .and(Criteria.where("size").gte(minSize))
                                    .and(Criteria.where("size").lte(maxSize)).withAllowFiltering().withAllowFiltering();

                return cassandraTemplate.select(query, Room.class);
            } else if(isPets && !isBreakfast) {
                Query query = Query.query(Criteria.where("capacity").gte(roomCapacity))
                        .and(Criteria.where("price").lte(roomPrice))
                                .and(Criteria.where("size").gte(minSize))
                                    .and(Criteria.where("size").lte(maxSize))
                                        .and(Criteria.where("pets").is(true)).withAllowFiltering();

                return cassandraTemplate.select(query, Room.class);
            } else if(!isPets && isBreakfast) {
                Query query = Query.query(Criteria.where("capacity").gte(roomCapacity))
                        .and(Criteria.where("price").lte(roomPrice))
                                .and(Criteria.where("size").gte(minSize))
                                    .and(Criteria.where("size").lte(maxSize))
                                        .and(Criteria.where("breakfast").is(true)).withAllowFiltering();

                return cassandraTemplate.select(query, Room.class);
            } else {
                Query query = Query.query(Criteria.where("capacity").gte(roomCapacity))
                        .and(Criteria.where("price").lte(roomPrice))
                                .and(Criteria.where("size").gte(minSize))
                                    .and(Criteria.where("size").lte(maxSize))
                                        .and(Criteria.where("pets").is(true))
                                            .and(Criteria.where("breakfast").is(true)).withAllowFiltering();

                return cassandraTemplate.select(query, Room.class);
            }
        }
    }

    @Override
    public List<Room> selectFeaturedRooms() {
        Select select = QueryBuilder.select().from("rooms").allowFiltering();
        select.where(QueryBuilder.eq("featured", true));
        return cassandraTemplate.select(select, Room.class);
    }

    @Override
    public void sendMessage(String msg) {

    }
}
