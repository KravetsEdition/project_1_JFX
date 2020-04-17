package sample;

public class NumberKode {
    public long getCountsOfDigits(long kode) { // возвращяет количество чисел в числе
        long count = (kode == 0) ? 1 : 0;
        while (kode != 0) {
            count++;
            kode /= 10;
        }
        return count;
    }
}
