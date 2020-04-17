package sample;

public class RemoveSpace {
    public static String removeSpace(String s) { // убирает пробелы из числа
        String withoutspaces = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ')
                withoutspaces += s.charAt(i);

        }
        return withoutspaces;

    }
}
