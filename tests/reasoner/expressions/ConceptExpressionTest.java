package reasoner.expressions;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ConceptExpressionTest {

  @Test
  public void testContradicts() throws Exception {
    ConceptExpression man = new ConceptExpression("MAN");

    assertTrue(man.contradicts(new NotExpression(man)));
    assertTrue(man.contradicts(new NotExpression(new ConceptExpression("MAN"))));
    assertTrue(new NotExpression(man).contradicts(man));
    assertFalse(man.contradicts(man));
  }

  @Test
  public void testEquals() throws Exception {
    ConceptExpression man = new ConceptExpression("MAN");
    ConceptExpression man2 = new ConceptExpression("MAN");
    assertEquals(man, man2);
  }
}