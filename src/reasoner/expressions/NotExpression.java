package reasoner.expressions;

/**
 * NotExpression represents the not expression in description logic. 
 *
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public class NotExpression extends Expression {
  public Expression expression;

 /**
  * Returns NotExpression that contains the expression to be negated
  *
  * @param expression the expression to be negated
  * @return           the NotExpression that holds the expression to be negated
  */
  public NotExpression(Expression expression) {
    this.expression = expression;
  }

 /**
  * Returns whether two expressions contradict each other  
  *
  * @param other the other expression to test whether this expression and the 
  *              other expression contradicts
  * @return      whether the two expressions contradicts
  */
  @Override
  public boolean contradicts(Expression other) {
    return expression.equals(other);
  }

 /**
  * Returns whether existential expressions are equal each other  
  *
  * @param other the other expression to test whether this expression and the 
  *              other expression are equal
  * @return      whether the two expressions are equal
  */
  @Override
  public boolean equals(Object other) {
    if (other instanceof NotExpression) {
      return expression.equals(((NotExpression) other).expression);
    }
    return super.equals(other);
  }

 /**
  * Returns the not expression as a String
  *
  * @return the not expression as a String
  */
  @Override
  public String toString() {
    return String.format("NOT(%s)", expression);
  }
}
