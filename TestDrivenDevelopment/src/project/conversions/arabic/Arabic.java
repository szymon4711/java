package project.conversions.arabic;

import project.conversions.GenericNumeralSystem;

public class Arabic implements GenericNumeralSystem {

    @Override
    public String fromArabic(int val) {
        return Integer.toString(val);
    }

    @Override
    public int toArabic(String val) {
        return Integer.parseInt(val);
    }
}
