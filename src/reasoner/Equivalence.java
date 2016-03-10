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
 */
public class Equivalence extends SubsumptionEquivalence {

  public Equivalence(Expression lhs, Expression rhs) {
    super(lhs, rhs);
  }
}
