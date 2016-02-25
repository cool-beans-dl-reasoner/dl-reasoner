package reasoner.tests;

import reasoner.Parser;
import reasoner.TBox;
import reasoner.expressions.AndExpression;
import reasoner.expressions.ConceptExpression;
import reasoner.expressions.Expression;

import java.util.Set;

public class ParserTest {

  public static void assertAnswer(String query, boolean lhs, boolean rhs) {
    if (lhs != rhs) {
      throw new RuntimeException("Invalid answer for: " + query);
    }
  }

  public static void testRichMan() {
    Parser parser = new Parser();
    parser.parseLine("RICH_MAN equivalent RICH intersect MAN");

    TBox tbox = parser.getTBox();
    ConceptExpression man = new ConceptExpression("MAN");
    ConceptExpression rich = new ConceptExpression("RICH");
    AndExpression richAndMan = new AndExpression(rich, man);
    ConceptExpression richMan = new ConceptExpression("RICH_MAN");

    Set<Expression> richManIsEqualToExpressions = tbox.get(richMan);
    assert(richManIsEqualToExpressions.size() == 1);
    assert(richManIsEqualToExpressions.contains(richAndMan));
  }
}
