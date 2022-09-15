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
public final class Newton2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x Nonnegative number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {

        double result = 0;

        if (x > 0) {

            final double epsilonerror = 0.0001;
            double r = x;
          //Calculate sqrt until it's right
            while (Math.abs(r * r - x) / x > epsilonerror * epsilonerror) {
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
        String response = "Y";

        out.println("Do you want to compute?");
        response = in.nextLine();

        while (response.equals("Y")) {
            out.println("Give us a num: ");
            int x = in.nextInteger();
            out.println(sqrt(x));
            out.println("Do you really want to compute?");
            response = in.nextLine();
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
