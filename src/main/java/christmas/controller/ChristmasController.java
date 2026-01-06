package christmas.controller;

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


    }
}
