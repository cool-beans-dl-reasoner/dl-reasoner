package reasoner;

import reasoner.expressions.Expression;

/**
 * SubsumptionEquivalence class is used to declare two Expressions as being either
 * a subsumption or equivalent.
 *
 * Subsumption example: RICH_MAN subsumption PEOPLE
 * This declares that RICH_MAN is a subset of PEOPLE.
 *
 * Equivlanece example: RICH_MAN equivalent RICH and MAN
 * This declares that RICH_MAN is equal to the set of RICH intersect set of MAN.
 *
 * @see {@class Equivalence}.
 */
public class SubsumptionEquivalence {

  Expression lhs;
  Expression rhs;

  public SubsumptionEquivalence(Expression lhs, Expression rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }
}
