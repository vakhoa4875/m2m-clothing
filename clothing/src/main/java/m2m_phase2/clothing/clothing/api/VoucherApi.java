package m2m_phase2.clothing.clothing.api;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.data.dto.UserDto;
import m2m_phase2.clothing.clothing.data.dto.VoucherDetailsDto;
import m2m_phase2.clothing.clothing.data.dto.VoucherDto;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.model.VoucherM;
import m2m_phase2.clothing.clothing.service.impl.VoucherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.sql.SQLException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api-public/vouchers")
public class VoucherApi {
    @Autowired
    private VoucherServiceImpl voucherService;
    @Autowired
    private HttpSession session;

    @GetMapping("/getAllVouchers")
    public ResponseEntity<?> doGetAllVouchers() {
        List<?> listVoucher;
        try {
            listVoucher = voucherService.findAll();
        } catch (Exception e) {
            System.out.println("Call API Failed: /api-public/vouchers/getAllVouchers");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(listVoucher);
    }

    @GetMapping("/getVoucherByID")
    public ResponseEntity<?> doGetVoucherByID(@RequestParam("voucherID") Integer voucherID) {
        VoucherM voucherM;
        try {
            VoucherDto voucherDto = new VoucherDto(); // Tạo một đối tượng VoucherDto và đặt voucherID vào đó
            voucherDto.setVoucherID(voucherID);
            voucherM = voucherService.findVoucherByID(voucherDto);
        } catch (Exception e) {
            System.out.println("Gọi API thất bại: /api-public/vouchers/getVoucherByID");
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
            System.out.println("Call API Failed: /api-public/vouchers/saveVoucher");
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
            System.out.println("Call API Failed: /api-public/vouchers/updateVoucher");
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
            System.out.println("Call API Failed: /api-public/vouchers/getVouchersByUserId");
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
            System.out.println("Call API Failed: /api-public/vouchers/getVouchersByUserId");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(voucherMList);
    }

}
