package christmas.controller;

import christmas.converter.StringToIntConverter;
import christmas.converter.StringToLocalDateConverter;
import christmas.domain.Food;
import christmas.domain.FoodCategory;
import christmas.domain.Order;
import christmas.util.DelimiterParser;
import christmas.view.InputView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InputHandler {

    private final InputView inputView;
    private final IteratorInputTemplate inputTemplate;

    public InputHandler(InputView inputView, IteratorInputTemplate iteratorInputTemplate) {
        this.inputView = inputView;
        this.inputTemplate = iteratorInputTemplate;
    }

    public LocalDate inputDate() {
        StringToLocalDateConverter converter = new StringToLocalDateConverter();
        return inputTemplate.execute(
                inputView::inputDate,
                converter::convert
        );
    }

    public Order inputOrderMenuAndCounts(LocalDate date) {
        DelimiterParser parser = new DelimiterParser();
        StringToIntConverter converter = new StringToIntConverter();
        return inputTemplate.execute(
                inputView::inputOrderMenuAndCounts,
                value -> {
                    value = value.trim();

                    // 티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1
                    // 먼저 ","로 구분
                    List<String> parsedFirst = parser.parse(value, ",");
                    // 이제 "-"로 구분
                    Map<Food, Integer> foodCountMap = new LinkedHashMap<>();
                    for (String part : parsedFirst) {
                        List<String> foodAndCount = parser.parse(part, "-");
                        Food food = Food.findByName(foodAndCount.get(0));
                        int count = converter.convertPositiveInt(foodAndCount.get(1));

                        foodCountMap.put(food, count);
                    }

                    validateOrder(foodCountMap);

                    return new Order(date, foodCountMap);
                }
        );
    }

    private void validateOrder(Map<Food, Integer> foodCountMap) {
        List<Food> foodList = new ArrayList<>(foodCountMap.keySet());
        //    - 예외4) 중복 메뉴를 입력한 경우
        long distinctCount = foodList.stream().distinct().count();
        if (distinctCount != foodList.size()) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        //    - 예외5) 음식의 총합이 20개 이상인 경우
        int totalOrderCount = foodCountMap.values()
                .stream()
                .mapToInt(count -> count)
                .sum();
        if (totalOrderCount > 20) {
            throw new IllegalArgumentException("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
        }
        //    - 예외6) 음료만 주문 했을 경우
        if (FoodCategory.isOnlyDrink(foodList)) {
            throw new IllegalArgumentException("음료만 주문 시, 주문할 수 없습니다.");
        }

    }
}