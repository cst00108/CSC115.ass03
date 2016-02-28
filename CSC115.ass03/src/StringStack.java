/*
 * The shell of the class, to be completed as part of the CSC115 Assignment 3 : Calculator.
 */

/*
 * NOTE TO STUDENT:
 * Fill in any of the parts that have the comment:
	//*******COMPLETE*******
 * Do not change method headers or code that has been supplied.
 * All methods must be properly commented.
 * Please delete all messages to you, including this one, before submitting.
 */
public class StringStack {

	private Node head;

	public boolean isEmpty() {
		if(head == null){
			return true;
		}
		
		return false;
	}

	public String pop() {
		String toReturn = (String)head.get();
		head = head.remove();
		
		if(toReturn == null){
			throw new StackEmptyException("Nothing to pop.");
		}
		
		return toReturn;
	}

	public String peek() {
		if(head == null){
			throw new StackEmptyException("Nothing to peek.");
		}

		return (String)head.get();
	}

	public void push(String item) {
		head = new Node<String>(item).add(head);
	}

	public void popAll() {
		head = null;
	}
	
	public static void main(String[] aaarg){
		StringStack s = new StringStack();
		System.out.println("isEmpty() is empty:  " + s.isEmpty());
		String abc = "abc";
		s.push(abc);
		System.out.println("push() abc");
		System.out.println("isEmpty() isnt empty:  " + s.isEmpty());

		String oneTwoThree = "123";
		s.push(oneTwoThree);		
		System.out.println("peek():  " + (s.peek() == oneTwoThree));
		System.out.println("pop():  " + (s.pop() == oneTwoThree));
		System.out.println("pop():  " + (s.pop() == abc));
		s.push(oneTwoThree);		
		s.push(oneTwoThree);		
		s.push(oneTwoThree);
		s.popAll();
		System.out.println("isEmpty() is empty:  " + s.isEmpty());		
	}
}
