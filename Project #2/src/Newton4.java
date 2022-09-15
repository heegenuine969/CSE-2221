import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x       positive number to compute square root of
     * @param epsilon value for epsilon.
     * @return estimate of square root
     */
    private static double sqrt(double x, double epsilon) {

        double result = 0;
        if (x > 0) {

            double r = x;
          //Calculate sqrt until it's right
            while (Math.abs(r * r - x) / x > epsilon * epsilon) {
                r = ((r + (x / r)) / 2.0);
            }
            result = r;
        }

        return result;

    }

    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        double x = 0.0;
        double epsilon;

        out.println("Please type in an epsilon");
        epsilon = in.nextDouble();
        out.println("Please give a value to compute:");
        x = in.nextDouble();

        while (x >= 0) {
            double result = sqrt(x, epsilon);
            out.println("The value of the sqrt is: " + result);
            out.println("Please give a value to compute:");
            x = in.nextDouble();
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
