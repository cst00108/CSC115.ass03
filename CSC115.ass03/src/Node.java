/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jason Donald
 */
class Node<E> {
	private Node<E> next = null;
	private E e = null;
	
	public Node(E e){
		this.e = e;
	}
	
	public Node<E> add(Node<E> old){
		this.next = old;
		
		return this;
	}
	
	public E get(){
		return e;
	}

	//lets the gc get the current Node and returns the second on the list
	public Node<E> remove(){
		return next;
	}
	
	public static void main(String[] aarg){
		Node<String> n = new Node<String>("abc");
		n = new Node<String>("123").add(n);
		n.print();
	}
	
	private void print(){
		Node<E> n = this;
		
		while(n != null){
			System.out.println(n.e);
			n = n.next;
		}
	}
}
