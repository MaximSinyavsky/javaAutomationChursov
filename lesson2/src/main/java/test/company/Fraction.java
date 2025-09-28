package test.company;

import java.util.Objects;

public class Fraction {
    Integer num;
    Integer den;

    public Integer getNum() {
        return num;
    }

    public Integer getDen() {
        return den;
    }

    public Fraction(Integer num, Integer den) {
        if (num == null || den == null) {
            throw new NullPointerException("Values should be not null");
        }
        if (den == null) {
            throw new NullPointerException("Cannot divide zero");
        }
        this.num = num;
        this.den = den;
    }

    public static Fraction sum(Fraction first, Fraction second) {
        long bigCommonDen = ((long) first.getDen() * (long) second.getDen());
        if (bigCommonDen > Integer.MAX_VALUE) {
            throw new RuntimeException("Common denominator is too big!");
        }
        Integer commonDen = first.getDen() * second.getDen();
        Integer sumNum = first.getNum() * second.getDen() + second.getNum() * first.getDen();
        return new Fraction(sumNum, commonDen);
    }

    @Override
    public String toString() {
        return num + "/" + den;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return Objects.equals(num, fraction.num) && Objects.equals(den, fraction.den);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, den);
    }
}

// 1/2 + 2/3 = 7/6;
// -1/2 + 2/3 = 1/6;
// -1/2 + 1/3 = -1/6;

// nul/2 + null/3 => Values should be not null
// 1/0 + 2/3 => Cannot divide zero
// Integer.MAX_VALUE/2 +1/2 => Integer.MIN_VALUE
// Common denominator is too big!