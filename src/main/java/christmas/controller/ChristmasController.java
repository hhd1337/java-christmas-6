package christmas.controller;

import christmas.domain.BenefitBadge;
import christmas.domain.Discounts;
import christmas.domain.Food;
import christmas.domain.Order;
import christmas.view.OutputView;
import java.time.LocalDate;

public class ChristmasController {
    private final InputHandler inputHandler;
    private final OutputView outputView;

    public ChristmasController(InputHandler inputHandler, OutputView outputView) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
    }

    public void process() {
        outputView.printHelloAndDateInputPrompt();
        LocalDate date = inputHandler.inputDate();

        outputView.printOrderMenuAndCountsInputPrompt();
        Order initOrder = inputHandler.inputOrderMenuAndCounts(date);

        initOrder.calculateBenefits();

        printEventResult(initOrder);
    }

    private void printEventResult(Order initOrder) {
        LocalDate date = initOrder.getDate();
        outputView.printEventResultHeader(date.getMonth().getValue(), date.getDayOfMonth());
        outputView.printOrderedMenu(initOrder.getFoodCountMap());
        outputView.printTotalPrice(initOrder.getTotalPrice());
        if (initOrder.isGiveChampagne()) {
            outputView.printGiveMenu(Food.CHAMPAGNE.getName(), 1);
        }
        if (!initOrder.isGiveChampagne()) {
            outputView.printGiveMenu(Food.CHAMPAGNE.getName(), 0);
        }
        Discounts discounts = initOrder.getDiscounts();
        int christmas = discounts.getChristmasDiscountAmount();
        int weekday = discounts.getWeekDayDiscountAmount();
        int weekend = discounts.getWeekEndDiscountAmount();
        int specialStar = discounts.getSpecialDiscountAmount();
        int giveAmount = 0;
        if (initOrder.isGiveChampagne()) {
            giveAmount = Food.CHAMPAGNE.getPrice();
        }

        outputView.printAllBenefits(christmas, weekday, weekend, specialStar, giveAmount);
        outputView.printTotalBenefitPrice(initOrder.getTotalBenefitAmount());
        outputView.printDiscountedPrice(initOrder.getDiscountedPrice());
        BenefitBadge badge = initOrder.getBenefitBadge();

        outputView.printDecemberEventBadge(badge);

    }


}
