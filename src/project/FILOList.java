package project;

public class FILOList<T> {
	public Node<T> head;
	
	public FILOList(){
		head = null;
	}
	public void add(T value){
		Node<T> elem = new Node<T>(value);
		if(head == null) head = elem;
		else{
			Node<T> aux = head;
			while(aux.getNext() != null) aux = aux.getNext();
			aux.setNext(elem);
		}
	}
	
	public T remove(){
		Node<T> aux = head;
		head = head.getNext();
		aux.setNext(null);
		return aux.getElem();
	}
}
