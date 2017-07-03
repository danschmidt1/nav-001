package navicure.nav_001;

import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }
    
    public void testParagraph() {
    	System.out.println("testParagraph\n");
    	try {
			App.doParagraphQuestion("Paragraph.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void testPrimes()
    {
    	System.out.println("\ntestPrimes\n");
        try {
			App.doPrimeNumberQuestion("PrimeNumberFile.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void testBigPrimes()
    {
    	System.out.println("\nTestBigPrimes\n");
        try {
			App.doPrimeNumberQuestion("PrimeNumberCrazyBigFile.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
