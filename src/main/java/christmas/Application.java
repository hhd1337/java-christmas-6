package christmas;

import christmas.controller.ChristmasController;

public class Application {
    public static void main(String[] args) {
        ChristmasConfig christmasConfig = new ChristmasConfig();
        ChristmasController christmasController = christmasConfig.christmasController();
        christmasController.process();
    }
}
