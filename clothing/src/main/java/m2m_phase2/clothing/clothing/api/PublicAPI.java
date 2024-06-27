package m2m_phase2.clothing.clothing.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.data.dto.AccountGGDTO;
import m2m_phase2.clothing.clothing.data.entity.AccountGGE;
import m2m_phase2.clothing.clothing.service.AccountGGService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
@Slf4j
public class PublicAPI {
    private final AccountGGService accountGGService;

    @PostMapping("/googleAccount/save")
    public ResponseEntity<?> doPostSaveGGAccount (@RequestBody AccountGGDTO accountGGDTO){
        try {
            accountGGService.saveAccountGG(accountGGDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("UserGG đã được lưu thành công.");
        } catch (SQLException e) {
            log.error("Đã xảy ra lỗi khi tạo UserGG", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi tạo UserGG.");
        }
    }

    @PostMapping("/googleAccount/findByEmail")
    public ResponseEntity<?> doPostFindGGAccountByEmail(@RequestBody AccountGGDTO accountGGDTO){
        try {
            AccountGGE accountGGE =  accountGGService.findByEmailGG(accountGGDTO);
            return ResponseEntity.ok(accountGGE);
        } catch (SQLException e) {
            log.error("Đã xảy ra lỗi khi tim account gg", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi tìm UserGG.");
        }
    }
}
