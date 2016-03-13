package reasoner.expressions;

/**
 * TODO: Create superclass for UnionExpression and AndExpression.
 */
public class UnionExpression extends Expression {
  public Expression lhs;
  public Expression rhs;

  public UnionExpression(Expression lhs, Expression rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  @Override
  public String toString() {
    return lhs.toString() + " AND " + rhs.toString();
  }

  @Override
  public boolean contradicts(Expression other) {
    if (other instanceof NotExpression) {
      return other.contradicts(lhs) || other.contradicts(rhs);
    }
    return super.contradicts(other);
  }
}
