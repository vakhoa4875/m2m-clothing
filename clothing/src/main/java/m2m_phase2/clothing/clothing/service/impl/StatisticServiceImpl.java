package m2m_phase2.clothing.clothing.service.impl;

import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.repository.ProductRepo;
import m2m_phase2.clothing.clothing.repository.StatisticRepo;
import m2m_phase2.clothing.clothing.service.StatisticService;
import m2m_phase2.clothing.clothing.service.VoucherService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepo statisticRepo;
    private final ProductRepo productRepo;
    private final VoucherService voucherService;

    @Override
    public List<Product> getTop10SoldProductByMonthAndYear(int month, int year) {
        var top10 = statisticRepo.getTop10SoldProductByMonthAndYear(month, year);
        if (top10 == null) {
            return null;
        }
        return top10.stream()
                .map(productRepo::findByProductId)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getActiveMonths() {
        return statisticRepo.getActiveMonths();
    }

    @Override
    public List<Object[]> getTopUsedVoucher(int month, int year) {
        var top10 = statisticRepo.getTopUsedVoucher(month, year);
        if (top10 == null) {
            return null;
        }
        return top10.stream()
                .map(e -> {
                    try {
                        e[0] = voucherService.findByVoucherID((Integer) e[0]);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    return new Object[]{e[0], e[1]};
                })
                .collect(Collectors.toList());
    }
}
