package christmas.converter;

public class StringToIntConverter implements Converter<String, Integer> {
    @Override
    public Integer convert(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바른 정수 형식으로 입력해주세요.");
        }
    }

    public int convertPositiveInt(String value) {
        int number = convert(value);

        if (number < 1) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        return number;
    }
}

