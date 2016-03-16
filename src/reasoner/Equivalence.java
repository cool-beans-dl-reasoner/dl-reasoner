package reasoner;

import reasoner.expressions.Expression;

/**
 * Same functionality as {@class SubsumptionEquivalence} but needs to be handled separately
 * with regard to the {@class TBox}.
 *
 * The TBox for "RICH_MAN equivalent MAN and RICH" should look like:
 * RICH_MAN => [MAN and RICH]
 * MAN and rich => RICH_MAN
 *
 * Whereas TBox for "RICH_MAN subsumption MAN and RICH" should look like:
 * RICH_MAN => [MAN and RICH]
 *
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public class Equivalence extends SubsumptionEquivalence {

  /**
   * Constructs a new Equivalence object that says the lhs expression is equal to the rhs expression.
   *
   * For example: new Equivalence(new ConceptExpression("JOHN"), new ConceptExpression("JON"));
   * would create an equivalence that says that JOHN and JON are the same thing.
   *
   * @param lhs the lhs expression
   * @param rhs the expression that the lhs is equal to
   */
  public Equivalence(Expression lhs, Expression rhs) {
    super(lhs, rhs);
  }
}
