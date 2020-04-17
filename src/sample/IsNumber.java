package sample;

import static java.lang.Character.isDigit;

public class IsNumber { // Проверка на число, если находит один из символов char то возвращяет false
    public static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!isDigit(str.charAt(i))) return false;
        }
        return true;
    }
}
