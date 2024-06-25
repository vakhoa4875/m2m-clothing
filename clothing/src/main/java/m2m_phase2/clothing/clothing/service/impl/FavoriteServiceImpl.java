package m2m_phase2.clothing.clothing.service.impl;

import m2m_phase2.clothing.clothing.data.dto.FavoriteDto;
import m2m_phase2.clothing.clothing.data.entity.FavoriteE;
import m2m_phase2.clothing.clothing.data.model.FavoriteM;
import m2m_phase2.clothing.clothing.data.model.FavoriteProductM;
import m2m_phase2.clothing.clothing.repository.FavoriteRepo;
import m2m_phase2.clothing.clothing.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    FavoriteRepo favoriteRepo;

    @Override
    public FavoriteM checkFavorite(String email, String slugUrl) {
        FavoriteE favoriteE = favoriteRepo.checkFavorite(email, slugUrl);
        return favoriteE == null ? null : FavoriteM.convertFavoriteEToFavoriteM(favoriteE);
    }

    @Override
    public int addFavorite(FavoriteDto favoriteDto) {
        return favoriteRepo.saveFavorite(favoriteDto.getUser().getId(), favoriteDto.getProduct().getProductId());
    }

    @Override
    public int deleteFavorite(FavoriteDto favoriteDto) {
        return favoriteRepo.deleteFavorite(favoriteDto.getUser().getId(), favoriteDto.getProduct().getProductId());
    }

    @Override
    public List<FavoriteProductM> getFavoritesByEmail(String email) {
        List<Object[]> results = favoriteRepo.findFavoritesByEmail(email);
        return results.stream().map(result -> FavoriteProductM.builder()
                .productId((Integer) result[0])
                .productName((String) result[1])
                .pictures((String) result[2])
                .slugUrl((String) result[3])
                .id((Integer) result[4])
                .build())
                .collect(Collectors.toList());
    }

    @Override
    public int deleteFavoriteProduct(int id, int productId) {
        return favoriteRepo.deleteFavoriteByEmailAndProductId(id, productId);
    }
}
