package m2m_phase2.clothing.clothing.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.data.dto.UserDto;
import m2m_phase2.clothing.clothing.data.dto.VoucherDetailsDto;
import m2m_phase2.clothing.clothing.data.entity.VoucherE;
import m2m_phase2.clothing.clothing.data.model.UserM;
import m2m_phase2.clothing.clothing.service.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> doGetAllUsers() {
        List<?> listUser;
        try {
            listUser = userService.getAllUser();
        } catch (SQLException e) {
            System.out.println("Call API Failed: /api/user/getAllUsers");
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
            System.out.println("Call API Failed: /api/user/getUserByUsernameAndEmail");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getListUserByVoucherID")
    public ResponseEntity<?> doGetListUserByVoucherID(@RequestParam("voucherID") Integer voucherID) {
        List<UserM> user;
        try {
            VoucherDetailsDto voucherDetailsDto = new VoucherDetailsDto();
            VoucherE voucherE = new VoucherE();
            voucherE.setVoucherID(voucherID);
            voucherDetailsDto.setVoucher(voucherE);
            user = userService.findUserNotInVoucher(voucherDetailsDto);
        } catch (SQLException e) {
            System.out.println("Call API Failed: /api/user/getListUserByVoucherID");
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
            System.out.println("Call API Failed: /api/user/saveUser");
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
            System.out.println("Call API Failed: /api/user/saveUser");
            rowEffected = 0;
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(rowEffected);
    }

    @PostMapping("/updateUserInfo")
    public ResponseEntity<?> doPostUpdateUserInfo(@RequestBody UserDto userDto) {
        byte rowEffected;
        try {
            // Nhận dữ liệu base64 từ Frontend
            String base64Data = userDto.getAvatar();
            if (base64Data != null && !base64Data.isEmpty()) {
                String name = base64Data.substring(0, base64Data.indexOf(","));
                int viTriDauPhayThuHai = base64Data.indexOf(",", base64Data.indexOf(",") + 1);
                String data = base64Data.substring(viTriDauPhayThuHai + 1);
                byte[] bytes = Base64.decodeBase64(data);
                File file = new File("src/main/resources/templates/swappa/assests/imagesUser", name);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(bytes);
                fos.close();
                userDto.setAvatar(name);
            } else {
                UserM existingUser = userService.getUserByUsernameAndEmail(userDto); // Lấy thông tin người dùng từ UserService
                if (existingUser != null) {
                    userDto.setAvatar(existingUser.getAvatar()); // Sử dụng ảnh của người dùng hiện tại
                }
            }
            rowEffected = userService.updateUserInfo(userDto);
        } catch (Exception e) {
            System.out.println("Gọi API thất bại: /api/user/saveUser");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(rowEffected);
    }
}
