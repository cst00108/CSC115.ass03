/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import java.util.LinkedList;

/**
 *
 * @author Jason Donald
 */
public class FixxingPost {
	private Stack<Character> timesesAndStuff = new Stack<>();
	private LinkedList<Character> result = new LinkedList<>();
	
	public void getPostFixFromInfix(String infix){
		for(int index=0; index<infix.length(); index++){
			char token = infix.charAt(index);

			try{
				//test out if its an int
				int operand = Integer.parseInt(
						new String(new char[]{token}));
				
				//ok it is, put it on the list
				
			} catch(NumberFormatException x){
				if(token == '('){
					timesesAndStuff.push(token);
				} else if(istoken is an operator
				while (stack is not empty and top of stack is not "("
				and token precedence <= precedence of stack top)
				pop operator off stack
				append operator to list
				push token onto stack
				else if token is ")"
				while top of stack is not "("
				pop operator off stack
				append operator to list
				pop "(" off stack
	
			}

			while stack is not empty
			pop operator off stack
			append operator to list

		}
	}
	
	private static boolean isOperator(char operator){
		if(isHigherPresedence('(', operator){
			return true;
		}
		
		
	}
	
	public static boolean isHigherPresedence(char higher, char lower){
		char[] ops = {'-', '+', '/', '*', '^', '(', ')'};
		int higherInt = -1;
		int lowerInt = -1;
		
		
		for(int index=0; index<ops.length && higherInt<0 && lowerInt<0; index++){
			if(higher == ops[index]){
				higherInt = index;
			}
			if(lower == ops[index]){
				lowerInt = index;
			}
		}
		
		if(higher > lower){
			return true;
		}
		
		return false;
	}
}
/*
stack = new Stack
list = new List
for each token in infix expression
 if token is operand
 append operand to list
 else if token is "("
 push "(" onto stack
 else if token is an operator
 while (stack is not empty and top of stack is not "("
 and token precedence <= precedence of stack top)
 pop operator off stack
 append operator to list
 push token onto stack
 else if token is ")"
 while top of stack is not "("
 pop operator off stack
 append operator to list
 pop "(" off stack
while stack is not empty
 pop operator off stack
 append operator to list
*/
