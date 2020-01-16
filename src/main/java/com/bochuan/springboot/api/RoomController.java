package com.bochuan.springboot.api;

import com.bochuan.springboot.modal.Room;
import com.bochuan.springboot.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

// API layer / Controller layer
@RequestMapping("api/v1/room")
// @RequestMapping：提供路由信息，负责URL到Controller中的具体函数的映射。
@RestController
@CrossOrigin // 相当于在浏览器中开启CROS插件
// @RestController：用于标注控制层组件(如struts中的action)，@ResponseBody和@Controller的合集。
// @Controller：用于定义控制器类，在spring 项目中由控制器负责将用户发来的URL请求转发到对应的服务接口（service层），
// 一般这个注解在类中，通常方法需要配合注解@RequestMapping。
public class RoomController {

    private RoomService roomService;

    @Autowired
    // @Autowired：自动导入依赖的bean, @Autowired是根据类型（byType）进行自动装配的
    // @Autowired 注释可以对类成员变量、方法及构造函数进行标注，完成自动装配。
    // 通过 @Autowired的使用来消除 set、get方法。
    // 在默认情况下只使用 @Autowired 注解进行自动注入时，Spring 容器中匹配的候选 Bean 数目必须有且仅有一个。
    // Spring 允许我们通过 @Qualifier 注释指定注入 Bean 的名称，这样就消除歧义了。
    // 所以 @Autowired 和 @Qualifier 结合使用时，自动注入的策略就从 byType 转变成 byName 了。
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public UUID addRoom(@Valid @NotNull @RequestBody Room room) {
        return roomService.addRoom(room);
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping(path = "{uuid}")
    public Room getRoomById(@PathVariable("uuid") UUID uuid) {
        return roomService.getRoomById(uuid);
    }

    @RequestMapping("/filterRoom")
    @GetMapping()
    public List<Room> filterRoom(@RequestParam(value = "type") String roomType,
                                 @RequestParam(value = "capacity") int roomCapacity,
                                 @RequestParam(value = "price") double roomPrice,
                                 @RequestParam(value = "minSize") double minSize,
                                 @RequestParam(value = "maxSize") double maxSize,
                                 @RequestParam(value = "isPets") boolean isPets,
                                 @RequestParam(value = "isBreakfast") boolean isBreakfast) {
        return roomService.filterRoom(roomType, roomCapacity, roomPrice, minSize, maxSize, isPets, isBreakfast);
    }

    @RequestMapping("/featuredRoom")
    @GetMapping()
//    @ResponseBody
//  @ResponseBody：表示该方法的返回结果直接写入HTTP response body中，一般在异步获取数据时使用，用于构建RESTful的api。
//  在使用@RequestMapping后，返回值通常解析为跳转路径，加上@responsebody后返回结果不会被解析为跳转路径，
//  而是直接写入HTTP response body中。比如异步获取json数据，加上@responsebody后，会直接返回json数据。
//  该注解一般会配合@RequestMapping一起使用

    public List<Room> getFeaturedRooms() {
        return roomService.getFeaturedRooms();
    }

    @DeleteMapping(path = "{uuid}")
    public Room deleteRoomById(@PathVariable("uuid") UUID uuid) {
        return roomService.deleteRoom(uuid);
    }

    @PutMapping(path = "{uuid}")
    public Room updateRoomById(@PathVariable("uuid") UUID uuid, @Valid @NotNull @RequestBody Room room) {
        return roomService.updateRoom(uuid, room);
    }

}
