package pl.retsuz.conversions.roman;

import pl.retsuz.conversions.GenericNumeralSystem;


public class Roman implements GenericNumeralSystem {

    int fun(char tmp) {
        if (tmp == 'I')
            return 1;
        else if (tmp == 'V')
            return 5;
        else if (tmp == 'X')
            return 10;
        else if (tmp == 'L')
            return 50;
        else if (tmp == 'C')
            return 100;
        else if (tmp == 'D')
            return 500;
        else if (tmp == 'M')
            return 1000;
        return -1;
    }


    public int toArabic(String val) {
        int res = 0;
        for (int i = 0; i < val.length(); i++) {
            int tmp1 = fun(val.charAt(i));

            if (i + 1 < val.length()) {
                int tmp2 = fun(val.charAt(i + 1));
                if (tmp1 >= tmp2) {
                    res = res + tmp1;
                } else {
                    res = res + tmp2 - tmp1;
                    i++;
                }
            } else {
                res = res + tmp1;
            }
        }
        return res;
    }

    public String fromArabic(int val) {
        StringBuilder res = new StringBuilder();
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < values.length; i++) {
            while (val >= values[i]) {
                val -= values[i];
                res.append(roman[i]);
            }
        }
        return res.toString();
    }
}



