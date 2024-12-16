package vn.dodientu.service.implementation;

import hcmute.edu.vn.repository.OrderRepository;
import hcmute.edu.vn.service.IRevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RevenueService implements IRevenueService {
    @Autowired
    private OrderRepository orderRepository;

    public RevenueService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Object[]> getDailyRevenue(int month, int year, int day, int idShop)  {
        return orderRepository.getDailyRevenue(month, year, day, idShop);
    }

    @Override
    public List<Object[]> getMonthlyRevenue(int year, int month, int idShop) {
        return orderRepository.getMonthlyRevenue(year, month, idShop);
    }

    @Override
    public List<Object[]> getYearlyRevenue(int idShop) {
        return orderRepository.getYearlyRevenue(idShop);
    }

    @Override
    public List<Object[]> getTodayRevenue(int idShop) {
        LocalDate today = LocalDate.now();
        return orderRepository.findRevenueByDate(today, today.plusDays(1),idShop); // ngày hôm nay
    }

}
