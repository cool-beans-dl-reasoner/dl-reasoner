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
public class Subsumption extends SubsumptionEquivalence {

 /**
  * Returns Subsumption that contains the left-hand expression and the 
  * right-hand expression split by subsumption or equivalence
  *
  * @param lhs the expression on the left-hand side of the subsumption or 
  *            equivalence
  * @param rhs the expression on the right-hand side of the subsumption or 
  *            equivalence  
  * @return    the Subsumption that contains the left-hand expression and 
  *            the right-hand expression
  */
  public Subsumption(Expression lhs, Expression rhs) {
    super(lhs, rhs);
  }
}
