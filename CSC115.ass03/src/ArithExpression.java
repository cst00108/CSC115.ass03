/*
 * The shell of the class, to be completed as part of CSC115 Assignment 3 : Calculator.
 */

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/*
 * NOTE TO STUDENT:
 * Fill in any of the parts that have the comment:
	 /******COMPLETE *****\
 * Do not change method headers or code that has been supplied.
 * All methods must be properly commented.
 * Please delete all messages to you, incuding this one, before submitting.
 */

public class ArithExpression {

	private TokenList postfixTokens;
	private TokenList infixTokens;

	/**
	 * Sets up a legal standard Arithmetic expression.
	 * The only parentheses accepted are "(" and ")".
	 * @param word An arithmetic expression in standard infix order.
	 * 		An invalid expression is not expressly checked for, but will not be
	 * 		successfully evaluated, when the <b>evaluate</b> method is called.
	 * @throws InvalidExpressionException if the expression cannot be properly parsed,
	 *  	or converted to a postfix expression.
	 */
	public ArithExpression(String word) {
		if (Tools.isBalancedBy("()",word)) {
			tokenizeInfix(word);
			infixToPostfix();
		} else {
			throw new InvalidExpressionException("Parentheses unbalanced");
		}
	}

	/*
	 * A private helper method that tokenizes a string by separating out
	 * any arithmetic operators or parens from the rest of the string.
	 * It does no error checking.
	 * The method makes use of Java Pattern matching and Regular expressions to
	 * isolate the operators and parentheses.
	 * The operands are assumed to be the substrings delimited by the operators and parentheses.
	 * The result is captured in the infixToken list, where each token is 
	 * an operator, a paren or a operand.
	 * @param express The string that is assumed to be an arithmetic expression.
	 */
	private void tokenizeInfix(String express) {
		infixTokens  = new TokenList(express.length());

		// regular expression that looks for any operators or parentheses.
		Pattern opParenPattern = Pattern.compile("[-+*/^()]");
		Matcher opMatcher = opParenPattern.matcher(express);

		String matchedBit, nonMatchedBit;
		int lastNonMatchIndex = 0;
		String lastMatch = "";

		// find all occurrences of a matched substring
		while (opMatcher.find()) {
			matchedBit = opMatcher.group();
			// get the substring between matches
			nonMatchedBit = express.substring(lastNonMatchIndex, opMatcher.start());
			nonMatchedBit = nonMatchedBit.trim(); //removes outside whitespace
			// The very first '-' or a '-' that follows another operator is considered a negative sign
			if (matchedBit.charAt(0) == '-') {
				if (opMatcher.start() == 0 || 	
					!lastMatch.equals(")") && nonMatchedBit.equals("")) {
					continue;  // ignore this match
				}
			}
			// nonMatchedBit can be empty when an operator follows a ')'
			if (nonMatchedBit.length() != 0) {
				infixTokens.append(nonMatchedBit);
			}
			lastNonMatchIndex = opMatcher.end();
			infixTokens.append(matchedBit);
			lastMatch = matchedBit;
		}
		// parse the final substring after the last operator or paren:
		if (lastNonMatchIndex < express.length()) {
			nonMatchedBit = express.substring(lastNonMatchIndex,express.length());
			nonMatchedBit = nonMatchedBit.trim();
			infixTokens.append(nonMatchedBit);
		}
	}

	/**
	 * Determines whether a single character string is an operator.
	 * The allowable operators are {+,-,*,/,^}.
	 * @param op The string in question.
	 * @return True if it is recognized as a an operator.
	 */
	public static boolean isOperator(String op) {
		switch(op) {
			case "+":
			case "-":
			case "/":
			case "*":
			case "^":
				return true;
			default:
				return false;
		}
	}
		
