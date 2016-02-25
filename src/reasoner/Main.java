package reasoner;

import reasoner.tests.ParserTest;
import reasoner.tests.ReasonerTest;

public class Main {
  public static void main(String[] args) {
    ReasonerTest.testManIsSubsetOfPerson();
    ReasonerTest.testRichMan();
    ReasonerTest.testAnimalSubsetOfPerson();
    ReasonerTest.testRichWoman();

    ParserTest.testRichMan();
  }
}
