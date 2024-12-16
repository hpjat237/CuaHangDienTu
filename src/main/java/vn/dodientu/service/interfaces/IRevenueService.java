package vn.dodientu.service.interfaces;

import java.time.LocalDateTime;

public interface IRevenueService {
    public double calculateDailyRevenue();
    public double calculateWeeklyRevenue();
    public double calculateMonthlyRevenue();
    public double calculateYearlyRevenue();
    public long calculateTotalRevenue();
    public double calculateRevenue(LocalDateTime startDate, LocalDateTime endDate);
    public double[] calculateMonthlyRevenueForYear();
    //public List<Order> getOrdersWithShippingStatusAndReceiveDate(LocalDateTime startDate, LocalDateTime endDate);
}
