import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
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
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private XMLTreeNNExpressionEvaluator() {
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
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null: "Violation of: exp is not null";
        NaturalNumber operand = new NaturalNumber2();
        
        if (exp.numberOfChildren() != 0) {
            if (exp.label().equals("times")) {
                operand = evaluate(exp.child(0));
                operand.multiply(evaluate(exp.child(1)));
            }
            if (exp.label().equals("plus")) {
                operand = evaluate(exp.child(0));
                operand.add(evaluate(exp.child(0)));
            }
            if (exp.label().equals("divide")) {
                operand = evaluate(exp.child(0));
                NaturalNumber operand2 = evaluate(exp.child(1));
                if (operand2.isZero()) {
                    Reporter.fatalErrorToConsole("In divide expression,"
                            + "the second number shouldn't be zero");
                }
                operand.divide(operand2);
            }
            if (exp.label().equals("minus")) {
                operand = evaluate(exp.child(0));
                NaturalNumber operand2 = evaluate(exp.child(1));
                if (operand.compareTo(operand2) < 0) {
                    Reporter.fatalErrorToConsole("In subtraction of NaturalNumber,"
                            + "ensure that the first number is larger than the second one");
                }
                operand.subtract(operand2);
            }
        } else {
            operand.setFromString(exp.attributeValue("value"));
        }
        return operand;
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
            out.println(evaluate(exp.child(0)));
            out.println("Enter the name of an expression XMLTree file: ");
            file = in.nextLine();
        }
        in.close();
        out.close();
    }
}