package reasoner;

import org.junit.Test;
import reasoner.expressions.*;

import java.util.Set;

import static junit.framework.Assert.assertTrue;

public class ParserTest {

  @Test
  public void BritishgotoBar() {
    Parser parser = new Parser();
    parser.parseExpression("British subset exists goto dot not park union forall goto dot not library");
    parser.parseExpression("John subset rich OR NOT poor AND ");
    parser.parseExpression("British subset exists goto dot school");
  }

  @Test
  public void equality() {
    Parser parser = new Parser();
    parser.parseExpression("British subset lessthanorequalto 2 dot goto");
  }

  @Test
  public void testNot() {
    Parser parser = new Parser();
    parser.parseExpression("British subset not poor");
  }

  @Test
  public void FATTEST() {
    Parser parser = new Parser();
    parser.parseExpression("British subset exists goto dot not bar intersect greaterthanorequalto 2 dot goto union rich");
    parser.parseExpression("British subset not rich");
    parser.parseExpression("John equivalent British");
  }

  @Test
  public void testJohnSubsetCarryNotBooks() {
    Parser parser = new Parser();
    parser.parseExpression("JOHN subset BRITISH");
    parser.parseExpression("BRITISH subset exists carry dot not BOOKS");

    SubsumptionEquivalence query = parser.parseQuery("JOHN subset exists carry dot not BOOKS");
    Reasoner reasoner = new Reasoner(parser.getTBox(), query);
    assertTrue(reasoner.queryIsValid());
  }

  public static void assertAnswer(String query, boolean lhs, boolean rhs) {
    if (lhs != rhs) {
      throw new RuntimeException("Invalid answer for: " + query);
    }
  }

  @Test
  public void testRichMan() {
    Parser parser = new Parser();
    parser.parseExpression("RICH_MAN equivalent RICH intersect MAN");

    TBox tbox = parser.getTBox();
    ConceptExpression man = new ConceptExpression("MAN");
    ConceptExpression rich = new ConceptExpression("RICH");
    IntersectExpression richAndMan = new IntersectExpression(rich, man);
    ConceptExpression richMan = new ConceptExpression("RICH_MAN");

    Set<Expression> richManIsEqualToExpressions = tbox.get(richMan);
    assert (richManIsEqualToExpressions.size() == 1);
//    assert (richManIsEqualToExpressions.contains(richAndMan));
  }

  @Test
  public void testRichWoman() {
    Parser parser = new Parser();
    parser.parseExpression("RICH_WOMAN equivalent RICH intersect WOMAN");
    TBox tbox = parser.getTBox();
    ConceptExpression woman = new ConceptExpression("WOMAN");
    ConceptExpression rich = new ConceptExpression("RICH");
    IntersectExpression richAndWoman = new IntersectExpression(rich, woman);
    ConceptExpression richWoman = new ConceptExpression("RICH_WOMAN");

    Set<Expression> richWomanIsEqualToExpressions = tbox.get(richWoman);
    assert (richWomanIsEqualToExpressions.size() == 1);
    assert (richWomanIsEqualToExpressions.contains(richAndWoman));
  }

  @Test
  public void testcowissubsetofAnimal() {
    Parser parser = new Parser();
    parser.parseExpression("COW subset ANIMAL");
    TBox tbox = parser.getTBox();
    ConceptExpression cow = new ConceptExpression("COW");
    ConceptExpression animal = new ConceptExpression("ANIMAL");
    Set<Expression> cowsubsetExp = tbox.get(cow);
    assert (cowsubsetExp.size() == 1);
    assert (cowsubsetExp.contains(animal));
  }

  @Test
  public void testStudents() {
    Parser parser = new Parser();
    parser.parseExpression("STUDENTS equivalent UNDERGRAD intersect GRAD");
    TBox tbox = parser.getTBox();
    ConceptExpression undergrad = new ConceptExpression("UNDERGRAD");
    ConceptExpression grad = new ConceptExpression("GRAD");
    IntersectExpression undergradandgrad = new IntersectExpression(undergrad, grad);
    ConceptExpression students = new ConceptExpression("STUDENTS");
    Set<Expression> studentsequivalentExp = tbox.get(students);
    assert (studentsequivalentExp.size() == 1);
//    assert (studentsequivalentExp.contains(undergradandgrad));
  }

  @Test
  public void fluffyeatsKibbles() {
    Parser parser = new Parser();
    parser.parseExpression("FLUFFY eats KIBBLES");
    TBox tbox = parser.getTBox();
    ConceptExpression fluffy = new ConceptExpression("FLUFFY");
    ConceptExpression kibbles = new ConceptExpression("KIBBLES");
    ExistentialExpression exst = new ExistentialExpression("eats", kibbles);

    Set<Expression> studentsequivalentExp = tbox.get(exst);
//    assert (studentsequivalentExp.size() == 1);
//    assert (studentsequivalentExp.contains(fluffy));
  }

  @Test
  public void fluffylikesfish() {
    Parser parser = new Parser();
    parser.parseExpression("FLUFFY likes FISH");
    TBox tbox = parser.getTBox();
    ConceptExpression fluffy = new ConceptExpression("FLUFFY");
    ConceptExpression fish = new ConceptExpression("FISH");
    ExistentialExpression exst = new ExistentialExpression("likes", fish);

    Set<Expression> studentsequivalentExp = tbox.get(exst);
//    assert (studentsequivalentExp.size() == 1);
//    assert (studentsequivalentExp.contains(fluffy));
  }

  @Test
  public void fluffylikesGuacomole() {
    Parser parser = new Parser();
    parser.parseExpression("FLUFFY likes GUACOMOLE");
    TBox tbox = parser.getTBox();
    ConceptExpression fluffy = new ConceptExpression("FLUFFY");
    ConceptExpression guacomole = new ConceptExpression("GUACOMOLE");
    ExistentialExpression exst = new ExistentialExpression("likes",
        guacomole);

    Set<Expression> studentsequivalentExp = tbox.get(exst);
//    assert (studentsequivalentExp.size() == 1);
//    assert (studentsequivalentExp.contains(fluffy));
  }

  @Test
  public void JohneatsonlyFruits() {
    Parser parser = new Parser();
    parser.parseExpression("JOHN eats only FRUITS");
    TBox tBox = new TBox();
    ConceptExpression john = new ConceptExpression("JOHN");
    ConceptExpression fruits = new ConceptExpression("FRUITS");
    UniversalExpression uni = new UniversalExpression("eats", fruits);
    Set<Expression> subExpressions = tBox.get(uni);

//    assert (subExpressions.size() == 1);
//    assert (subExpressions.contains(john));
  }
}