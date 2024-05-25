package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.model.ShopM;

public interface ShopService {
    ShopM findShopByUser(String email);
}
