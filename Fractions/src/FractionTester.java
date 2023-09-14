public class FractionTester {

    public static void main(String[] args) {
	Fraction f = new Fraction(3, 4);
	Fraction g = new Fraction("1/5");
	Fraction[] myFractions = new Fraction[5];

	System.out.println("f = " + f + " and g = " + g);

	// Add the fractions, store the result

	Fraction sum = f.add(g);
	
	myFractions[0] = f;
	myFractions[1] = g;
	myFractions[4] = sum;

	// Print the result
	System.out.println(myFractions[4]);
    }
}
