package christmas.domain;

import java.time.LocalDate;

public class Discounts {
    private int christmasDiscountAmount;
    private int weekDayDiscountAmount;
    private int weekEndDiscountAmount;
    private int specialDiscountAmount;

    public Discounts() {
        initDiscounts();
    }

    public void initDiscounts() {
        this.christmasDiscountAmount = 0;
        this.weekDayDiscountAmount = 0;
        this.weekEndDiscountAmount = 0;
        this.specialDiscountAmount = 0;
    }

    public int getTotalDiscountAmount() {
        return christmasDiscountAmount + weekDayDiscountAmount + weekEndDiscountAmount + specialDiscountAmount;
    }

    public void calculateDiscounts(LocalDate date, int dessertMenuCount, int mainMenuCount) {
        calculateChristmasDiscountAmount(date);
        calculateWeekDayDiscountAmount(date, dessertMenuCount);
        calculateWeekEndDiscountAmount(date, mainMenuCount);
        calculateSpecialDiscountAmount(date);
    }

    //크리스마스 디데이 할인
    //이벤트 기간: 2023.12.1 ~ 2023.12.25
    //1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
    //총주문 금액에서 해당 금액만큼 할인
    //(e.g. 시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인)
    public void calculateChristmasDiscountAmount(LocalDate date) {
        int dayOfMonth = date.getDayOfMonth();
        if (dayOfMonth <= 25 && isDecember2023(date)) {
            christmasDiscountAmount = 900 + (dayOfMonth * 100);
        }
    }

    //평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
    public void calculateWeekDayDiscountAmount(LocalDate date, int dessertMenuCount) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        if (isWeekday(dayOfWeek) && isDecember2023(date)) {
            weekDayDiscountAmount = dessertMenuCount * 2023;
        }
    }

    //주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
    public void calculateWeekEndDiscountAmount(LocalDate date, int mainMenuCount) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        if (!isWeekday(dayOfWeek) && isDecember2023(date)) {
            weekEndDiscountAmount = mainMenuCount * 2023;
        }
    }

    //특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
    public void calculateSpecialDiscountAmount(LocalDate date) {
        if (StarDay.isStarDay(date) && isDecember2023(date)) {
            specialDiscountAmount = 1000;
        }
    }

    private boolean isDecember2023(LocalDate date) {
        return date.getYear() == 2023 && date.getMonth().getValue() == 12;
    }

    private boolean isWeekday(int dayOfWeek) {
        return dayOfWeek <= 4 || dayOfWeek == 7;
    }

    public int getChristmasDiscountAmount() {
        return christmasDiscountAmount;
    }

    public int getWeekDayDiscountAmount() {
        return weekDayDiscountAmount;
    }

    public int getWeekEndDiscountAmount() {
        return weekEndDiscountAmount;
    }

    public int getSpecialDiscountAmount() {
        return specialDiscountAmount;
    }
}
