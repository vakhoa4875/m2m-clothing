package m2m_phase2.clothing.clothing.api;

import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.data.dto.CommentDTO;
import m2m_phase2.clothing.clothing.data.dto.VoucherDetailsDto;
import m2m_phase2.clothing.clothing.service.NotificationService;
import m2m_phase2.clothing.clothing.service.impl.VoucherDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api-public/vouchers-details")
public class VoucherDetailsApi {
    @Autowired
    private VoucherDetailsServiceImpl voucherDetailsService;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/saveVoucherDetailsList")
    public ResponseEntity<?> doPostSaveVoucherDetailsList(@RequestBody List<VoucherDetailsDto> voucherDetailsDtoList) {
        byte totalRowsEffected = 0;
        try {
            for (VoucherDetailsDto voucherDetailsDto : voucherDetailsDtoList) {
                boolean checkExist = voucherDetailsService.isVoucherDetailsExist(voucherDetailsDto);
                if(checkExist){
                    totalRowsEffected += voucherDetailsService.deleteVoucherDetails(voucherDetailsDto);
                }else{
                    totalRowsEffected += voucherDetailsService.saveVoucherDetails(voucherDetailsDto);
                }
            }
        } catch (Exception e) {
            System.out.println("Gọi API thất bại: /api-public/vouchers-details/saveVoucherDetailsList");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(totalRowsEffected);
    }
    @PostMapping("/createVoucher")
    public ResponseEntity<?> doPostCreateComment(@RequestBody VoucherDetailsDto voucherDetailsDto) {
        log.info("Đã nhận yêu cầu tạo voucher: {}", voucherDetailsDto);
        byte rowEffected;
        try {
            rowEffected = voucherDetailsService.saveVoucherForUser(voucherDetailsDto);
        } catch (Exception e) {
            log.error("Lỗi khi tạo voucher", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi");
        }
        return ResponseEntity.ok(rowEffected);
    }
}
