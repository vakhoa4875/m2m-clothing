package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.entity.Product;

import java.util.List;

public interface StatisticService {
    List<Product> getTop10SoldProductByMonthAndYear(int month, int year);
    List<String> getActiveMonths();
    List<Object[]> getTopUsedVoucher(int month, int year);
}