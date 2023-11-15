package christmas.resource;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public enum BadgeValue {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private String name;
    private int price;

    BadgeValue(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static String getBadge(int price) {
        return findMatchingBadge(price)
                .map(badgeValue -> badgeValue.name)
                .orElse(null);
    }

    private static Stream<BadgeValue> getDescSortedBadgeValue() {
        return Arrays.stream(BadgeValue.values())
                .sorted(Comparator.comparingInt((BadgeValue badge) -> badge.price).reversed());
    }

    private static Optional<BadgeValue> findMatchingBadge(int price) {
        return getDescSortedBadgeValue()
                .filter(badgeValue -> price >= badgeValue.price)
                .findFirst();
    }
}
