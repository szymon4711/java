package project.conversions.urnfield;

import project.conversions.GenericNumeralSystem;

public class Urnfield implements GenericNumeralSystem {
    @Override
    public String fromArabic(int val) {
        StringBuilder tmp_1 = new StringBuilder();
        StringBuilder tmp_5 = new StringBuilder();
        if (val > 0 && val < 30) {
            while (val > 4) {
                val -= 5;
                tmp_5.append("\\");
            }
            while (val > 0) {
                val -= 1;
                tmp_1.append("/");
            }
        }
        return tmp_1.toString() + tmp_5;
    }

    @Override
    public int toArabic(String val) {
        int res = 0;
        for (int i = 0; i < val.length(); i++) {
            if (val.charAt(i) == '\\')
                res += 5;
            else
                res += 1;
        }
        return res;
    }
}
