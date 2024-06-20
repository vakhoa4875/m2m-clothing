package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.model.SearchShopM;
import m2m_phase2.clothing.clothing.data.model.ShopM;
import m2m_phase2.clothing.clothing.data.model.ShowShopSearchM;

import java.util.List;

public interface SearchShopService {
    List<SearchShopM> searchShop(String nameShop);
    ShowShopSearchM showShopSearch(int shopId);
}
