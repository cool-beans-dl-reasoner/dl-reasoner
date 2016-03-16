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
  * Constructs a new Subsumption object that says the lhs expression is a
  * subsumption of the rhs expression.
  *
  * For example: new Subsumption(new ConceptExpression("JOHN"), new ConceptExpression("BRITISH"));
  * would create a subsumption that says that JOHN is a subset of BRITISH.
  *
  * @param lhs the lhs expression
  * @param rhs the expression that the lhs is a subset of
  */
  public Subsumption(Expression lhs, Expression rhs) {
    super(lhs, rhs);
  }
}
