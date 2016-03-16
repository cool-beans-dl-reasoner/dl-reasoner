package reasoner;

import org.junit.Test;
import reasoner.expressions.*;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

public class ReasonerTest {
  public void assertAnswer(String query, boolean lhs, boolean rhs) {
    if (lhs != rhs) {
      throw new RuntimeException("Invalid answer for: " + query);
    }
  }

  @Test
  public void testManIsSubsetOfPerson() {
    ConceptExpression man = new ConceptExpression("MAN");
    ConceptExpression person = new ConceptExpression("PERSON");

    // Man is a subset of Person
    Set<Expression> manExpressions = new HashSet<>();
    manExpressions.add(person);
    TBox tbox = new TBox();
    tbox.put(man, manExpressions);

    // Is man a subset of Person? Remember to negate the D.
    SubsumptionEquivalence query = new SubsumptionEquivalence(man, new NotExpression(person));

    Reasoner reasoner = new Reasoner(tbox, query);
    assertAnswer("Is man a subset of person?", true, reasoner.queryIsValid());
    assertTrue(reasoner.queryIsValid());
  }

  @Test
  public void testAnimalSubsetOfPerson() {
    ConceptExpression animal = new ConceptExpression("ANIMAL");
    ConceptExpression person = new ConceptExpression("PERSON");

    // Tbox has no knowledge
    TBox tbox = new TBox();

    // Is animal a subset of Person? Remember to negate the D.
    SubsumptionEquivalence query = new SubsumptionEquivalence(animal, new NotExpression(person));

    Reasoner reasoner = new Reasoner(tbox, query);
    assertAnswer("Is animal a subset of person?", false, reasoner.queryIsValid());
    assertFalse(reasoner.queryIsValid());
  }

  @Test
  public void testRichMan() {
    
    TBox tbox = new TBox();
    ConceptExpression man = new ConceptExpression("MAN");
    ConceptExpression rich = new ConceptExpression("RICH");
    IntersectExpression richAndMan = new IntersectExpression(rich, man);
    ConceptExpression richMan = new ConceptExpression("RICH_MAN");

    // Richman is equal to richAndMan
    Set<Expression> richManExpressions = new HashSet<>();
    richManExpressions.add(richAndMan);
    tbox.put(richMan, richManExpressions);

    SubsumptionEquivalence query = new SubsumptionEquivalence(richMan, new NotExpression(rich));
    Reasoner reasoner = new Reasoner(tbox, query);
    assertAnswer("Is richMan a subset of richAndMan?", true, reasoner.queryIsValid());
    assertTrue(reasoner.queryIsValid());
  }

  @Test
  public void testRichWoman() {
    TBox tbox = new TBox();
    ConceptExpression man = new ConceptExpression("MAN");
    ConceptExpression rich = new ConceptExpression("RICH");
    IntersectExpression richAndMan = new IntersectExpression(rich, man);
    ConceptExpression richMan = new ConceptExpression("RICH_MAN");
    // Richman is equal to richAndMan
    Set<Expression> richManExpressions = new HashSet<>();
    richManExpressions.add(richAndMan);
    tbox.put(richMan, richManExpressions);

    ConceptExpression richWoman = new ConceptExpression("RICH_WOMAN");
    IntersectExpression richAndNotRichMan = new IntersectExpression(rich, new NotExpression(richAndMan));
    // Richwoman is equal to rich and not richMan
    Set<Expression> richWomanExpressions = new HashSet<>();
    richWomanExpressions.add(richAndNotRichMan);
    tbox.put(richWoman, richWomanExpressions);

    SubsumptionEquivalence query = new SubsumptionEquivalence(richWoman, new NotExpression(rich));
    Reasoner reasoner = new Reasoner(tbox, query);
    assertAnswer("Is richWoman a subset of rich?", true, reasoner.queryIsValid());

    query = new SubsumptionEquivalence(richWoman, new NotExpression(man));
    reasoner = new Reasoner(tbox, query);
    assertAnswer("Is richWoman a subset of man?", false, reasoner.queryIsValid());
    assertFalse(reasoner.queryIsValid());
  }

