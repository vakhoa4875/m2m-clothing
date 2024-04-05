package m2m_phase2.clothing.clothing.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.data.dto.UserDto;
import m2m_phase2.clothing.clothing.data.model.UserM;
import m2m_phase2.clothing.clothing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api-public/users")
public class UserApi {
    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> doGetAllUsers() {
        List<?> listUser;
        try {
            listUser = userService.getAllUser();
        } catch (SQLException e) {
            System.out.println("Call API Failed: /api-public/users/getAllUsers");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(listUser);
    }

    @GetMapping("/getUserByUsernameAndEmail")
    public ResponseEntity<?> doGetUserByUsernameAndEmail(UserDto userDto) {
        UserM user;
        try {
            user = userService.getUserByUsernameAndEmail(userDto);
        } catch (SQLException e) {
            System.out.println("Call API Failed: /api-public/users/getUserByUsernameAndEmail");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> doPostSaveUser(@RequestBody UserDto userDto) {
        byte rowEffected;
        try {
            rowEffected = userService.saveUser(userDto);
        } catch (Exception e) {
            System.out.println("Call API Failed: /api-public/users/saveUser");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(rowEffected);
    }
    @PostMapping("/disableUser")
    public ResponseEntity<?> doDeleteUser(@RequestBody UserDto userDto) {
        byte rowEffected;
        try {
            rowEffected = userService.disableUser(userDto);
        } catch (SQLException e) {
            System.out.println("Call API Failed: /api-public/users/saveUser");
            rowEffected = 0;
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(rowEffected);
    }
//    @GetMapping("/getUserByDto")
//    public ResponseEntity<?> doGetUserByDto(UserDto userDto) {
//        List<?> listUser;
//        try {
//            listUser = userService.getUserByDto(userDto);
//        } catch (SQLException e) {
//            System.out.println("Call API Failed: /api-public/users/getUserByUsernameAndEmail");
//            throw new RuntimeException(e);
//        }
//        return ResponseEntity.ok(listUser);
//    }

}
