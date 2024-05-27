package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.model.ShopM;

import java.sql.SQLException;

public interface ShopService {
    ShopM findShopByUser(String email) throws SQLException;
}
