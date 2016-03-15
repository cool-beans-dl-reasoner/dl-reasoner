package reasoner;

import reasoner.tests.ParserTest;
import reasoner.tests.ReasonerTest;

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
	 * The only method in class Main that runs the test cases for the parser
	 * and the reasoner
	 * 
	 * @param args the arguments that can be passed in through command line
	 */
	public static void main(String[] args) {
		ReasonerTest.testManIsSubsetOfPerson();
		ReasonerTest.testAnimalSubsetOfPerson();
		ReasonerTest.testRichMan();
		ReasonerTest.teststudents();
		ReasonerTest.fluffyeatsKibbles();
		ReasonerTest.fluffylikesfish();
		ReasonerTest.fluffylikesGuacomole();
		ReasonerTest.testRichWoman();
		ReasonerTest.BritishgotoBar();
		ReasonerTest.JohnlikesIcecreams();
		ReasonerTest.BritishersCarryUmbrella();
		ReasonerTest.testjohnIsSubsetofBritish();
		ReasonerTest.testRichWoman();
		ReasonerTest.JohneatsonlyFruits();

		ParserTest.testRichMan();
		ParserTest.testRichWoman();
		ParserTest.testStudents();
		ParserTest.testcowissubsetofAnimal();

		ParserTest.fluffyeatsKibbles();
		ParserTest.fluffylikesfish();
		ParserTest.fluffylikesGuacomole();
		ParserTest.BritishgotoBar();
		ParserTest.JohneatsonlyFruits();
		ReasonerTest.teststudentswithunion();
		ReasonerTest.testGreaterthanequal();
		ReasonerTest.testlessthanequal();
	}
}
