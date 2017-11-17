package inf1010.assignment;

import java.util.Iterator;
import inf1010.lib.two.IfiCollection;
import java.util.NoSuchElementException;

public class OrderedSet<E extends Comparable<? super E>> implements
IfiCollection<E> {
	Node forste;
	int size;
/**
 * @param e
 * add e in list
 * 
 */
	public boolean add(E e) {
		System.out.println("add");
		if(e == null) {
			throw new NullPointerException() ;
		}
		if (contains(e) == false) {
		Node tmp = forste ;
		if(forste == null) {
			forste = new Node(e);
			size++;
			return true;
		}
		else if (forste.data.compareTo(e) > 0) {
			Node ny = new Node(e);
			ny.neste = forste;
			forste = ny;
			size++;
			return true ;
		}
		else if(forste.data.compareTo(e) == 0){
			return false;
		}
		while (tmp.neste != null) {
			if (tmp.data.compareTo(e) == 0) {
				return false ;
			}
			if (tmp.neste.data.compareTo(e) > 0){
				Node ny = new Node(e);
				ny.neste = tmp.neste;
				tmp.neste = ny;
				size++;
				return true;
			}
			tmp = tmp.neste;
		}
		tmp.neste = new Node(e);
		size++;
		return true;
	}
		return false;
	}

	public boolean contains(E e) {
		System.out.println("contains");
		if(e == null) {
			throw new NullPointerException() ;
		}
		Node tmp = forste;
		while (tmp != null) {
			if (tmp.data.compareTo(e) == 0) {
				return true;
			}
			tmp = tmp.neste;
		}

		return false;
	}

	public boolean remove(E e) {
		System.out.println("remove");
		if(e == null) {
			throw new NullPointerException() ;
		}
		Node tmp = forste ;
		if (forste != null) {
			if (forste.data.compareTo(e) == 0) {
				forste = forste.neste;
				size--;
				return true;
			}
			while (tmp.neste != null) {
				if (tmp.neste.data.compareTo(e) == 0) {
					if (tmp.neste.neste == null) {
						tmp.neste = null;
						size--;
						return true;
					}
					else {
						tmp.neste = tmp.neste.neste;
						size--;
						return true;
					}
				}
				tmp = tmp.neste;
			}
		}
		return false;
	}

	public int size() {
		System.out.println("size");
		return size;
	}
	
	public boolean isEmpty() {
		System.out.println("isEmpty");
		if (forste == null) {
			return true;
		}
		return false;
	}

	public void clear() {
		System.out.println("clear");
		forste = null;
		size = 0;
	}
	public E get(int index) {
		System.out.println("index");
		Node tmp = forste;
		int teller = 0;
		while (tmp != null) {
			if (teller == index){
				return tmp.data;
			}
			teller++;
			tmp = tmp.neste;
		}
		throw new IndexOutOfBoundsException();
	}

	public E[] toArray(E[] a) {
		System.out.println("toArray");
		if (a == null) {
			throw new NullPointerException() ;
		}
		Node tmp = forste ;
			if(a.length >= size) {
				for (int i=0; i < a.length; i++) {
					if (tmp != null) {
						a[i] = tmp.data;
						tmp = tmp.neste;
					}
					else {
					a[i] = null;
					}
				}
				return a;
			}
		return null;
	}
	
	public Iterator<E> iterator() {
		return new MyIterator();
	}
	class Node{
		Node(E e){
			data = e;
		}
		Node neste;
		E data;
	}
	class MyIterator implements Iterator<E> {
		Node current;
		Node forrige;
		boolean ones = false;
		public MyIterator() {
			current = forste;
		}
		
		public boolean hasNext() {
			if(current == null) {
				return false;
			} else {
				return true;
			}
		}
		public E next() {
			if (current != null) {
				forrige = current;
				current = current.neste;
				ones = true;
			return forrige.data;
			}
			else {
				throw new NoSuchElementException();
			}
		}
		public void remove() {
			if (ones != true) {
				throw new IllegalStateException();
				}
			OrderedSet.this.remove(forrige.data);
			ones = false;
		}
	}
}

