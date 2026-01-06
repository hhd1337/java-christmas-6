package christmas.converter;

import java.time.LocalDate;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String value) {
        int dayOfMonth = convertToInt(value);

        if (dayOfMonth > 31 || dayOfMonth < 1) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
        return LocalDate.of(2023, 12, dayOfMonth);
    }

    public int convertToInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
