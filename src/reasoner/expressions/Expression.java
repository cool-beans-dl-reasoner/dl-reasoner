package reasoner.expressions;

/**
 * Every subclass needs to override contradicts method, equals method, and hashcode method.
 */
public abstract class Expression {

  public boolean contradicts(Expression other) {
    if (other instanceof NotExpression) {
      return other.contradicts(this);
    }
    return false;
    // TODO: Not sure if the above will work for all instances...
//    throw new RuntimeException("contradicts not overrided.");
  }
}
