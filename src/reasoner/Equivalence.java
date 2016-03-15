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
  * Returns Equivalence that represent the left-hand and right-hand
  * expressions
  *
  * @param lhs the expression on the left-hand side of the dot
  * @param rhs the expression on the right-hand side of the dot
  * @return        the Equivalence that represents the concept
  */
  public Equivalence(Expression lhs, Expression rhs) {
    super(lhs, rhs);
  }
}
