package christmas.domain;

import java.util.Arrays;
import java.util.List;

public enum FoodCategory {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료");

    private final String name;

    FoodCategory(String name) {
        this.name = name;
    }

    public static FoodCategory findByName(String name) {
        return Arrays.stream(FoodCategory.values())
                .filter(category -> category.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 형식을 입력하였습니다."));
    }

    public static boolean isOnlyDrink(List<Food> foodList) {
        return foodList.stream().allMatch(food -> food.getFoodCategory().equals(DRINK));
    }

    public String getName() {
        return this.name;
    }
}
