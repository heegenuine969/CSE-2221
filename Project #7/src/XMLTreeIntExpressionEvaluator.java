import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author Heeji Kim
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private XMLTreeIntExpressionEvaluator() {
        // no code needed here
    }
    /**
     * Evaluate the given expression.
     * 
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        assert exp != null: "Volation of: exp is not null";
        
        if (exp.label().equals("times")) {
            return evaluate(exp.child(0)) * evaluate(exp.child(1));
        }
        if (exp.label().equals("plus")) {
            return evaluate(exp.child(0)) + evaluate(exp.child(1));
        }
        if (exp.label().equals("divide")) {
            int dividend = evaluate(exp.child(1));
            if (dividend == 0) {
                Reporter.fatalErrorToConsole("Error: Dividing by Zero");
            }
            return evaluate(exp.child(0)) /dividend;
        }
        if (exp.label().equals("minus")) {
            return evaluate(exp.child(0)) - evaluate(exp.child(1));
        }
        if (exp.label().equals("number")) {
            return Integer.parseInt(exp.attributeValue("value"));
        } else {
            return 0;
        }
    }
    
    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        
        out.println("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            String x = exp.toString();
            
            out.println(x);
            out.println(evaluate(exp.child(0)));
            out.println("");
            out.println("Enter the name of an expression XMLTree file: ");
            file = in.nextLine();
        }
        in.close();
        out.close();
    }
}