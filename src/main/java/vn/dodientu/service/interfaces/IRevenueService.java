package vn.dodientu.service.interfaces;

import java.time.LocalDate;
import java.util.List;

public interface IRevenueService {
    List<Object[]> getDailyRevenue(int month, int year, int day, int idShop);

    List<Object[]> getMonthlyRevenue(int year, int month, int idShop);

    List<Object[]> getYearlyRevenue(int idShop);

    List<Object[]> getTodayRevenue(int idShop);

//    List<Object[]> getDailyRevenue(int month, int year, int day);
//
//    List<Object[]> getMonthlyRevenue(int year, int month);
//
//    List<Object[]> getYearlyRevenue();
//
//    List<Object[]> getTodayRevenue();

}
