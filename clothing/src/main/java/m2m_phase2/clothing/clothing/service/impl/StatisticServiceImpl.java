package m2m_phase2.clothing.clothing.service.impl;

import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.repository.ProductRepo;
import m2m_phase2.clothing.clothing.repository.StatisticRepo;
import m2m_phase2.clothing.clothing.service.StatisticService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepo statisticRepo;
    private final ProductRepo productRepo;

    @Override
    public ArrayList<Object[]> getTop10SoldProductByMonthAndYear(int month, int year) {
        var top10 = statisticRepo.getTop10SoldProductByMonthAndYear(month, year);
        if (top10 == null) {
            return null;
        }
        for (Object[] objects : top10) {
            objects[0] = productRepo.findByProductId((int) objects[0]);
        }
        return top10;
    }

    @Override
    public ArrayList<String> getActiveMonths() {
        return statisticRepo.getActiveMonths();
    }
}
