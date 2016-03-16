package reasoner.expressions;

import org.junit.Test;
import reasoner.Subsumption;
import reasoner.TBox;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GreaterThanOrEqualToExpressionTest {

  @Test
  public void testContradicts() throws Exception {
    Expression john = new ConceptExpression("JOHN");
    Expression goToBar = new ExistentialExpression("goTo", new ConceptExpression("BAR"));
    Expression goToSchool = new ExistentialExpression("goTo", new ConceptExpression("SCHOOL"));

    Subsumption johnGoToBar = new Subsumption(john, goToBar);
    Subsumption johnGoToSchool = new Subsumption(john, goToSchool);

    TBox tbox = new TBox();
    tbox.add(johnGoToBar);
    tbox.add(johnGoToSchool);
    System.out.println(tbox);

    NumberRestrictionExpression goToAtLeastOneThing = new GreaterThanOrEqualToExpression("goTo", 1);
    assertTrue(goToAtLeastOneThing.contradicts(tbox.get(john)));

    LessThanOrEqualToExpression goToAtMostThreeThings = new LessThanOrEqualToExpression("goTo", 3);
    assertTrue(goToAtMostThreeThings.contradicts(tbox.get(john)));

    NumberRestrictionExpression goToAtLeastThreeThings = new GreaterThanOrEqualToExpression("goTo", 3);
    assertFalse(goToAtLeastThreeThings.contradicts(tbox.get(john)));
  }
}