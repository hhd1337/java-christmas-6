package christmas.domain;

import static christmas.domain.FoodCategory.APPETIZER;
import static christmas.domain.FoodCategory.DESSERT;
import static christmas.domain.FoodCategory.DRINK;
import static christmas.domain.FoodCategory.MAIN;

import java.util.Arrays;

public enum Food {
    MUSHROOM_SOUP("양송이수프", 6000, APPETIZER),
    TAPAS("타파스", 5500, APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, APPETIZER),
    T_BONE_STEAK("티본스테이크", 55000, MAIN),
    BARBECUED_RIB("바베큐립", 54000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MAIN),
    CHOCOLATE_CAKE("초코케이크", 15000, DESSERT),
    ICE_CREAM("아이스크림", 5000, DESSERT),
    ZERO_COKE("제로콜라", 3000, DRINK),
    RED_WINE("레드와인", 60000, DRINK),
    CHAMPAGNE("샴페인", 25000, DRINK);


    private String name;
    private int price;
    private FoodCategory foodCategory;

    Food(String name, int price, FoodCategory foodCategory) {
        this.name = name;
        this.price = price;
        this.foodCategory = foodCategory;
    }

    public static Food findByName(String name) {
        return Arrays.stream(Food.values())
                .filter(food -> food.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요."));
    }


    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public int getPrice() {
        return price;
    }
}
