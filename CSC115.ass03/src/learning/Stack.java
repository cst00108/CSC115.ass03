import java.util.TreeSet;

public class Stack<E>{
	Lego<E> lego = null;


	public static void main(String[] aarg){
		TreeSet<String> t = new TreeSet<>();
		t.add("%#$@");
		String s = t.first();

		Stack<String> stack = new Stack<String>();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		
		/*stack.push("the ");
		stack.push("thingy ");
		stack.push("is ");
		stack.push("a ");
		stack.push("thingy ");
		*/

		stack.print();
	}
	
	public void push(E e){
		Lego<E> lego = new Lego<E>(e);

		lego.next = this.lego;
		this.lego = lego;
	}
	
	public E pop(){
		E toReturn = lego.e;
		lego = lego.next;
		
		return toReturn;
	}

	public void print(){
		Lego<E> lego = this.lego;
		
		for(int index=0; lego!=null; index++){
			System.out.println(lego.e);

 			lego = lego.next;
		}
	}
	
	
	private class Lego<E>{
		Lego<E> next = null;
		E e = null;
		
		private Lego(E e){
			this.e = e;
		}
	}
}