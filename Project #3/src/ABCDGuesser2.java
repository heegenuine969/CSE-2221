import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

public class ABCDGuesser2 {
    
    private ABCDGuesser2() {
    }
    
    /**
     * @author Heeji Kim
     * @CSE_2221_Project3 out
    **/
    
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

        //Declare a, b, c, and d
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        //Count the length of a, b, c, and d
        int aCount = 0;
        int bCount = 0;
        int cCount = 0;
        int dCount = 0;
        
        double estimate = 0;
        double estimateBest = 0;
        
        final double[] deJagerList = {-5.0, -4.0, -3.0, -2.0, -1.0, -1/2.0,
                -1.0/3.0, -1.0/4.0, 0.0, 1.0/4.0, 1.0/3.0, 1.0/2.0, 1.0, 2.0, 3.0, 4.0, 5.0};
        
        
        while (aCount < deJagerList.length) {
            bCount = 0;
            while (bCount < deJagerList.length) {
                cCount = 0;
                while (cCount < deJagerList.length) {
                    dCount = 0;
                    while (dCount < deJagerList.length) {
                        estimate = (Math.pow(w, deJagerList[aCount])
                        * (Math.pow(x, deJagerList[bCount]))
                        * (Math.pow(y, deJagerList[cCount]))
                        * (Math.pow(z, deJagerList[dCount])));
                        
                        if ((Math.abs(estimate - muString))
                                < Math.abs(estimateBest - muString)) {
                            a = aCount;
                            b = bCount;
                            c = cCount;
                            d = dCount;
                            estimateBest = estimate;
                        }
                        dCount++;
                    }
                    cCount++;
                }
                bCount++;
            }
            aCount++;
        }
        
        double error = (((Math.abs((estimateBest)- muString))) / (muString) * 100);
        
        out.println("The number of a is: " + deJagerList[a]);
        out.println("The number of b is: " + deJagerList[b]);
        out.println("The number of c is: " + deJagerList[c]);
        out.println("The number of d is: " + deJagerList[d]);
        
        out.println("The estimated value of µ: " + estimateBest);
        out.println("The relative error: " + error);
        
    }
}