  @Test
  public void testJohnGoesToBar() throws Exception {
    ConceptExpression john = new ConceptExpression("JOHN");
    ExistentialExpression goToBar = new ExistentialExpression("goTo", new ConceptExpression("BAR"));

    Set<Expression> johnExpressions = new HashSet<>();
    johnExpressions.add(goToBar);

    TBox tbox = new TBox();
    tbox.put(john, johnExpressions);

    SubsumptionEquivalence query = new SubsumptionEquivalence(john, new NotExpression(goToBar));
    Reasoner reasoner = new Reasoner(tbox, query);
    assertAnswer("Does John go to the bar?", true, reasoner.queryIsValid());
    query = new SubsumptionEquivalence(john, goToBar);
    reasoner = new Reasoner(tbox, query);
    assertAnswer("Does John not go to the bar?", false, reasoner.queryIsValid());
  }

  @Test
  public void testJohnGoesToPub() throws Exception {
    ConceptExpression john = new ConceptExpression("JOHN");
    ConceptExpression bar = new ConceptExpression("BAR");
    ConceptExpression pub = new ConceptExpression("PUB");

    ExistentialExpression goToBar = new ExistentialExpression("goTo", bar);
    ExistentialExpression goToPub = new ExistentialExpression("goTo", pub);

    TBox tbox = new TBox();
    Set<Expression> johnExpressions = new HashSet<>();
    johnExpressions.add(goToBar);
    tbox.put(john, johnExpressions);

    Set<Expression> barExpressions = new HashSet<>();
    barExpressions.add(pub);
    tbox.put(bar, barExpressions);

    Set<Expression> pubExpressions = new HashSet<>();
    pubExpressions.add(bar);
    tbox.put(pub, pubExpressions);

    SubsumptionEquivalence query = new SubsumptionEquivalence(john, new NotExpression(goToPub));
    Reasoner reasoner = new Reasoner(tbox, query);
    assertAnswer("Does John go to the pub?", true, reasoner.queryIsValid());
  }

  @Test
  public void testAssignmentSevenQuestion3() {
    Expression john = new ConceptExpression("JOHN");
    Expression british = new ConceptExpression("BRITISH");
    Expression talksAboutWeather = new ExistentialExpression("talksAbout", new ConceptExpression("WEATHER"));
    Expression carriesUmbrella = new ExistentialExpression("carries", new ConceptExpression("UMBRELLA"));
    Expression allEatsNotIceCream = new UniversalExpression("eats", new NotExpression(new ConceptExpression("ICE_CREAM")));
    Expression eatsPistachios = new ExistentialExpression("eats", new ConceptExpression("PISTACHIOS"));
    Expression pistachios = new ConceptExpression("PISTACHIOS");
    Expression fruit = new ConceptExpression("FRUIT");
    Expression nut = new ConceptExpression("NUT");
    Expression killer = new ConceptExpression("KILLER");
    Expression allergicToNut = new ExistentialExpression("allgericTo", nut);

    Subsumption johnIsBritish = new Subsumption(john, british);
    Subsumption britishTalksAboutWeather = new Subsumption(british, talksAboutWeather);
    Subsumption britishCarriesUmbrella = new Subsumption(british, carriesUmbrella);
    Subsumption johnAllEatsNotIceCream = new Subsumption(john, allEatsNotIceCream);
    Subsumption johnEatsPistachios = new Subsumption(john, eatsPistachios);
    Subsumption pistachiosSubsetNotFruit = new Subsumption(pistachios, new NotExpression(fruit));
    Subsumption pistachiosSubsetNut = new Subsumption(pistachios, nut);
    Subsumption killerAllergicToNut = new Subsumption(killer, allergicToNut);
    Subsumption killerSubsetBritish = new Subsumption(killer, british);

    TBox tbox = new TBox();
    tbox.add(johnIsBritish);
    tbox.add(britishTalksAboutWeather);
    tbox.add(britishCarriesUmbrella);
    tbox.add(johnAllEatsNotIceCream);
    tbox.add(johnEatsPistachios);
    tbox.add(pistachiosSubsetNut);
    tbox.add(killerAllergicToNut);
    tbox.add(killerSubsetBritish);

    SubsumptionEquivalence[] queries = new SubsumptionEquivalence[] {
        johnIsBritish,
        britishTalksAboutWeather,
        britishCarriesUmbrella,
        johnAllEatsNotIceCream,
        johnEatsPistachios,
        pistachiosSubsetNut,
        killerAllergicToNut,
        killerSubsetBritish,
    };

    // Test that everything we put in the Tbox is true.
    for (SubsumptionEquivalence query : queries) {
      Reasoner reasoner = new Reasoner(tbox, query.negateRhs());
      assertTrue(reasoner.queryIsValid());
    }
  }

