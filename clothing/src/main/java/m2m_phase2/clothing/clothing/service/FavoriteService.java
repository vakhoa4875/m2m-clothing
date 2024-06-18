package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.dto.FavoriteDto;
import m2m_phase2.clothing.clothing.data.entity.FavoriteE;
import m2m_phase2.clothing.clothing.data.model.FavoriteM;

public interface FavoriteService {
    FavoriteM checkFavorite(String email,String slugUrl);
    int addFavorite(FavoriteDto favoriteDto);
    int deleteFavorite(FavoriteDto favoriteDto);
}
