package m2m_phase2.clothing.clothing.service;

import java.util.ArrayList;

public interface StatisticService {
    ArrayList<Object[]> getTop10SoldProductByMonthAndYear(int month, int year);
}