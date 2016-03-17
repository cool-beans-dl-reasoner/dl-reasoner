package reasoner.expressions;

/**
 * IntersectUnionExpression is the abstract class that 
 * {@class IntersectExpression} and {@class UnionExpression} will inherit from.
 * 
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public class IntersectUnionExpression extends Expression {
  public Expression lhs;
  public Expression rhs;

 /**
  * Returns IntersectUnionExpression that represent the leff-hand and right-hand
  * expressions
  *
  * @param lhs the expression on the left-hand side of the dot
  * @param rhs the expression on the right-hand side of the dot
  * @return        the ExistentialExpression that represents the concept
  */
  public IntersectUnionExpression(Expression lhs, Expression rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

 /**
  * Returns whether two expressions contradict each other.  
  *
  * @param other the other expression to test whether this expression and the 
  *              other expression contradicts
  * @return      whether the two expressions contradicts
  */
  @Override
  public boolean contradicts(Expression other) {
    if (other instanceof NotExpression) {
      return other.contradicts(lhs) || other.contradicts(rhs);
    }
    return super.contradicts(other);
  }

  /**
   * {@inheritDoc}
   */
  public boolean equals(Object other) {
    if (other instanceof IntersectUnionExpression) {
      return lhs.equals(((IntersectUnionExpression) other).lhs)
          && rhs.equals(((IntersectUnionExpression) other).rhs);
    }
    return super.equals(other);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return lhs.hashCode() + rhs.hashCode();
  }
}
