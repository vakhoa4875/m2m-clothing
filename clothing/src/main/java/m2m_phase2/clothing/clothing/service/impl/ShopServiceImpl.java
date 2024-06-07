package m2m_phase2.clothing.clothing.service.impl;

import m2m_phase2.clothing.clothing.data.dto.ShopDto;
import m2m_phase2.clothing.clothing.data.entity.ShopE;
import m2m_phase2.clothing.clothing.data.model.ShopM;
import m2m_phase2.clothing.clothing.repository.ShopRepo;
import m2m_phase2.clothing.clothing.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ShopServiceImpl implements ShopService {


    @Autowired
    ShopRepo shopRepo;

    @Override
    public ShopM findShopByUser(String email) {
        return ShopM.convertShopEToShopM(shopRepo.findShopByUser(email));
    }

    @Override
    public int insertShop(ShopDto shopDto) throws SQLException {
        int rowCount = shopRepo.insertShop(shopDto.getNameShop(), shopDto.getDateEstablished(), shopDto.getUserE().getId());
        return rowCount > 0 ? rowCount : -1;
    }

    @Override
    public int updateShop(String nameShop, String logo, String email) throws SQLException {
        int rowCount = shopRepo.updateShop(nameShop, logo, email);
        return rowCount > 0 ? rowCount : -1;
    }

    @Override
    public ShopE findShopByEmail(String email) {
        return shopRepo.findByUserE_Email(email)
                .orElseThrow(() -> new RuntimeException("Shop not found for the given email"));
    }

}
