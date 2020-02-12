package com.bochuan.springboot.service;

import com.bochuan.springboot.dao.RoomDao;
import com.bochuan.springboot.modal.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

// Service layer / Business logic layer
@Service
// @Service：一般用于修饰service层的组件
public class RoomService {

    private RoomDao roomDao;

    @Autowired
    // @Autowired是根据类型（byType）进行自动装配的
    // 在默认情况下只使用 @Autowired 注解进行自动注入时，Spring 容器中匹配的候选 Bean 数目必须有且仅有一个。
    // @Qualifier：当有多个同一类型的Bean时，可以用@Qualifier(“name”)来指定。与@Autowired配合使用
    // Spring 允许我们通过 @Qualifier 注释指定注入 Bean 的名称，这样就消除歧义了。
    // 所以 @Autowired 和 @Qualifier 结合使用时，自动注入的策略就从 byType 转变成 byName 了。
    public RoomService(@Qualifier("RoomDao") RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public void sendMessage(String msg) {
        roomDao.sendMessage(msg);
    }

    public UUID addRoom(Room room) {
        UUID uuid = UUID.randomUUID();
        return roomDao.insertRoom(uuid, room);
    }

    public Room getRoomById(UUID uuid) {
        return roomDao.selectRoomById(uuid);
    }

    public List<Room> filterRoom(String roomType, int roomCapacity, double roomPrice,
                                 double minSize, double maxSize, boolean isPets, boolean isBreakfast) {
        return roomDao.filterRoom(roomType, roomCapacity, roomPrice, minSize, maxSize,
                isPets, isBreakfast);
    }

    public List<Room> getFeaturedRooms() {

        return roomDao.selectFeaturedRooms();
    }

    public List<Room> getAllRooms() {
        return roomDao.selectAllRoom();
    }

    public Room deleteRoom(UUID uuid) {
        return roomDao.deleteRoomById(uuid);
    }

    public Room updateRoom(UUID uuid, Room room) {
        return roomDao.updateRoomById(uuid, room);
    }
}
