
public class MyInteger {
	//int data type that stores value
	int value;
	
	//constructor that creates a MyInteger object for the specified int value
	public MyInteger(int value) {
		this.value = value;
	}
	
	//get method that returns the int value
	public int getValue() {
		return value;
	}
	
	//returns true if the value is even
	public boolean isEven() {
		return isEven(value);
	}
	
	//returns true if the value is odd
	public boolean isOdd() {
		return isOdd(value);
	}
	
	//returns true if the value is prime
	public boolean isPrime() {
		return isPrime(value);
	}
	
	//checks if the given value is even and returns true or false
	public static boolean isEven(int val) {
		if(val % 2 == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	//checks if the given value is odd and returns true or false
	public static boolean isOdd(int val) {
		if(val % 2 == 1) {
			return false;
		} else {
			return true;
		}
	}
	
	////checks if the given value is prime and returns true or false
	public static boolean isPrime(int val) {
		for(int i = 2; i <= val / 2; i++) {
			if(val % 2 == 0) {
				return false;
			}
		}
		return true;
	}
	
	//returns true if the given value is even
	public static boolean isEven(MyInteger myInt) {
		return myInt.isEven();
	}
	
	//returns true if the given value is odd
	public static boolean isOdd(MyInteger myInt) {
		return myInt.isOdd();
	}
	
	//returns true if the given value is prime
	public static boolean isPrime(MyInteger myInt) {
		return myInt.isPrime();
	}
	
	//returns true if both integer values match
	public boolean equals(int val) {
		return this.value == val;
	}
	
	//returns true if both integer values match
	public boolean equals(MyInteger myInt) {
		return myInt.value == this.value;
	}
	
	//converts a character array into an integer
	public static int parseInt(char[] character) {
		int val = 0;
	    for(int i = 0; i < character.length; i++){
	        val = val * 10 + (character[i]-'0');
	    }
	    return val;
	}
	
	//converts a String value to an integer
	public static int parseInt(String str) {
		return Integer.parseInt(str);
	}
}
