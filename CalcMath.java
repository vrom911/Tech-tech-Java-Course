/**
* This is the parser of Math expression
* @version 1.0  -- 29/02/2016
* @author Veronika Romashkina
* @return Returns the result of operations given by user when run the programm
*/
import java.util.Stack;
import java.util.EmptyStackException;
import static java.lang.Math.*;

public class CalcMath {
	public static int cur_indx = 0;

	public static void main(String[] eq) {
		// 		Chapter 3.6.8 : 
		// 		Every time you concatenate strings, a new String object is constructed. 
		//		This is time-consuming and wastes memory. 
		// 		Using the StringBuilder class avoids this problem.
		System.out.println("Welcome!");
		StringBuilder expressionBuilder = new StringBuilder();
		// I need to have expression in () , so algorythm works smoother
		expressionBuilder.append('(');
		for (String e : eq) {
			expressionBuilder.append(e);
		}
		expressionBuilder.append(')');
		//when done go back to String
		String expression = expressionBuilder.toString();
		if (expression != null && expression.length() > 0) {
			Stack<Double> nums = new Stack();
			Stack operators = new Stack();
			int last_indx = 0;
			int prev_type = 1;
			check_expression:
			while(cur_indx < expression.length()) {
				int type = getElement(expression,cur_indx);
				switch (type) {
					// Again: 0 is for numbers, 1 is for chars(operators), -1 wrong data! Let's go

					// Operators
					case 1:
						String op = expression.substring(cur_indx-1,cur_indx);
						if (op.equals(")")) {
							while ((!operators.empty() && !nums.empty() ) && !operators.peek().equals("(")) {	
								changeIteration(nums, operators);
							}
							operators.pop();
						} else {
							int level = checkLevel(op);
							//this check is for unary operations like -1 == 0-1
							if (prev_type == 10) {
								switch (level){
									case 1:
										nums.push(0.0);
										break;
									case 2:
										nums.push(1.0);
										break;
								}
							}
							//this check is for expression like 8(2+2) etc.
							if (level == 10 && prev_type == 0) {
								operators.push("*");
							}
							if ( canIterate(operators, level) ) {
								changeIteration(nums,operators);
							} 
							operators.push(op);
							prev_type = level;
						}
						break;
					// Numbers(Operands)
					case 0:
						nums.push(Double.parseDouble(expression.substring(last_indx,cur_indx)));
						prev_type = 0;
						break;
					// Hmm Wrong data
					case -1:
						System.out.println("Write correct math expression you want to solve");
						break check_expression;
				}
				//remember last index (for numbers , because we have only char operations, but numbers can be 123 or 1.2 or .005)
				last_indx = cur_indx;
			}
			if (operators.empty() && nums.size()==1) {
				System.out.println(expression+" = " + nums.peek());
			}
		} else {
			System.out.println("Write correct math expression you want to solve");
		}
	}
	
	public static int getElement(String expression, int indx) {
		// 0 - integer
		// 1 - operator
		// -1 - error
		int type_flag,row = 0;
		String possible_operators = "(+-*/)";
		//charAt method to get character of this index
		if (Character.isDigit(expression.codePointAt(indx)) || expression.substring(indx,indx+1).equals(".")) {
			type_flag = 0;
			row++;
			
    		while ((indx + row) < expression.length() && (Character.isDigit(expression.codePointAt(indx + row)) || expression.codePointAt(indx + row) == '.'))
        		{
        			row++;
        		}
		} else if (possible_operators.indexOf(expression.codePointAt(indx)) != -1) {
			row++;
			type_flag = 1;

		} else {
			row++;
			type_flag = -1;
		}
		cur_indx += row;
		return type_flag;

	}
	public static  void changeIteration(Stack nums, Stack operators) {
		try {
			double last_num = (Double)(nums.pop());
			double prev_num = (Double)(nums.pop());
			double result = makeOperation(prev_num, last_num, (String)operators.pop());
			nums.push( result );
		}
		catch(EmptyStackException er) {
			System.out.println("Wrong input"); 
		}
		
	}
	public static boolean canIterate(Stack operators, int cur_oper_l){
		if (!operators.empty()) {
			int prev_oper_l = checkLevel((String)operators.peek());
			if (cur_oper_l == 10 || prev_oper_l == 10) {
				return false;
			} else if (prev_oper_l >= cur_oper_l) {
				return true;
			} else {return false;}
		} else {return false;}
		

	}
	public static  int checkLevel(String operator) {
		int lev = 0;
		switch (operator) {
			case "+":
				lev = 1;
				break;
			case "-":
				lev = 1;
				break;
			case "*":
				lev = 2;
				break;
			case "/":
				lev = 2;
				break;
			case "(":
				lev = 10;
				break;
		}
		return lev;
	}

	public static double makeOperation(double a, double b, String operator) {
		String error_text = "";
		double result = 0;
		if (operator.equals("+")) {
				result = a + b;
			} else if (operator.equals("-")) {
				result = a - b;
			} else if (operator.equals("*")) {
				result = a * b;
			} else if (operator.equals("/")) {	
				try {
					result = a / b;
				} catch (ArithmeticException e) { 
            		System.err.println("You're trying to divide by Zero, r u serious?"); 
        		} 
			} else if (operator.equals("%")) {
				result = a % b;
			} else {
				error_text = "Invalid operator:" + operator;
			}
			if (error_text != "") {
				System.out.println(error_text);
			}
			return result;
	}
}
