package m2m_phase2.clothing.clothing.service.impl;


import m2m_phase2.clothing.clothing.data.dto.SaleDTO;
import m2m_phase2.clothing.clothing.data.entity.Sale;
import m2m_phase2.clothing.clothing.data.model.SaleM;
import m2m_phase2.clothing.clothing.repository.SaleRepo;
import m2m_phase2.clothing.clothing.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepo saleRepo;

    @Override
    public List<SaleM> getAllSales() {
        List<Sale> listSaleE = saleRepo.findAll();
        return SaleM.convertListSaleEToListSaleM(listSaleE);
    }

    @Override
    public SaleM getSaleById(int saleId) {
        Sale saleE = saleRepo.findBySaleId(saleId);
        return SaleM.convertSaleEToSaleM(saleE);
    }

    @Override
    public SaleM UpdateSale(int saleId, SaleDTO saleDTO) throws SQLException {
        Sale sale = SaleDTO.convertSaleDtoToSaleE(saleDTO);
        Sale saleE = saleRepo.findBySaleId(saleId);
        if (!Objects.isNull(saleE)) {
            saleRepo.updateSale(
                    sale.getSaleName(),
                    sale.getSalePercent(),
                    sale.getSaleStart(),
                    sale.getSaleEnd(),
                    saleId);
        }
        return SaleM.convertSaleEToSaleM(sale);
    }

    @Override
    public SaleM createSale( SaleDTO saleDTO) {
        Sale sale = SaleDTO.convertSaleDtoToSaleE(saleDTO);
        sale = saleRepo.save(sale);
        return SaleM.convertSaleEToSaleM(sale);
    }

    @Override
    public void deleteSale(int saleId) {
        saleRepo.deleteBySaleId(saleId);
    }

    @Override
    public void updateProductSaleFromSale(Integer sale_ID, Integer product_id) {
        saleRepo.updateProductSaleFromSale(sale_ID, product_id);
    }
}
