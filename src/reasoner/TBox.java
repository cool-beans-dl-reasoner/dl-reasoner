package reasoner;

import reasoner.expressions.Expression;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TBox extends HashMap<Expression, Set<Expression>> {

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<Expression, Set<Expression>> values : this.entrySet()) {
      sb.append(values.getKey());
      sb.append("={");
      for (Expression e : values.getValue()) {
        sb.append(e);
        sb.append(",");
      }
      sb.append("}\n");
    }
    return sb.toString();
  }
}
