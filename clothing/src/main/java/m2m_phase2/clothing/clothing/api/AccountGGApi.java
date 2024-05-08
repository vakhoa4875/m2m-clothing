package m2m_phase2.clothing.clothing.api;

import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.data.dto.AccountGGDTO;
import m2m_phase2.clothing.clothing.data.entity.AccountGGE;
import m2m_phase2.clothing.clothing.service.impl.AccountGGServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@Slf4j
public class AccountGGApi {

    @Autowired
    AccountGGServiceImpl accountGGServiceimpl;

    @PostMapping("/saveUserGG")
    public ResponseEntity<?> saveUsergg (@RequestBody AccountGGDTO accountGGDTO){
        try {
            accountGGServiceimpl.saveAccountGG(accountGGDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("UserGG đã được lưu thành công.");
        } catch (SQLException e) {
            log.error("Đã xảy ra lỗi khi tạo UserGG", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi tạo UserGG.");
        }
    }

    @PostMapping("/findByEmailgg")
    public ResponseEntity<?> findAccountGGByEmail(@RequestBody AccountGGDTO accountGGDTO){
        try {
            AccountGGE accountGGE =  accountGGServiceimpl.findByEmailGG(accountGGDTO);
            System.out.println(accountGGE);
            return ResponseEntity.ok(accountGGE);
        } catch (SQLException e) {
            log.error("Đã xảy ra lỗi khi tim account gg", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi tìm UserGG.");
        }
    }

}
