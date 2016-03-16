package reasoner.tests;

import org.junit.Test;
import reasoner.Parser;
import reasoner.Reasoner;
import reasoner.SubsumptionEquivalence;

import static junit.framework.Assert.assertTrue;

public class ParserTest {

	public static void assertAnswer(String query, boolean lhs, boolean rhs) {
		if (lhs != rhs) {
			throw new RuntimeException("Invalid answer for: " + query);
		}
	}

//	public static void testRichMan() {
//		Parser parser = new Parser();
//		parser.parseLine("RICH_MAN equivalent RICH intersect MAN");
//
//		TBox tbox = parser.getTBox();
//		ConceptExpression man = new ConceptExpression("MAN");
//		ConceptExpression rich = new ConceptExpression("RICH");
//		IntersectExpression richAndMan = new IntersectExpression(rich, man);
//		ConceptExpression richMan = new ConceptExpression("RICH_MAN");
//
//		Set<Expression> richManIsEqualToExpressions = tbox.get(richMan);
//		assert (richManIsEqualToExpressions.size() == 1);
//		assert (richManIsEqualToExpressions.contains(richAndMan));
//	}
//
//	public static void testRichWoman() {
//		Parser parser = new Parser();
//		parser.parseLine("RICH_WOMAN equivalent RICH intersect WOMAN");
//		TBox tbox = parser.getTBox();
//		ConceptExpression woman = new ConceptExpression("WOMAN");
//		ConceptExpression rich = new ConceptExpression("RICH");
//		IntersectExpression richAndWoman = new IntersectExpression(rich, woman);
//		ConceptExpression richWoman = new ConceptExpression("RICH_WOMAN");
//
//		Set<Expression> richWomanIsEqualToExpressions = tbox.get(richWoman);
//		assert (richWomanIsEqualToExpressions.size() == 1);
//		assert (richWomanIsEqualToExpressions.contains(richAndWoman));
//
//	}
//
//	public static void testcowissubsetofAnimal() {
//		Parser parser = new Parser();
//		parser.parseLine("COW subset of ANIMAL");
//		TBox tbox = parser.getTBox();
//		ConceptExpression cow = new ConceptExpression("COW");
//		ConceptExpression animal = new ConceptExpression("ANIMAL");
//		Set<Expression> cowsubsetExp = tbox.get(animal);
//		assert (cowsubsetExp.size() == 1);
//		assert (cowsubsetExp.contains(cow));
//
//	}
//
//	public static void testStudents() {
//		Parser parser = new Parser();
//		parser.parseLine("STUDENTS equivalent UNDERGRAD intersect GRAD");
//		TBox tbox = parser.getTBox();
//		ConceptExpression undergrad = new ConceptExpression("UNDERGRAD");
//		ConceptExpression grad = new ConceptExpression("GRAD");
//		IntersectExpression undergradandgrad = new IntersectExpression(undergrad, grad);
//		ConceptExpression students = new ConceptExpression("STUDENTS");
//		Set<Expression> studentsequivalentExp = tbox.get(students);
//		assert (studentsequivalentExp.size() == 1);
//		assert (studentsequivalentExp.contains(undergradandgrad));
//
//	}
//
//	public static void fluffyeatsKibbles() {
//		Parser parser = new Parser();
//		parser.parseLine("FLUFFY eats KIBBLES");
//		TBox tbox = parser.getTBox();
//		ConceptExpression fluffy = new ConceptExpression("FLUFFY");
//		ConceptExpression kibbles = new ConceptExpression("KIBBLES");
//		ExistentialExpression exst = new ExistentialExpression("eats", kibbles);
//
//		Set<Expression> studentsequivalentExp = tbox.get(exst);
//		assert (studentsequivalentExp.size() == 1);
//		assert (studentsequivalentExp.contains(fluffy));
//
//	}
//
//	public static void fluffylikesfish() {
//		Parser parser = new Parser();
//		parser.parseLine("FLUFFY likes FISH");
//		TBox tbox = parser.getTBox();
//		ConceptExpression fluffy = new ConceptExpression("FLUFFY");
//		ConceptExpression fish = new ConceptExpression("FISH");
//		ExistentialExpression exst = new ExistentialExpression("likes", fish);
//
//		Set<Expression> studentsequivalentExp = tbox.get(exst);
//		assert (studentsequivalentExp.size() == 1);
//		assert (studentsequivalentExp.contains(fluffy));
//
//	}
//
//	public static void fluffylikesGuacomole() {
//		Parser parser = new Parser();
//		parser.parseLine("FLUFFY likes GUACOMOLE");
//		TBox tbox = parser.getTBox();
//		ConceptExpression fluffy = new ConceptExpression("FLUFFY");
//		ConceptExpression guacomole = new ConceptExpression("GUACOMOLE");
//		ExistentialExpression exst = new ExistentialExpression("likes",
//				guacomole);
//
//		Set<Expression> studentsequivalentExp = tbox.get(exst);
//		assert (studentsequivalentExp.size() == 1);
//		assert (studentsequivalentExp.contains(fluffy));
//
//	}
//
//	public static void BritishgotoBar() {
//		Parser parser = new Parser();
//		parser.parseLine("BRITISH goto BAR");
//		TBox tbox = parser.getTBox();
//		ConceptExpression british = new ConceptExpression("BRITISH");
//		ConceptExpression bar = new ConceptExpression("BAR");
//		ExistentialExpression exst = new ExistentialExpression("goto", bar);
//
//		Set<Expression> studentsequivalentExp = tbox.get(exst);
//		assert (studentsequivalentExp.size() == 1);
//		assert (studentsequivalentExp.contains(british));
//	}
//
//	public static void JohneatsonlyFruits() {
//		Parser parser = new Parser();
//		parser.parseLine("JOHN eats only FRUITS");
//		TBox tBox = new TBox();
//		ConceptExpression john = new ConceptExpression("JOHN");
//		ConceptExpression fruits = new ConceptExpression("FRUITS");
//		UniversalExpression uni = new UniversalExpression("eats", fruits);
//		Set<Expression> subExpressions = tBox.get(uni);
//
//		assert (subExpressions.size() == 1);
//		assert (subExpressions.contains(john));
//
//	}

	public static void BritishgotoBar() {
		Parser parser = new Parser();
		parser.parseLine("British subset exists goto dot not park union forall goto dot not library");
		parser.parseLine("John subset rich OR NOT poor AND ");
		parser.parseLine("British subset exists goto dot school");
	}

	public static void equality() {
		Parser parser = new Parser();
		parser.parseLine("British subset lessthanorequalto 2 dot goto");
	}

	public static void testNot() {
		Parser parser = new Parser();
		parser.parseLine("British subset not poor");
	}

	public static  void FATTEST(){
		Parser parser = new Parser();
		parser.parseLine("British subset exists goto dot not bar intersect greaterthanorequalto 2 dot goto union rich");
		parser.parseLine("British subset not rich");
		parser.parseLine("John equivalent British");
	}

	@Test
	public void testJohnSubsetCarryNotBooks() {
		Parser parser = new Parser();
		parser.parseLine("JOHN subset BRITISH");
		parser.parseLine("BRITISH subset exists carry dot not BOOKS");

		SubsumptionEquivalence query = parser.parseQuery("JOHN subset exists carry dot not BOOKS");
		Reasoner reasoner = new Reasoner(parser.getTBox(), query);
		assertTrue(reasoner.queryIsValid());
	}
}
