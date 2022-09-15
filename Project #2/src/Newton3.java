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
public final class Newton3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton3() {
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
        String response = "Y";
        // Newton 3's instructions to check the value of epsilon.
        out.println("Please type in an epsilon");
        epsilon = in.nextDouble();
        out.println("Whether you want to compute sqrt? ");
        response = in.nextLine();

        while (response.equals("Y")) {
            out.println("Give us a num: ");
            x = in.nextDouble();
            double result = sqrt(x, epsilon);
            out.println("The value of the sqrt is: " + result);
            out.println("Whether you want to continue computing sqrt? ");
            response = in.nextLine();
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
