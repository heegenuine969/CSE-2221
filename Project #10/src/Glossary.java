import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * @author Heeji Kim
 */
public final class Glossary {
    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    public static Queue<String> getterms(SimpleReader input,
            Map<String, String> termsDefinitions) {

        Queue<String> terms = new Queue1L<>();

        while (!input.atEOS()) {
            String term = input.nextLine();
            StringBuilder definition = new StringBuilder();
            
            boolean next = false;

            while (!next) {
                String line = input.nextLine();
                if (!line.equals("")) {
                    definition.append(line + " ");
                } else {
                    definition.deleteCharAt(definition.length() - 1);
                    next = true;
                }
            }

            termsDefinitions.add(term, definition.toString());
            terms.enqueue(term);
        }
        return terms;
    }

    public static void TitlePage(SimpleWriter out, Queue<String> terms) {
        out.println("<html>");
        out.println(" <head>");
        out.println("      <title>Glossary</title>");
        out.println(" </head>");
        out.println(" <body>");
        out.println("      <hr>");
        out.println("      <h2>Index</h2>");
        out.println("      <ul>");
        
        for (int i = 0; i < terms.length(); i++) {
            out.println("         <li>");
            out.println("              <a href=\"" + terms.front() + ".html\">"
                    + terms.front() + "</a>");
            out.println("         </li>");
            terms.rotate(1);
        }
        out.println("      </ul>");
        out.println(" </body>");
        out.println("</html>");
    }

    public static void TermPage(SimpleWriter out, String term,
            Map<String, String> termsDefinitions) {
        out.println("<html>");
        out.println(" <head>");
        out.println("      <title>" + term + "</title>");
        out.println(" </head>");
        out.println(" <body>");
        out.println("<h2><b><i><font color=\"red\">" + term
                + "</font></i></b></h2>");

        out.println("<blockquote>" + termsDefinitions.value(term)
                + "</blockquote>");
        out.println("      <hr>");
        out.println("<p>Return to <a href=\"index.html\">index</a>.</p>");
        out.println(" </body>");
        out.println("</html>");
    }

    public static void gettootherDefinition(Queue<String> terms,
            Map<String, String> termsDefinitions) {
        Set<Character> separators = new Set1L<>();
        
        separators.add(',');
        separators.add(' ');
        separators.add('\t');
        separators.add(';');
        separators.add('.');

        int i = 0;
        while (i < terms.length()) {
            String frontterm = terms.front();

            int q = 0;
            while (q < terms.length()) {
                String front = terms.front();
                String definition = termsDefinitions.value(frontterm);

                int position = 0;
                while (position < definition.length()) {
                    String word = nextWordOrSeparator(definition, position,
                            separators);
                    if (word.equals(front)) {
                        definition = definition.substring(0, position)
                                + "<a href=\"" + word + ".html\">" + word
                                + "</a>"
                                + definition.substring(position + word.length(),
                                        definition.length());
                    }
                    position += word.length();
                }
                termsDefinitions.replaceValue(frontterm, definition);
                terms.rotate(1);
                q++;
            }
            terms.rotate(1);
            i++;
        }
    }

    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        int i = 0;
        while (i < separators.size()) {
            char c = separators.removeAny();
            if (!separators.contains(c)) {
                separators.add(c);
            }
            i++;
        }
        int j = position;
        boolean answer = separators.contains(text.charAt(position));
        while (j < text.length()
                && separators.contains(text.charAt(j)) == answer) {
            j++;
        }
        return text.substring(position, j);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {

        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println("Enter input: ");
        String inputFile = in.nextLine();
        out.println("Enter a folder to save output: ");
        String folder = in.nextLine();

        SimpleReader input = new SimpleReader1L(inputFile);
        Map<String, String> termsDefinitions = new Map1L<>();
        Queue<String> terms = getterms(input, termsDefinitions);
        gettootherDefinition(terms, termsDefinitions);

        SimpleWriter output = new SimpleWriter1L(folder + "\\index.html");
        TitlePage(output, terms);

        int length = terms.length();
        for (int i = 0; i < length; i++) {
            String term = terms.dequeue();
            output = new SimpleWriter1L(folder + "\\" + term + ".html");
            TermPage(output, term, termsDefinitions);
        }

        in.close();
        out.close();
        input.close();
        output.close();
    }
}
