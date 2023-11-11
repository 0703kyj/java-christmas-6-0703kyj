package christmas.util;

import christmas.exception.argument.NotValidDateException;

public class TypeChanger {

    public static int toIntDate(String input){
        try{
            return Integer.parseInt(input);
        }catch (NumberFormatException e){
            throw new NotValidDateException();
        }
    }
}