  @Test
  public void teststudents() {
    TBox tbox = new TBox();
    ConceptExpression undergrad = new ConceptExpression(
        "UndergraduateStudents");
    ConceptExpression grad = new ConceptExpression("GraduateStudents");
    UnionExpression undergradandgrad = new UnionExpression(undergrad, grad);
    ConceptExpression students = new ConceptExpression("Students");

    // Students are equal to undergrad and grad students
    Set<Expression> studentsExpressions = new HashSet<>();
    studentsExpressions.add(undergradandgrad);
    tbox.put(students, studentsExpressions);

    SubsumptionEquivalence query = new SubsumptionEquivalence(students,
        new NotExpression(undergradandgrad));
    Reasoner reasoner = new Reasoner(tbox, query);
    assertAnswer("Are undergrad and grad students subset of students?",
        true, reasoner.queryIsValid());

  }

  @Test
  public void fluffyeatsKibbles() {
    TBox tBox = new TBox();
    //Fluffy eats Kibbles is true?
    ConceptExpression fluffy = new ConceptExpression("Fluffy");
    ConceptExpression kibbles = new ConceptExpression("Kibbles");
    ExistentialExpression exst = new ExistentialExpression("eats", kibbles);
    Set<Expression> subExpressions = new HashSet<>();
    subExpressions.add(exst);
    tBox.put(fluffy, subExpressions);
    SubsumptionEquivalence query = new SubsumptionEquivalence(fluffy,
        new NotExpression(exst));
    Reasoner reasoner = new Reasoner(tBox, query);
    assertAnswer("Fluffy eats Kibbles?", true, reasoner.queryIsValid());

  }

  @Test
  public void JohneatsonlyFruits() {
    TBox tBox = new TBox();
    //Test if John eats only Fruits
    ConceptExpression john = new ConceptExpression("John");
    ConceptExpression fruits = new ConceptExpression("Fruits");
    UniversalExpression uni = new UniversalExpression("eats", fruits);
    Set<Expression> subExpressions = new HashSet<>();
    subExpressions.add(uni);
    tBox.put(john, subExpressions);
    SubsumptionEquivalence query = new SubsumptionEquivalence(john,
        new NotExpression(uni));
    Reasoner reasoner = new Reasoner(tBox, query);
    assertAnswer("John eats only Fruits?", true, reasoner.queryIsValid());

  }

  @Test
  public void fluffylikesfish() {
    TBox tBox = new TBox();
    //Test if Fluffy likes Fish
    ConceptExpression fluffy = new ConceptExpression("Fluffy");
    ConceptExpression fish = new ConceptExpression("Fish");
    ExistentialExpression exst = new ExistentialExpression("likes", fish);
    Set<Expression> subExpressions = new HashSet<>();
    subExpressions.add(exst);
    tBox.put(fluffy, subExpressions);
    SubsumptionEquivalence query = new SubsumptionEquivalence(fluffy,
        new NotExpression(exst));
    Reasoner reasoner = new Reasoner(tBox, query);
    assertAnswer("Fluffy likes Fish?", true, reasoner.queryIsValid());

  }

