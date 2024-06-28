package m2m_phase2.clothing.clothing.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.data.dto.SaleDTO;
import m2m_phase2.clothing.clothing.data.model.SaleM;
import m2m_phase2.clothing.clothing.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleApi {
    private final SaleService saleService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllSales() {
        try {
            List<SaleM> sales = saleService.getAllSales();
            return ResponseEntity.ok(sales);
        } catch (SQLException e) {
            log.error("Failed to get all sales", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<?> getSaleById(@PathVariable int saleId) {
        try {
            SaleM sale = saleService.getSaleById(saleId);
            if (sale != null) {
                return ResponseEntity.ok(sale);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            log.error("Failed to get sale by ID: {}", saleId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSale(@RequestBody SaleDTO saleDTO) {
        try {
            SaleM createdSale = saleService.createSale(saleDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSale);
        } catch (SQLException e) {
            log.error("Failed to create sale", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{saleId}")
    public ResponseEntity<?> updateSale(@PathVariable int saleId, @RequestBody SaleDTO saleDTO) {
        try {
            SaleM updatedSale = saleService.UpdateSale(saleId, saleDTO);
            if (updatedSale != null) {
                return ResponseEntity.ok(updatedSale);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            log.error("Failed to update sale with ID: {}", saleId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{saleId}")
    public ResponseEntity<?> deleteSale(@PathVariable int saleId) {
        try {
            saleService.deleteSale(saleId);
            return ResponseEntity.noContent().build();
        } catch (SQLException e) {
            log.error("Failed to delete sale with ID: {}", saleId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/updateSaleOnProduct")
    public ResponseEntity<?> updateSaleOnProduct(@RequestParam int sale_ID, @RequestParam int product_id) {
        try {
            saleService.updateProductSaleFromSale(sale_ID, product_id);
        } catch (Exception e) {
            log.error("Failed to create sale", e);
        }
        return ResponseEntity.ok("cập nhật thành công");
    }
}
