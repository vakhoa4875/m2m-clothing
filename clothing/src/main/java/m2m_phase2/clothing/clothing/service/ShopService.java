package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.dto.ShopDto;
import m2m_phase2.clothing.clothing.data.entity.ShopE;
import m2m_phase2.clothing.clothing.data.model.ShopM;

import java.sql.SQLException;
import java.util.List;

public interface ShopService {

    ShopE findShopByEmail(String email);

    ShopM findShopByUser(String email) throws SQLException;

    int insertShop(ShopDto shopDto) throws SQLException;

    int updateShop(String nameShop, String logo, String email) throws SQLException;

    List<Object[]> getShopDetails(int shopId);

}
