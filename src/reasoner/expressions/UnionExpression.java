package reasoner.expressions;

/**
 * UnionExpression represents the union (or) expression in description logic.
 *
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public class UnionExpression extends IntersectUnionExpression {

 /**
  * Constructs a new UnionExpression of the two expressions.
  *
  * @param lhs the expression on the left-hand side of the union
  * @param rhs the expression on the right-hand side of the union
  */
  public UnionExpression(Expression lhs, Expression rhs) {
    super(lhs, rhs);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return lhs.toString() + " OR " + rhs.toString();
  }
}
