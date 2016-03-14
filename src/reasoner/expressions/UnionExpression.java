package reasoner.expressions;

public class UnionExpression extends IntersectUnionExpression {

  public UnionExpression(Expression lhs, Expression rhs) {
    super(lhs, rhs);
  }

  @Override
  public String toString() {
    return lhs.toString() + " OR " + rhs.toString();
  }
}
