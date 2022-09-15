import static org.junit.Assert.*;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    @Test
    public void combination_Hi_im() {
        String n = new String("Hi");
        String m = new String("im");
        Integer o = new Integer(1);
        assertEquals("Him", StringReassembly.combination(n, m, o));

    }

    @Test
    public void combination_Ohio_hioState() {
        String n = new String("Ohio");
        String m = new String("hioState");
        Integer o = new Integer(3);
        assertEquals("OhioState", StringReassembly.combination(n, m, o));
    }

    @Test
    public void addToSetAvoidingSubstrings_Yes_No() {
        Set<String> n = new Set1L<>();
        n.add("Yes");
        String m = new String("No");
        StringReassembly.addToSetAvoidingSubstrings(n, m);

    }

    @Test
    public void addToSetAvoidingSubstrings_No_No() {
        Set<String> n = new Set1L<>();
        n.add("No");
        String m = new String("No");
        StringReassembly.addToSetAvoidingSubstrings(n, m);

    }

    @Test
    public void overlap_Yes_Ohio() {
        String n = new String("Yes");
        String m = new String("Ohio");
        assertEquals(0, StringReassembly.overlap(n, m));

    }

    @Test
    public void overlap_Yes_state() {
        String n = new String("Yes");
        String m = new String("state");
        assertEquals(1, StringReassembly.overlap(n, m));

    }

    @Test
    public void printWithLineSeparators_1() {
        String n = new String("Yes~~");
        SimpleWriter output = new SimpleWriter1L();
        StringReassembly.printWithLineSeparators(n, output);
    }

    @Test
    public void printWithLineSeparators_2() {
        String n = new String("~~~~~~");
        SimpleWriter output = new SimpleWriter1L();
        StringReassembly.printWithLineSeparators(n, output);

    }

    @Test
    public void linesFromInput_1() {
        SimpleReader input = new SimpleReader1L("1");
        assertEquals(1, StringReassembly.linesFromInput(input));

    }

    @Test
    public void linesFromInput_2() {
        SimpleReader input = new SimpleReader1L("2");
        assertEquals(2, StringReassembly.linesFromInput(input));

    }
}