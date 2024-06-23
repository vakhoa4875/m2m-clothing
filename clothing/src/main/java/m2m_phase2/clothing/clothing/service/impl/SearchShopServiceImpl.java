package m2m_phase2.clothing.clothing.service.impl;

import m2m_phase2.clothing.clothing.data.entity.ShopE;
import m2m_phase2.clothing.clothing.data.model.SearchShopM;
import m2m_phase2.clothing.clothing.data.model.ShowShopSearchM;
import m2m_phase2.clothing.clothing.repository.SearchShopRepo;
import m2m_phase2.clothing.clothing.service.SearchShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class SearchShopServiceImpl implements SearchShopService {
    @Autowired
    private SearchShopRepo searchShopRepo;

    @Override
    public List<SearchShopM> searchShop(String nameShop) {
        List<ShopE> shopEList = searchShopRepo.searchShopsByName(nameShop.toLowerCase());
        return SearchShopM.converListShopEToSearchShopM(shopEList);
    }

    @Override
    public ShowShopSearchM showShopSearch(int shopId) {
        ShopE showShop = searchShopRepo.findById(shopId);
        return ShowShopSearchM.convertShopEToShowShopSearchM(showShop);
    }
}
