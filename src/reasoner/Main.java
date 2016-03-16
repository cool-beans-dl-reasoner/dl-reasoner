package reasoner;

import reasoner.tests.ParserTest;

import java.util.Scanner;

/**
 * Main is the class that calls and runs all the test cases for parser 
 * and the reasoner.
 * 
 * The parser takes each line from user input and adds it to the TBox.
 * The reasoner then uses the TBox and sees if the query is true.
 * 
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public class Main {

	/**
	 * Helper method that runs the tests. These tests will be moved to JUnit.
	 */
	public static void runTests() {
    ParserTest.testRichMan();
    ParserTest.testRichWoman();
    ParserTest.testStudents();
    ParserTest.testcowissubsetofAnimal();
    ParserTest.fluffyeatsKibbles();
    ParserTest.fluffylikesfish();
    ParserTest.fluffylikesGuacomole();
    ParserTest.BritishgotoBar();
    ParserTest.JohneatsonlyFruits();
	}

  /**
   * The main driver to interact with our description logic reasoner.
   * Takes in user input, builds a TBox using {@class Parser}, builds the {@class SubsumptionEquivalence} query,
   * and reasons it through the {@class Reasoner}.
   *
   * Outputs the result to System.out.
   *
   * @param args Command line arguments. None need to be specified to run the program.
   */
  public static void main(String[] args) {
    System.out.println("Enter subsumptions or equivalencies below.\n" +
        "When done enter a single period '.'\n" +
        "Refer to our User Manual for instructions and keywords.");
    Scanner input = new Scanner(System.in);

    boolean buildingTBox = true;
    boolean readingQuery = false;

    Parser parser = new Parser();
    // Build TBox with Parser
    while (input.hasNext()) {
      String line = input.nextLine();
      if (line.equals(".")) {
        break;
      }
      parser.parseLine(line);
    }

    // Build Query
    System.out.print("Enter query on a single line: " );
    String line = input.nextLine();
    SubsumptionEquivalence query = null; // parser.parseQuery(line);

    System.out.println("Reasoning...");
    Reasoner reasoner = new Reasoner(parser.tbox, query);
    System.out.println("Result: " +  reasoner.queryIsValid());
  }
}
