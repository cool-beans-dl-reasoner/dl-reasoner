package reasoner.expressions;

/**
 * ForAllExpression represents the for all expression in description logic
 *
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public class ForAllExpression extends Expression {
  public Expression lhs;
  public Expression rhs;

 /**
  * Returns ForAllExpressions that represent the leff-hand and right-hand
  * expressions
  *
  * @param lhs the expression on the left-hand side of the dot
  * @param rhs the expression on the right-hand side of the dot
  * @return        the ExistentialExpression that represents the concept
  */
  public ForAllExpression(Expression lhs, Expression rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

 /**
  * Returns the left-hand expression and the right-hand expression as a String
  *
  * @return the left-hand expression and the right-hand expression as a String
  */
  @Override
  public String toString() {
    return lhs.toString() + " AND " + rhs.toString();
  }

 /**
  * Returns whether two concept expressions contradict each other  
  *
  * @param other the other expression to test whether this expression and the 
  *              other expression contradicts
  * @return      whether the two expressions contradicts
  */
  @Override
  public boolean contradicts(Expression other) {
    if (other instanceof ForAllExpression ||
        other instanceof ExistExpression) {
      return other.contradicts(lhs) || other.contradicts(rhs);
    }
    return super.contradicts(other);
  }
}