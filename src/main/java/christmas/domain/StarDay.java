package christmas.domain;

import java.time.LocalDate;
import java.util.Arrays;

public enum StarDay {
    DECEMBER_3(LocalDate.of(2023, 12, 3)),
    DECEMBER_10(LocalDate.of(2023, 12, 10)),
    DECEMBER_17(LocalDate.of(2023, 12, 17)),
    DECEMBER_24(LocalDate.of(2023, 12, 24)),
    DECEMBER_25(LocalDate.of(2023, 12, 25)),
    DECEMBER_31(LocalDate.of(2023, 12, 31));

    private final LocalDate date;

    StarDay(LocalDate date) {
        this.date = date;
    }

    public static StarDay findByDate(LocalDate date) {
        return Arrays.stream(StarDay.values())
                .filter(starDay -> starDay.date.equals(date))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 형식을 입력하였습니다."));
    }

    public static boolean isStarDay(LocalDate date) {
        return Arrays.stream(StarDay.values())
                .anyMatch(starDay -> starDay.date.equals(date));
    }

    public LocalDate getDate() {
        return this.date;
    }
}
