package com.bochuan.springboot.dao;
import com.bochuan.springboot.modal.Room;

import java.util.List;
import java.util.UUID;

public interface RoomDao {
    UUID insertRoom(UUID uuid, Room room);

    Room selectRoomById(UUID uuid);

    List<Room> selectAllRoom();

    //** when integrate with mongoDB **//
    Room deleteRoomByIdMongo(UUID uuid);

    //** when integrate with cassandra **//
    void deleteRoomByIdCassandra(UUID uuid);

    //** when integrate with mongoDB **//
    Room updateRoomByIdMongo(UUID uuid, Room room);

    //** when integrate with cassandra **//
    boolean updateRoomByIdCassandra(UUID uuid, Room room);

    List<Room> filterRoom(String roomType, int roomCapacity, double roomPrice,
                          double minSize, double maxSize, boolean isPets, boolean isBreakfast);

    List<Room> selectFeaturedRooms();

    void sendMessage(String msg);

}
