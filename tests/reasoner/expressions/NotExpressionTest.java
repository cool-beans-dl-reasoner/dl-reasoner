package reasoner.expressions;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

public class NotExpressionTest {

  @Test
  public void testContradicts() throws Exception {
    ConceptExpression rich = new ConceptExpression("RICH");
    NotExpression notRich = new NotExpression(rich);
    NotExpression notRich2 = new NotExpression(new ConceptExpression("RICH"));

    assertFalse(notRich.contradicts(notRich));
    assertFalse(notRich.contradicts(notRich2));
    assertFalse(notRich2.contradicts(notRich));

    assertTrue(rich.contradicts(notRich));
    assertTrue(notRich.contradicts(rich));
    assertTrue(notRich2.contradicts(rich));
    assertTrue(rich.contradicts(notRich2));
  }
}