	/**
	 * NOTE TO STUDENT: These requirements don't show up in the 
	 * java documentation because it is a private method.
	 * It is private because it directly accesses the data fields.	
	 * 
	 * A private method that initializes the postfixTokens data field.
	 * It takes the information from the infixTokens data field.
	 * If, during the process, it is determined that the expression is invalid,
	 * an InvalidExpressionException is thrown.
 	 * Note that since the only method that calls this method is the constructor,
	 * the Exception is propogated through the constructor.
	 */
	private void infixToPostfix() {
		postfixTokens = new TokenList(infixTokens.size());
		StringStack stack = new StringStack();
	
		for(int index=0; index<infixTokens.size(); index++){
			String token = infixTokens.get(index);
			
			if(!isOperator(token) && !isBracket(token)){
				postfixTokens.append(token);
			}else if(token.equals("(")){
				stack.push(token);
			}else if (token.equals(")")){
				while (!stack.peek().equals("(")){
					postfixTokens.append(stack.pop());
				}

				stack.pop(); //pop off '('
			} else {
				while (!stack.isEmpty() && !stack.peek().equals("(") &&
						(isHigherPresedence(stack.peek(), token) || 
						stack.peek().equals(token))){

					postfixTokens.append(stack.pop());
				}
				
				stack.push(token);
			}
		}//end for
		
		while(!stack.isEmpty()){
			postfixTokens.append(stack.pop());
		}
	}
	
	public static boolean isHigherPresedence(String higher, String lower){
		String[] ops = {"-", "+", "/", "*", "^", "(", ")"};
		int higherInt = -1;
		int lowerInt = -1;
		
		
		for(int index=0; index<ops.length; index++){
			if(higher.equals(ops[index])){
				higherInt = index;
			}
			if(lower.equals(ops[index])){
				lowerInt = index;
			}
		}
		
		if(higherInt > lowerInt){
			return true;
		}
		
		return false;
	}

	public String getInfixExpression() {
		return getString(infixTokens);
	}

	public String getPostfixExpression() {
		return getString(postfixTokens);
	}
		
	public double evaluate() {
		StringStack construction = new StringStack();
		
		for(int index=0; index<postfixTokens.size(); index++){
			String token = postfixTokens.get(index);
			
			if(!isOperator(token)){
				construction.push(token);
			} else {
				double num2 = Double.parseDouble(construction.pop());
				double num1 = Double.parseDouble(construction.pop());
				
				if(token.equals("-")){
					construction.push(Double.toString(num1 - num2));
				} else if(token.equals("+")){
					construction.push(Double.toString(num1 + num2));
				} else if(token.equals("/")){
					construction.push(Double.toString(num1 / num2));
				} else if(token.equals("*")){
					construction.push(Double.toString(num1 * num2));
				}
			}
		}
		
		return Double.valueOf(construction.pop());
	}
						
	public static void main(String[] args) {
		System.out.println(isHigherPresedence("*", "-"));
		System.out.println(isHigherPresedence("/", "+"));
		System.out.println(isHigherPresedence("(", "("));
		System.out.println(isHigherPresedence("*", "-"));
		
		String[] infixes = {"(3+2)*2", "1+2*3/2", "(5*2+5*2)/2"};
		
		for(int index=0; index<infixes.length; index++){
			String infix = infixes[index];
			
			System.out.println("Infix Expression:  " + infix);
			ArithExpression a = new ArithExpression(infix); //should be... %#$@!!
			System.out.println("Postfix Expression:  " + a.getPostfixExpression());
			System.out.println("Solution:  " + a.evaluate());
			System.out.println();
		}
	}
	
	private static String getString(TokenList tokenList){
		String toReturn = "";
		
		for(int index=0; index<tokenList.size(); index++){
			toReturn += tokenList.get(index);
		}
		
		return toReturn;
	}
	
	private static boolean isBracket(String token){
		String brackets = "(){}[]<>";
		
		for(int index=0; index<brackets.length(); index++){
			if(Character.toString(brackets.charAt(index)).equals(token)){
				return true;
			}
		}
		
		return false;
	}
}
