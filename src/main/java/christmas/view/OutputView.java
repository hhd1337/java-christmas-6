package christmas.view;

import christmas.domain.BenefitBadge;
import christmas.domain.Food;
import christmas.util.ErrorMessage;
import christmas.view.formatter.MoneyFormatter;
import java.util.Map;

public class OutputView {

    public void printErrorMessage(Exception exception) {
        System.out.println(ErrorMessage.PREFIX + exception.getMessage());
    }

    public void printHelloAndDateInputPrompt() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public void printOrderMenuAndCountsInputPrompt() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public void printEventResultHeader(int month, int date) {
        System.out.printf("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", month, date);
        printEmptyLine();
    }

    public void printOrderedMenu(Map<Food, Integer> foodCountMap) {
        System.out.println("<주문 메뉴>");
        for (Food key : foodCountMap.keySet()) {
            int count = foodCountMap.get(key);
            System.out.printf("%s %d개%n", key.getName(), count);
        }
        printEmptyLine();
    }

    public void printTotalPrice(int totalPrice) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%s원%n", MoneyFormatter.format(totalPrice));
        printEmptyLine();
    }

    public void printGiveMenu(String thing, int count) {
        System.out.println("<증정 메뉴>");
        if (count != 0) {
            System.out.printf("%s %d개%n", thing, count);
            printEmptyLine();
            return;
        }
        System.out.println("없음");
        printEmptyLine();
    }

    public void printAllBenefits(int christmas, int weekday, int weekend, int specialStar, int give) {
        System.out.println("<혜택 내역>");
        if (christmas + weekday + weekend + specialStar + give == 0) {
            System.out.println("없음");
            printEmptyLine();
            return;
        }
        if (christmas != 0) {
            System.out.printf("크리스마스 디데이 할인: -%s원%n", MoneyFormatter.format(christmas));
        }
        if (weekday != 0) {
            System.out.printf("평일 할인: -%s원%n", MoneyFormatter.format(weekday));
        }
        if (weekend != 0) {
            System.out.printf("주말 할인: -%s원%n", MoneyFormatter.format(weekend));
        }
        if (specialStar != 0) {
            System.out.printf("특별 할인: -%s원%n", MoneyFormatter.format(specialStar));
        }
        if (give != 0) {
            System.out.printf("증정 이벤트: -%s원%n", MoneyFormatter.format(give));
        }
        printEmptyLine();
    }

    public void printTotalBenefitPrice(int totalBenefitPrice) {
        System.out.println("<총혜택 금액>");
        if (totalBenefitPrice == 0) {
            System.out.printf("0원%n");
            printEmptyLine();
            return;
        }
        System.out.printf("-%s원%n", MoneyFormatter.format(totalBenefitPrice));
        printEmptyLine();
    }

    public void printDiscountedPrice(int discountedPrice) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%s원%n", MoneyFormatter.format(discountedPrice));
        printEmptyLine();
    }

    public void printDecemberEventBadge(BenefitBadge badge) {
        System.out.println("<12월 이벤트 배지>");
        if (badge != null) {
            System.out.println(badge.getName());
            printEmptyLine();
            return;
        }
        System.out.println("없음");
        printEmptyLine();
    }


    private void printEmptyLine() {
        System.out.println();
    }

}
