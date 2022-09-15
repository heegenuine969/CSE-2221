import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

public class ABCDGuesser1 {
    
    private ABCDGuesser1() {
    }
    
    /**
     * @author Heeji Kim
     * @CSE_2221_Project3 out
    **/
    
    private static double error (double a, double b) {
        return Math.abs((a-b)/a * 100);
    }
    
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        //return a positive real number
        double muNumber = 0;
        out.println("Enter a positive real number: ");
        String muString = in.nextLine();
        //If it is true, get the number corresponding to the String
        if (FormatChecker.canParseDouble(muString) == true) {
            muNumber = Double.parseDouble(muString);
        }
        //if it is not positive real number
        while ((muNumber == 0) || (FormatChecker.canParseDouble(muString) == false)) {
            out.println("Enter a positive real number: ");
            muString = in.nextLine();
            
            if ((FormatChecker.canParseDouble(muString) == true)) {
                muNumber = Double.parseDouble(muString);
            }
        }
        return muNumber;
}
    
      
    private static double getPositiveDoubleNotOne(SimpleReader in, 
            SimpleWriter out) {
        double iNum = 0;
        //Enter the positive number, but not one.
        out.println("Enter a positive real number, but not one: ");
        String iString = in.nextLine();
        
        if (FormatChecker.canParseDouble(iString) == true) {
            iNum = Double.parseDouble(iString);
        }
        
        while ((iNum == 1) || (FormatChecker.canParseDouble(iString)) == false) {
            out.println("Please try again: ");
            iString = in.nextLine();
            
            if ((FormatChecker.canParseDouble(iString) == false)) {
                iNum = Double.parseDouble(iString);
            }
        }
            
        return iNum;
    }

    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        //getPositiveDouble method used to get µ.
        double muString = getPositiveDouble(in, out);
        
        out.println("Enter a value of w: ");
        out.println("Enter a value of x: ");
        out.println("Enter a value of y: ");
        out.println("Enter a value of z: ");

        
        double w = getPositiveDoubleNotOne(in, out);
        out.println("The value for w: ");
        double x = getPositiveDoubleNotOne(in, out);
        out.println("The value for x: ");
        double y = getPositiveDoubleNotOne(in, out);
        out.println("The value for y: ");
        double z = getPositiveDoubleNotOne(in, out);
        out.println("The value for z: ");


        //Count the length of a, b, c, and d
        int aCount = 0;
        int bCount = 0;
        int cCount = 0;
        int dCount = 0;
        
        double estimate = 0;
        double estimateBest = 0;
        
        final double[] deJagerList = {-5.0, -4.0, -3.0, -2.0, -1.0, -1/2.0,
                -1.0/3.0, -1.0/4.0, 0.0, 1.0/4.0, 1.0/3.0, 1.0/2.0, 1.0, 2.0, 3.0, 4.0, 5.0};
        
        double lowError = error(muString, estimateBest); //revise later
        
        for (int a = 0; a < deJagerList.length; a++) {
            
            for (int b = 0; b < deJagerList.length; b++) {

                for (int c = 0; c < deJagerList.length; c++) {

                    for (int d = 0; d < deJagerList.length; d++) {
                        estimate = (Math.pow(w, deJagerList[a])
                        * (Math.pow(x, deJagerList[b]))
                        * (Math.pow(y, deJagerList[c]))
                        * (Math.pow(z, deJagerList[d])));
                        
                        double newError = error(muString, estimate);//revise later
                        
                        if (newError < lowError) {
                            estimate = estimateBest;
                            lowError = newError;
                            a = aCount;
                            b = bCount;
                            c = cCount;
                            d = dCount;
                            }
                        }
                    }
                }
            }
        out.println("Enter a constant: ");
        out.println("Estimate: " + estimateBest);
        out.println("A, B, C, D Values: " + deJagerList[aCount] + " "
                + deJagerList[bCount] + " " + deJagerList[cCount]
                        + " " + deJagerList[dCount]);
        out.println("Error: " + error(muString, estimateBest) + " %");
        
    }
}