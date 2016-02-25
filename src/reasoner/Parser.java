package reasoner;

import reasoner.expressions.ConceptExpression;
import reasoner.expressions.Expression;

import java.util.HashSet;
import java.util.Set;

public class Parser {

  TBox tbox;

  public Parser() {
    tbox = new TBox();
  }

  public void parseLine(String line) {
    String[] items = line.split(" ");

    Expression c = new ConceptExpression(items[0]);
    Set<Expression> d = new HashSet<>();
    // TODO: Sam fill up d.

    Set<Expression> allD = tbox.get(c);
    if (allD == null) { // C is not in the TBox
      allD = new HashSet<>();
    }
    allD.addAll(d);
    tbox.put(c, allD);
    System.out.println(tbox);
  }

  public TBox getTBox() {
    return this.tbox;
  }
}
