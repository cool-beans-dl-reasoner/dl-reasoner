package reasoner;

import reasoner.tests.ParserTest;
import reasoner.tests.ReasonerTest;

public class Main {
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
