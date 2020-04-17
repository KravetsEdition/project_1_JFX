package sample;

public class Alg_Luna {
    static boolean luhn(String pnr, long m){
        int extraChars = (int) (pnr.length() - m);
        pnr = pnr.substring(extraChars, (int) (m + extraChars));
        int sum = 0;
        for (int i = 0; i < pnr.length(); i++){
            char tmp = pnr.charAt(i);
            int num = tmp - '0';
            int product;
            if (i % 2 != 0){
                product = num * 1;
            }
            else{
                product = num * 2;
            }
            if (product > 9)
                product -= 9;
            sum+= product;
        }
        return (sum % 10 == 0);
    }
}

