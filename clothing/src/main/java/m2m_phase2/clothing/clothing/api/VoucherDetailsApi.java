package m2m_phase2.clothing.clothing.api;

import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.data.dto.VoucherDetailsDto;
import m2m_phase2.clothing.clothing.data.dto.VoucherDto;
import m2m_phase2.clothing.clothing.service.VoucherDetailsService;
import m2m_phase2.clothing.clothing.service.impl.VoucherDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/saveVoucherDetailsList")
    public ResponseEntity<?> doPostSaveVoucherDetailsList(@RequestBody List<VoucherDetailsDto> voucherDetailsDtoList) {
        byte totalRowsEffected = 0;
        try {
            for (VoucherDetailsDto voucherDetailsDto : voucherDetailsDtoList) {
                totalRowsEffected += voucherDetailsService.saveVoucherDetails(voucherDetailsDto);
            }
        } catch (Exception e) {
            System.out.println("Gọi API thất bại: /api-public/vouchers-details/saveVoucherDetailsList");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(totalRowsEffected);
    }

}
