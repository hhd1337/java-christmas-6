package christmas.domain;

import java.time.LocalDate;
import java.util.Map;

public class Order {
    private LocalDate date;
    private int totalPrice;
    private int discountedPrice;
    private boolean giveChampagne;
    private BenefitBadge benefitBadge;

    private Map<Food, Integer> foodCountMap;

    private Discounts discounts;

    public Order(LocalDate date, Map<Food, Integer> foodCountMap) {
        this.date = date;
        this.foodCountMap = foodCountMap;
    }

    // calculateDiscounts()
}
