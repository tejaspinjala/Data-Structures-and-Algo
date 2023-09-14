import java.util.Stack;
import java.util.*;

class infixToPostfix {
	
	//returns true if the character is an operator
	public boolean operator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '(' || c == ')' ;
	}
	
	//returns true if the characters is a space
	public boolean space(char c) {
		return c == ' ';
	}
	
	//This method checks which operator has lower precedence between two operators. 
	//It takes two operator characters as input and returns true if the first 
	//operator has lower precedence than the second operator.
	public boolean precedence(char operator1, char operator2) {
		switch (operator1) {
	    
		case '+':
        case '-':
        	return !(operator2 == '+' || operator2 == '-') ;

        case '*':
        case '/':
           return operator2 == '^' || operator2 == '(';

        case '^':
           return operator2 == '(';

        case '(': return true;

        default:
           return false;
           }
	}
	
	//This method takes an infix expression as input and returns 
	//the corresponding postfix expression.
	public String conversion(String input) {
        input = input.replaceAll("–", "-");

        Stack<String> operatorStack = new Stack<>();
        StringTokenizer p = new StringTokenizer(input,"+-*/^() ",true);
        StringBuffer postfix = new StringBuffer(input.length());
        
        while (p.hasMoreTokens()) {
        	String token = p.nextToken();
            char c = token.charAt(0);
            
            if ((token.length() == 1) && operator(c)) {
                while (!operatorStack.empty() && !precedence(((String)operatorStack.peek()).charAt(0), c))
                    postfix.append(" ").append((String)operatorStack.pop());
                if (c == ')') {
                    String operator = (String)operatorStack.pop();
                    while (operator.charAt(0)!='(') {
                        postfix.append(" ").append(operator);
                        operator = (String)operatorStack.pop();
                    }
                }
                else
                    operatorStack.push(token);
            }
            else if ((token.length() == 1) && space(c)) {
            }
            else {
                postfix.append(" ").append(token);
            }
        }
        while (!operatorStack.empty())
            postfix.append(" ").append((String)operatorStack.pop());

        return postfix.toString();
    }
	
	//This method takes a postfix expression as input and evaluates it. 
	//It uses a stack to perform the evaluation.
	public int evaluate(String input) {
		Stack<Integer> stack = new Stack<>();
		
		int operator1, operator2, result = 0;
        String token;
        StringTokenizer tokenizer = new StringTokenizer(input);
        
        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
            char c = token.charAt(0);
            if (operator(c)) {
                if (token.length() == 1) { // regular operator
                    operator2 = ((Integer) stack.pop()).intValue();
                    operator1 = ((Integer) stack.pop()).intValue();
                    result = checkSingleOp(token.charAt(0), operator1, operator2);
                    stack.push(new Integer(result));
                }
                else { // negative number
                    stack.push(new Integer(Integer.parseInt(token)));
                }
            }
            else {
                stack.push(new Integer(Integer.parseInt(token)));
            }
        }
        
        result = ((Integer) stack.pop()).intValue();
        return result;
        
	}
	
	private final static char add = '+', sub = '-', mult = '*', div = '/', power ='^';
	
	//This method applies a single operator to two operands.
	public int checkSingleOp(char operation, int operator1, int operator2) {
		int result = 0;
	    switch (operation) {
	        case add :
	            result = operator1 + operator2;
	            break;
	        case sub :
	            result = operator1 - operator2;
	            break;
	        case mult :
	            result = operator1 * operator2;
	            break;
	        case div :
	            result = operator1 / operator2;
	            break;
	        case power :
	            result = (int) Math.pow(operator1, operator2);
	    }

	    return result;
	}
	
	//main method
	public static void main(String [] args) {
		
		String[] test = {"( ( 2 + 3 ) / 5 ) – 1"};
		
		infixToPostfix converter = new infixToPostfix();
		
		for (int i=0; i < test.length; i++) {
			System.out.println("infix: " + test[i]);
			System.out.println("postfix: " + converter.conversion(test[i]));
			System.out.println("postfix evaluation: " + converter.evaluate(converter.conversion(test[i])));
			System.out.println();
		}
		
	}
	
}