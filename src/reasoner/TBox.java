package reasoner;

import reasoner.expressions.Expression;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * TBox contains all the expressions connected to a single expression.
 * 
 * Example Usage:
 * "John is rich"
 * "John is British"
 * The TBox will contain "John" and a set of expressions "rich" and "British"
 * 
 * @author Brian Fung
 * @author Jon Miranda
 * @author Sravani Mudduluru
 * @author Siavash Rezaie
 */
public class TBox extends HashMap<Expression, Set<Expression>> {

  private Set<Expression> getRhsOrNewSet(Expression key) {
    Set<Expression> rhs = get(key);
    if (rhs == null) {
      rhs = new HashSet<>();
    }
    return rhs;
  }

  /**
   * Adds a new Subsumption or Equivalence to the TBox.
   *
   * If adding "JOHN subsumption BRITISH", then the TBox will have JOHN => BRITISH.
   * However, if adding "JOHN equivalent BRITISH", then the TBox will also have BRITISH => JOHN.
   * @param subsumptionEquivalence The new subsumption or equivalence to add.
   */
  public void add(SubsumptionEquivalence subsumptionEquivalence) {
    Expression lhs = subsumptionEquivalence.lhs;
    Expression rhs = subsumptionEquivalence.rhs;

    Set<Expression> currentRhs = getRhsOrNewSet(lhs);
    currentRhs.add(rhs);
    put(lhs, currentRhs);

    if (subsumptionEquivalence instanceof Equivalence) {
      Set<Expression> currentLhs = getRhsOrNewSet(rhs);
      currentLhs.add(lhs);
      put(rhs, currentLhs);
    }
  }

 /**
  * {@inheritDoc}
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("========== TBOX START ==========\n");
    for (Map.Entry<Expression, Set<Expression>> values : this.entrySet()) {
      sb.append(values.getKey());
      sb.append("={");
      for (Expression e : values.getValue()) {
        sb.append(e);
        sb.append(",");
      }
      sb.deleteCharAt(sb.length() - 1);
      sb.append("}\n");
    }
    sb.append("========== TBOX END ==========\n");
    return sb.toString();
  }
}
