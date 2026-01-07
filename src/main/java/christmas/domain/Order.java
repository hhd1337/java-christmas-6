package christmas.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Order {
    private static final int TOTAL_PRICE_MIN_FOR_BENEFIT = 10000;
    private static final int TOTAL_PRICE_MIN_FOR_CHAMPAGNE = 120000;

    private LocalDate date;
    private int totalPrice;
    private int discountedPrice;
    private boolean giveChampagne;
    private int totalBenefitAmount;
    private BenefitBadge benefitBadge;

    private Map<Food, Integer> foodCountMap;

    private Discounts discounts;

    public Order(LocalDate date, Map<Food, Integer> foodCountMap) {
        this.date = date;
        this.foodCountMap = foodCountMap;
        this.discounts = new Discounts();
        // 만원 미만일때 혜택 적용 안되게 미리 초기화
        this.totalPrice = 0;
        this.discountedPrice = 0;
        this.giveChampagne = false;
        this.totalBenefitAmount = 0;
        this.benefitBadge = null;
    }

    public void calculateBenefits() {

        for (Food key : foodCountMap.keySet()) {
            int count = foodCountMap.get(key);
            this.totalPrice += key.getPrice() * count;
        }

        if (totalPrice < TOTAL_PRICE_MIN_FOR_BENEFIT) {
            return;
        }

        List<Food> foodList = new ArrayList<>(foodCountMap.keySet());
        int dessertCount = (int) foodList.stream()
                .filter(food -> food.getFoodCategory().equals(FoodCategory.DESSERT))
                .count();
        int mainCount = (int) foodList.stream()
                .filter(food -> food.getFoodCategory().equals(FoodCategory.MAIN))
                .count();
        discounts.calculateDiscounts(date, dessertCount, mainCount);

        discountedPrice = totalPrice - discounts.getTotalDiscountAmount();
        // 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
        giveChampagne = totalPrice >= TOTAL_PRICE_MIN_FOR_CHAMPAGNE;

        // 총혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격
        totalBenefitAmount = discounts.getTotalDiscountAmount();
        if (giveChampagne) {
            totalBenefitAmount += Food.CHAMPAGNE.getPrice();
        }

        // 총혜택 금액에 따라 다른 이벤트 배지를 부여
        benefitBadge = BenefitBadge.findByBenefitAmountOrNull(totalBenefitAmount);
    }
}
