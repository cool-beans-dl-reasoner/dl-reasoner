package reasoner.expressions;

public class IntersectExpression extends IntersectUnionExpression {
  public IntersectExpression(Expression lhs, Expression rhs) {
    super(lhs, rhs);
  }

  @Override
  public String toString() {
    return lhs.toString() + " AND " + rhs.toString();
  }
}
