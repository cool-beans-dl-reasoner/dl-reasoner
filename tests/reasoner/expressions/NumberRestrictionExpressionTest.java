package reasoner.expressions;

import org.junit.Test;
import reasoner.Reasoner;
import reasoner.Subsumption;
import reasoner.TBox;

import static org.junit.Assert.assertTrue;

public class NumberRestrictionExpressionTest {

  @Test
  public void testReasonerHandling() {
    Expression john = new ConceptExpression("JOHN");
    Expression goToBar = new ExistentialExpression("goTo", new ConceptExpression("BAR"));
    Expression goToSchool = new ExistentialExpression("goTo", new ConceptExpression("SCHOOL"));

    Subsumption johnGoToBar = new Subsumption(john, goToBar);
    Subsumption johnGoToSchool = new Subsumption(john, goToSchool);

    TBox tbox = new TBox();
    tbox.add(johnGoToBar);
    tbox.add(johnGoToSchool);

    // Note that these are "opposite" for the subsumption
    Expression goToAtMostOneThing = new GreaterThanOrEqualToExpression("goTo", 1);
    Subsumption queryDoesJohnGoToAtMostOneThing = new Subsumption(john, goToAtMostOneThing);
    Reasoner reasoner = new Reasoner(tbox, queryDoesJohnGoToAtMostOneThing);
    assertTrue(reasoner.queryIsValid());

    // Note that these are "opposite" for the subsumption
    Expression goToAtLeastOneThing = new LessThanOrEqualToExpression("goTo", 1);
    Subsumption queryDoesJohnGoToAtLeastOneThing = new Subsumption(john, goToAtLeastOneThing);
    Reasoner reasoner2 = new Reasoner(tbox, queryDoesJohnGoToAtLeastOneThing);
    assertTrue(reasoner2.queryIsValid());
  }
}