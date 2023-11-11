package christmas.util;

import java.text.DecimalFormat;

public class DecimalFormatter {

    public static String format(int value) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        return decimalFormat.format(value);
    }
}
