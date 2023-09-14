public class TestDoubleCharachter {
	  public static void main(String[] args)
	   {
	      test("Hello"); // expect: a
	      test("roommate"); // expect: o (not m)
	      test("mate"); // expect: 0 (no duplicate letters)
	      test("test"); // expect: 0 (the t isn't repeating)
	      test("Mississippi"); // expect: 0 (the t isn't repeating)
	   }

	   public static void test(String s)
	   {
		   DoubleCharachter wa = new DoubleCharachter(s);
	      char result = wa.firstRepeatedCharacter();
	      if (result == 0)
	         System.out.println("No repeated character.");
	      else
	         System.out.println("First repeated character = " + result);
	     
	   }
}