  @Test
  public void fluffylikesGuacomole() {
    TBox tBox = new TBox();
    //Test if fluffy likes Guacomole
    ConceptExpression fluffy = new ConceptExpression("Fluffy");
    ConceptExpression guacomole = new ConceptExpression("Guacomole");
    ExistentialExpression exst = new ExistentialExpression("likes",
        guacomole);
    Set<Expression> subExpressions = new HashSet<>();
    subExpressions.add(exst);
    tBox.put(fluffy, subExpressions);
    SubsumptionEquivalence query = new SubsumptionEquivalence(fluffy,
        new NotExpression(exst));
    Reasoner reasoner = new Reasoner(tBox, query);
    assertAnswer("Fluffy likes Guacomole?", true, reasoner.queryIsValid());

  }

  @Test
  public void BritishgotoBar() {
    TBox tBox = new TBox();
    //test if British go to bar with Existential Expression
    ConceptExpression british = new ConceptExpression("British");
    ConceptExpression bar = new ConceptExpression("Bar");
    ExistentialExpression exst = new ExistentialExpression("goto", bar);
    Set<Expression> subExpressions = new HashSet<>();
    subExpressions.add(exst);
    tBox.put(british, subExpressions);
    SubsumptionEquivalence query = new SubsumptionEquivalence(british,
        new NotExpression(exst));
    Reasoner reasoner = new Reasoner(tBox, query);
    assertAnswer("British go to bar?", true, reasoner.queryIsValid());

  }

  @Test
  public void JohnlikesIcecreams() {
    TBox tBox = new TBox();
    //Test If John likes Icecream with Existential Expression
    ConceptExpression john = new ConceptExpression("John");
    ConceptExpression icecreams = new ConceptExpression("Icecreams");
    ExistentialExpression exst = new ExistentialExpression("likes",
        icecreams);
    Set<Expression> subExpressions = new HashSet<>();
    subExpressions.add(exst);
    tBox.put(john, subExpressions);
    SubsumptionEquivalence query = new SubsumptionEquivalence(john,
        new NotExpression(exst));
    Reasoner reasoner = new Reasoner(tBox, query);
    assertAnswer("John likes Icecreams?", true, reasoner.queryIsValid());

  }

  @Test
  public void testfluffywithunion() {
    TBox tBox = new TBox();
    //Test if Fluffy likes Fish or Guacomole with Union Expression
    ConceptExpression fluffy = new ConceptExpression("Fluffy");
    ConceptExpression fish = new ConceptExpression("Fish");
    ConceptExpression guacomole = new ConceptExpression("Guacomole");
    UnionExpression union = new UnionExpression(fish, guacomole);

    Set<Expression> subExpressions = new HashSet<>();
    subExpressions.add(union);
    tBox.put(fluffy, subExpressions);
    SubsumptionEquivalence query = new SubsumptionEquivalence(fluffy,
        new NotExpression(union));
    Reasoner reasoner = new Reasoner(tBox, query);
    assertAnswer("Fluffy likes Fish or Guacomole?", true,
        reasoner.queryIsValid());

  }

  @Test
  public void teststudentswithunion() {
    TBox tBox = new TBox();
    //Test if Students are Undergrad or grad students with uninon expression
    ConceptExpression Students = new ConceptExpression("Students");
    ConceptExpression undergrad = new ConceptExpression("Undergrad");
    ConceptExpression grad = new ConceptExpression("Grad");
    UnionExpression union = new UnionExpression(undergrad, grad);

    Set<Expression> subExpressions = new HashSet<>();
    subExpressions.add(union);
    tBox.put(Students, subExpressions);
    SubsumptionEquivalence query = new SubsumptionEquivalence(Students,
        new NotExpression(union));
    Reasoner reasoner = new Reasoner(tBox, query);
    assertAnswer("students is equivalent to undergrad or grad?", true,
        reasoner.queryIsValid());

  }

