package christmas;

import christmas.controller.InputHandler;
import christmas.controller.IteratorInputTemplate;
import christmas.controller.ChristmasController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasConfig {

    private InputView inputView;
    private OutputView outputView;
    private IteratorInputTemplate iteratorInputTemplate;
    private InputHandler inputHandler;
    private ChristmasController christmasController;

    public InputView inputView() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    public OutputView outputView() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    public IteratorInputTemplate iteratorInputTemplate() {
        if (iteratorInputTemplate == null) {
            iteratorInputTemplate = new IteratorInputTemplate(outputView());
        }
        return iteratorInputTemplate;
    }

    public InputHandler iteratorInputHandler() {
        if (inputHandler == null) {
            inputHandler = new InputHandler(inputView(), iteratorInputTemplate());
        }
        return inputHandler;
    }

    public ChristmasController christmasController() {
        if (christmasController == null) {
            christmasController = new ChristmasController(iteratorInputHandler(), outputView());
        }
        return christmasController;
    }
}
