package reasoner.expressions;

public class NotExpression extends Expression {
  public Expression expression;

  public NotExpression(Expression expression) {
    this.expression = expression;
  }

  @Override
  public boolean contradicts(Expression other) {
    return expression.equals(other);
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof NotExpression) {
      return expression.equals(((NotExpression) other).expression);
    }
    return super.equals(other);
  }

  @Override
  public String toString() {
    return String.format("NOT(%s)", expression);
  }
}