  @Test
  public void testGreaterthanequal() {
    TBox tbox = new TBox();
    //Test if Undergrad students can take atleast 3 courses , use Greater than or Equal to expression
    ConceptExpression undergrad = new ConceptExpression("Undergrad");
    ConceptExpression courses = new ConceptExpression("Courses");
    GreaterThanOrEqualToExpression greater = new GreaterThanOrEqualToExpression(
        "takes", 3);
    Set<Expression> subExpressions = new HashSet<>();
    subExpressions.add(greater);
    tbox.put(undergrad, subExpressions);
    SubsumptionEquivalence query = new SubsumptionEquivalence(undergrad,
        new NotExpression(greater));
    Reasoner reasoner = new Reasoner(tbox, query);
    assertAnswer("Undergrad can take atleat 3 courses?", true,
        reasoner.queryIsValid());

  }

  @Test
  public void testlessthanequal() {
    TBox tbox = new TBox();
    //Test if Graduate Students can take atmost 3 courses , use Leassthan or Equalto Expression
    ConceptExpression grad = new ConceptExpression("Grad");
    ConceptExpression courses = new ConceptExpression("Courses");
    LessThanOrEqualToExpression lesser = new LessThanOrEqualToExpression(
        "takes", 3);
    Set<Expression> subExpressions = new HashSet<>();
    subExpressions.add(lesser);
    tbox.put(grad, subExpressions);
    SubsumptionEquivalence query = new SubsumptionEquivalence(grad,
        new NotExpression(lesser));
    Reasoner reasoner = new Reasoner(tbox, query);
    assertAnswer("Graduates can take atmost 3 courses?", true,
        reasoner.queryIsValid());

  }

  @Test
  public void BritishersCarryUmbrella() {
    TBox tBox = new TBox();
    //Test if British carry Umbrella, use not expression for D
    ConceptExpression british = new ConceptExpression("British");
    ConceptExpression umbrella = new ConceptExpression("Umbrella");
    ExistentialExpression exst = new ExistentialExpression("carry",
        umbrella);
    Set<Expression> subExpressions = new HashSet<>();
    subExpressions.add(exst);
    tBox.put(british, subExpressions);
    SubsumptionEquivalence query = new SubsumptionEquivalence(british,
        new NotExpression(exst));
    Reasoner reasoner = new Reasoner(tBox, query);
    assertAnswer("British carry umbrellas?", true, reasoner.queryIsValid());

  }

  @Test
  public void testjohnIsSubsetofBritish() {
    TBox tbox = new TBox();
    //Test if John is a subset of British, use not expression for D 
    ConceptExpression john = new ConceptExpression("John");
    ConceptExpression british = new ConceptExpression("British");
    Subsumption johnSubsetBritish = new Subsumption(john, british);
    tbox.add(johnSubsetBritish);
    SubsumptionEquivalence query = new SubsumptionEquivalence(john,
        new NotExpression(british));
    Reasoner reasoner = new Reasoner(tbox, query);
    assertAnswer("Is john subset of British?", true, reasoner.queryIsValid());
  }

  @Test
  public void testJohnSubsetCarryNotBooks() {
    ConceptExpression john = new ConceptExpression("JOHN");
    ConceptExpression british = new ConceptExpression("BRITISH");
    ConceptExpression books = new ConceptExpression("BOOKS");

    ExistentialExpression carriesNotBooks = new ExistentialExpression("carry", new NotExpression(books));

    Subsumption britishSubsetCarryNotBooks = new Subsumption(british, carriesNotBooks);
    Subsumption johnSubsetBritish = new Subsumption(john, british);

    TBox tbox = new TBox();
    tbox.add(britishSubsetCarryNotBooks);
    tbox.add(johnSubsetBritish);

    Subsumption query = new Subsumption(john, new NotExpression(carriesNotBooks));

    Expression a = new NotExpression(carriesNotBooks);
    Expression b = new NotExpression(new ExistentialExpression("carry", new NotExpression(new ConceptExpression("BOOKS"))));
    assertTrue(a.equals(b));

    Reasoner reasoner = new Reasoner(tbox, query);
    assertTrue(reasoner.queryIsValid());

    reasoner = new Reasoner(tbox, query);
    query = new Subsumption(john, new NotExpression(new ExistentialExpression("carry", new NotExpression(books))));
    assertTrue(reasoner.queryIsValid());
  }
}
