package reasoner.expressions;

/**
 * Expression is the superclass that all other Expression classes will inherit 
 * from. This holds only one method, contradicts, that all other classes will
 * override. 
 * 
 * Every subclass needs to override contradicts method, equals method, and 
 * hashcode method.
 *
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public abstract class Expression {

 /**
  * Returns whether two expressions contradict each other  
  *
  * @param other the other expression to test whether this expression and the 
  *              other expression contradicts
  * @return      whether the two expressions contradicts
  */
  public boolean contradicts(Expression other) {
    if (other instanceof NotExpression) {
      return other.contradicts(this);
    }
    return false;
    // TODO: Not sure if the above will work for all instances...
//    throw new RuntimeException("contradicts not overrided.");
  }
}
