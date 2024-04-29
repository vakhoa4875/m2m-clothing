package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.dto.SaleDTO;
import m2m_phase2.clothing.clothing.data.model.SaleM;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface SaleService {
     List<SaleM> getAllSales()throws SQLException;
     SaleM getSaleById(int saleId) throws SQLException;
     SaleM createSale(SaleDTO saleDTO) throws SQLException;
     SaleM UpdateSale(int saleId, SaleDTO saleDTO) throws SQLException;
     void deleteSale(int saleId) throws SQLException;
     void updateProductSaleFromSale(Integer sale_ID, Integer product_id);
}
