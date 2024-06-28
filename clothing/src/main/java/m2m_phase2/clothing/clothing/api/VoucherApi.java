package m2m_phase2.clothing.clothing.api;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.data.dto.VoucherDto;
import m2m_phase2.clothing.clothing.data.model.VoucherM;
import m2m_phase2.clothing.clothing.service.impl.VoucherServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/voucher")
@RequiredArgsConstructor
public class VoucherApi {
    private final VoucherServiceImpl voucherService;
    private final HttpSession session;

    @GetMapping("/getVoucherByID")
    public ResponseEntity<?> doGetVoucherByID(@RequestParam("voucherID") Integer voucherID) {
        VoucherM voucherM;
        try {
            VoucherDto voucherDto = new VoucherDto(); // Tạo một đối tượng VoucherDto và đặt voucherID vào đó
            voucherDto.setVoucherID(voucherID);
            voucherM = voucherService.findVoucherByID(voucherDto);
        } catch (Exception e) {
            System.out.println("Gọi API thất bại: /api/voucher/getVoucherByID");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(voucherM);
    }

    @GetMapping("/getVoucherByID2")
    public ResponseEntity<?> doGetVoucherByID2(VoucherDto voucherDto) {
        VoucherM voucherM;
        try {
            voucherM = voucherService.findVoucherByID(voucherDto);
        } catch (Exception e) {
            System.out.println("Gọi API thất bại: /api/voucher/getVoucherByID");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(voucherM);
    }


    @PostMapping("/saveVoucher")
    public ResponseEntity<?> doPostSaveVoucher(@RequestBody VoucherDto voucherDto) {
        byte rowEffected;
        try {
            rowEffected = voucherService.saveVoucher(voucherDto);
        } catch (Exception e) {
            System.out.println("Call API Failed: /api/voucher/saveVoucher");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(rowEffected);
    }

    @PostMapping("/updateVoucher")
    public ResponseEntity<?> doPostUpdateVoucher(@RequestBody VoucherDto voucherDto) {
        byte rowEffected;
        try {
            rowEffected = voucherService.updateVoucher(voucherDto);
        } catch (Exception e) {
            System.out.println("Call API Failed: /api/voucher/updateVoucher");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(rowEffected);
    }


    @GetMapping("/getVouchersByEmail")
    public ResponseEntity<?> getVouchersByUserId(HttpSession session) {
        String email = (String) session.getAttribute("loggedInUser");
        if (email == null) {
            // Người dùng chưa đăng nhập, trả về lỗi hoặc thông báo phù hợp
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }

        List<VoucherM> voucherMList;
        try {
            // Gọi service để lấy danh sách voucher dựa trên email
            voucherMList = voucherService.findVouchersInfoByEmail(email);
        } catch (SQLException e) {
            System.out.println("Call API Failed: /api/voucher/getVouchersByUserId");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(voucherMList);
    }

    @GetMapping("/getCartVouchersByEmail")
    public ResponseEntity<?> getCartVouchersByEmail() {
        String email = (String) session.getAttribute("loggedInUser");
        if (email == null) {
            // Người dùng chưa đăng nhập, trả về lỗi hoặc thông báo phù hợp
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
        List<VoucherM> voucherMList;
        try {
            // Gọi service để lấy danh sách voucher dựa trên email
            voucherMList = voucherService.findVouchersInfoByEmail(email);
        } catch (SQLException e) {
            System.out.println("Call API Failed: /api/voucher/getVouchersByUserId");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(voucherMList);
    }

}
