package com.bochuan.springboot.dao;
import com.bochuan.springboot.modal.Room;

import java.util.List;
import java.util.UUID;

public interface RoomDao {
    UUID insertRoom(UUID uuid, Room room);

    Room selectRoomById(UUID uuid);

    List<Room> selectAllRoom();

    Room deleteRoomById(UUID uuid);

    Room updateRoomById(UUID uuid, Room room);

    List<Room> filterRoom(String roomType, int roomCapacity, double roomPrice,
                          double minSize, double maxSize, boolean isPets, boolean isBreakfast);

    List<Room> selectFeaturedRooms();

    void sendMessage(String msg);

}
