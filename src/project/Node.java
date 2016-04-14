package project;

public class Node<T> {
	private Node<T> next;
	private T elem;
	
	public Node(T elem, Node<T> next){
		this.elem = elem;
		this.next = next;
	}
	
	public Node(T elem){
		this(elem,null);
	}
	
	public Node<T> getNext(){
		return this.next;
	}
	
	public T getElem(){
		return this.elem;
	}
	
	public void setNext(Node<T> next){
		this.next = next;
	}
}
