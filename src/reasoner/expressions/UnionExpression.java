package reasoner.expressions;

/**
 * UnionExpression represents the union (or) expression in description logic
 *
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public class UnionExpression extends IntersectUnionExpression {

 /**
  * Returns UnionExpression that represent the leff-hand and right-hand
  * expressions
  *
  * @param lhs the expression on the left-hand side of the dot
  * @param rhs the expression on the right-hand side of the dot
  * @return        the UnionExpression that represents the concept
  */
  public UnionExpression(Expression lhs, Expression rhs) {
    super(lhs, rhs);
  }

 /**
  * Returns the left-hand expression and the right-hand expression as a String
  *
  * @return the left-hand expression and the right-hand expression as a String
  */
  @Override
  public String toString() {
    return lhs.toString() + " OR " + rhs.toString();
  }
}
