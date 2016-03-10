package reasoner;

import org.junit.Test;
import reasoner.expressions.*;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

public class ReasonerTest {
  public static void assertAnswer(String query, boolean lhs, boolean rhs) {
    if (lhs != rhs) {
      throw new RuntimeException("Invalid answer for: " + query);
    }
  }

  @Test
  public void testManIsSubsetOfPerson() {
    ConceptExpression man = new ConceptExpression("MAN");
    ConceptExpression person = new ConceptExpression("PERSON");

    // Man is a subset of Person
    Set<Expression> manExpressions = new HashSet<>();
    manExpressions.add(person);
    TBox tbox = new TBox();
    tbox.put(man, manExpressions);

    // Is man a subset of Person? Remember to negate the D.
    SubsumptionEquivalence query = new SubsumptionEquivalence(man, new NotExpression(person));

    Reasoner reasoner = new Reasoner(tbox, query);
    assertAnswer("Is man a subset of person?", true, reasoner.queryIsValid());
    assertTrue(reasoner.queryIsValid());
  }

  @Test
  public void testAnimalSubsetOfPerson() {
    ConceptExpression animal = new ConceptExpression("ANIMAL");
    ConceptExpression person = new ConceptExpression("PERSON");

    // Tbox has no knowledge
    TBox tbox = new TBox();

    // Is animal a subset of Person? Remember to negate the D.
    SubsumptionEquivalence query = new SubsumptionEquivalence(animal, new NotExpression(person));

    Reasoner reasoner = new Reasoner(tbox, query);
    assertAnswer("Is animal a subset of person?", false, reasoner.queryIsValid());
    assertFalse(reasoner.queryIsValid());
  }

  @Test
  public void testRichMan() {
    TBox tbox = new TBox();
    ConceptExpression man = new ConceptExpression("MAN");
    ConceptExpression rich = new ConceptExpression("RICH");
    AndExpression richAndMan = new AndExpression(rich, man);
    ConceptExpression richMan = new ConceptExpression("RICH_MAN");

    // Richman is equal to richAndMan
    Set<Expression> richManExpressions = new HashSet<>();
    richManExpressions.add(richAndMan);
    tbox.put(richMan, richManExpressions);

    SubsumptionEquivalence query = new SubsumptionEquivalence(richMan, new NotExpression(rich));
    Reasoner reasoner = new Reasoner(tbox, query);
    assertAnswer("Is richMan a subset of richAndMan?", true, reasoner.queryIsValid());
    assertTrue(reasoner.queryIsValid());
  }

  @Test
  public void testRichWoman() {
    TBox tbox = new TBox();
    ConceptExpression man = new ConceptExpression("MAN");
    ConceptExpression rich = new ConceptExpression("RICH");
    AndExpression richAndMan = new AndExpression(rich, man);
    ConceptExpression richMan = new ConceptExpression("RICH_MAN");
    // Richman is equal to richAndMan
    Set<Expression> richManExpressions = new HashSet<>();
    richManExpressions.add(richAndMan);
    tbox.put(richMan, richManExpressions);

    ConceptExpression richWoman = new ConceptExpression("RICH_WOMAN");
    AndExpression richAndNotRichMan = new AndExpression(rich, new NotExpression(richAndMan));
    // Richwoman is equal to rich and not richMan
    Set<Expression> richWomanExpressions = new HashSet<>();
    richWomanExpressions.add(richAndNotRichMan);
    tbox.put(richWoman, richWomanExpressions);

    SubsumptionEquivalence query = new SubsumptionEquivalence(richWoman, new NotExpression(rich));
    Reasoner reasoner = new Reasoner(tbox, query);
    assertAnswer("Is richWoman a subset of rich?", true, reasoner.queryIsValid());

    query = new SubsumptionEquivalence(richWoman, new NotExpression(man));
    reasoner = new Reasoner(tbox, query);
    assertAnswer("Is richWoman a subset of man?", false, reasoner.queryIsValid());
    assertFalse(reasoner.queryIsValid());
  }

  @Test
  public void testJohnGoesToBar() throws Exception {
    ConceptExpression john = new ConceptExpression("JOHN");
    ExistentialExpression goToBar = new ExistentialExpression("goTo", new ConceptExpression("BAR"));

    Set<Expression> johnExpressions = new HashSet<>();
    johnExpressions.add(goToBar);

    TBox tbox = new TBox();
    tbox.put(john, johnExpressions);

    SubsumptionEquivalence query = new SubsumptionEquivalence(john, new NotExpression(goToBar));
    Reasoner reasoner = new Reasoner(tbox, query);
    assertAnswer("Does John go to the bar?", true, reasoner.queryIsValid());
    query = new SubsumptionEquivalence(john, goToBar);
    reasoner = new Reasoner(tbox, query);
    assertAnswer("Does John not go to the bar?", false, reasoner.queryIsValid());
  }

  @Test
  public void testJohnGoesToPub() throws Exception {
    ConceptExpression john = new ConceptExpression("JOHN");
    ConceptExpression bar = new ConceptExpression("BAR");
    ConceptExpression pub = new ConceptExpression("PUB");

    ExistentialExpression goToBar = new ExistentialExpression("goTo", bar);
    ExistentialExpression goToPub = new ExistentialExpression("goTo", pub);

    TBox tbox = new TBox();
    Set<Expression> johnExpressions = new HashSet<>();
    johnExpressions.add(goToBar);
    tbox.put(john, johnExpressions);

    Set<Expression> barExpressions = new HashSet<>();
    barExpressions.add(pub);
    tbox.put(bar, barExpressions);

    Set<Expression> pubExpressions = new HashSet<>();
    pubExpressions.add(bar);
    tbox.put(pub, pubExpressions);

    SubsumptionEquivalence query = new SubsumptionEquivalence(john, new NotExpression(goToPub));
    Reasoner reasoner = new Reasoner(tbox, query);
    assertAnswer("Does John go to the pub?", true, reasoner.queryIsValid());
  }
}