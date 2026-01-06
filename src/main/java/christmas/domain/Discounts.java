package christmas.domain;

public class Discounts {
    private int christmasDiscountAmount;
    private int weekDayDiscountAmount;
    private int weekEndDiscountAmount;
    private int specialDiscountAmount;

    public int getTotalDiscountAmount() {
        return christmasDiscountAmount + weekDayDiscountAmount + weekEndDiscountAmount + specialDiscountAmount;
    }
}
