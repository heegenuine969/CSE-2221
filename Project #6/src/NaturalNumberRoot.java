import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
public final class NaturalNumberRoot {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private NaturalNumberRoot() {
        // no code needed here
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Volation of: n is not null";
        assert r >= 2 : "Volation of r >= 2";

        NaturalNumber one = new NaturalNumber2(1);
        NaturalNumber two = new NaturalNumber2(2);

        NaturalNumber lowEnough = new NaturalNumber2();
        NaturalNumber tooHigh = new NaturalNumber2();
        tooHigh.copyFrom(n);
        tooHigh.increment();

        NaturalNumber guess = new NaturalNumber2();
        guess.copyFrom(n);
        guess.divide(two);

        NaturalNumber number = new NaturalNumber2();
        number.copyFrom(tooHigh);
        number.subtract(lowEnough);

        while (number.compareTo(one) > 0) {
            NaturalNumber power = new NaturalNumber2();
            power.copyFrom(guess);
            power.power(r);

            if (n.compareTo(power) < 0) {
                tooHigh.transferFrom(guess);
            } else {
                lowEnough.transferFrom(guess);
            }
            number.copyFrom(tooHigh);
            number.subtract(lowEnough);
            number.divide(two);
            number.add(lowEnough);
            guess.transferFrom(number);
            number.copyFrom(tooHigh);
            number.subtract(lowEnough);
        }
        n.transferFrom(lowEnough);
    }
    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }
}
