package christmas.util;

import christmas.domain.Order;
import christmas.exception.argument.NotValidDateException;
import christmas.exception.argument.NotValidOrderException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TypeChanger {

    private static final String DELIMITER_TOTAL_MENU = ",";
    private static final String DELIMITER_MENU = "-";

    public static int toIntDate(String input){
        try{
            return Integer.parseInt(input);
        }catch (NumberFormatException e){
            throw new NotValidDateException();
        }
    }

    public static Order toOrder(String input){
        try{
            return parseMenuInput(input);
        }catch (NumberFormatException e){
            throw new NotValidDateException();
        }
    }

    private static Order parseMenuInput(String input) {
        Order order = new Order();

        List<String> totalMenu = splitToDelimiter(input, DELIMITER_TOTAL_MENU);
        for (String menu : totalMenu) {
            List<String> preMenu = splitToDelimiter(menu, DELIMITER_MENU);
            orderMenu(order, preMenu);
        }
        order.checkOnlyDrink();

        return order;
    }

    private static void orderMenu(Order order, List<String> preMenu) {
        if(preMenu.size()!=2){
            throw new NotValidOrderException();
        }
        order.orderMenu(preMenu.get(0),toIntOrder(preMenu.get(1)));
    }

    private static int toIntOrder(String input){
        try{
            return Integer.parseInt(input);
        }catch (NumberFormatException e){
            throw new NotValidOrderException();
        }
    }

    private static List<String> splitToDelimiter(String input, String delimiter){
        return Arrays.stream(input.split(delimiter))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
