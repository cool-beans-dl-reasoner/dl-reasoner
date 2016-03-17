package reasoner.expressions;

/**
 * NotExpression represents the negation expression in description logic.
 *
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public class NotExpression extends Expression {
  public Expression expression;

 /**
  * Constructs a new NotExpression object.
  * The alternative static {@method negateExpression} is recommended over this
  * constructor.
  *
  * @param expression the expression to be negated
  */
  public NotExpression(Expression expression) {
    this.expression = expression;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean contradicts(Expression other) {
    return expression.equals(other);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return -1 * expression.hashCode();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof NotExpression) {
      return expression.equals(((NotExpression) other).expression);
    }
    return super.equals(other);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return String.format("NOT(%s)", expression);
  }


  /**
   * Static helper method to negate an expression.
   *
   * @param expression The expression to negate.
   * @return The negated expression.
   */
  public static Expression negateExpression(Expression expression) {
    if (expression instanceof NotExpression) {
      return ((NotExpression) expression).expression;
    } else {
      return new NotExpression(expression);
    }
  }
}
