package christmas.controller;

import christmas.converter.StringToLocalDateConverter;
import christmas.view.InputView;
import java.time.LocalDate;

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
}