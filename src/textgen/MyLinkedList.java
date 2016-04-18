package textgen;

import java.util.AbstractList;

import javax.imageio.IIOException;

import sun.java2d.pipe.NullPipe;


/** A class that implements a doubly linked list
 *
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = new LLNode<E>(null, null, tail);
		tail = new LLNode<E>(null, head, null);
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element)
	{
		add(size, element);
		return true;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element)
	{
		if(element == null)
			throw new NullPointerException("Element value is null. Can`t add");

		//if an empty list
		if(size == 0){
			LLNode<E> newNode = new LLNode<E>(element, head, tail);
			head.next = newNode;
			tail.prev = newNode;
			size++;
			return;
		}

		LLNode<E> currNode;
		int currIndex = 0;
		//Validity of index
		if(index<0 || index>size)
			throw new IndexOutOfBoundsException("Not a valid index");

		for(currNode=head.next; currNode.next!=null; currNode = currNode.next){
			if(index!=currIndex){
				currIndex++;
				continue;
			}
			break;
		}
		//Add the node
		LLNode<E> newNode = new LLNode<E>(element, currNode.prev, currNode);
		currNode.prev.next = newNode;
		currNode.prev = newNode;
		size++;

	}


	/** Get the element at position index
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index)
	{
		LLNode<E> currNode;
		int currIndex = 0;
		//Validity of index
		if(index<0 || index>=size)
			throw new IndexOutOfBoundsException("Not a valid index");

		for(currNode=head.next; currNode.next!=null; currNode=currNode.next){
			if(index!=currIndex){
				currIndex++;
				continue;
			}
			break;
		}
		return currNode.data;
	}


	/** Return the size of the list */
	public int size()
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 *
	 */
	public E remove(int index)
	{
		LLNode<E> currNode;
		int currIndex = 0;

		if(index<0 || index>=size)
			throw new IndexOutOfBoundsException("Not a valid index");

		for(currNode=head.next; currNode.next!=null; currNode=currNode.next){
			if(index!=currIndex){
				currIndex++;
				continue;
			}
			break;
		}
		currNode.prev.next = currNode.next;
		currNode.next.prev = currNode.prev;
		size--;
		return currNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element)
	{
		int currIndex = 0;
		LLNode<E> currNode;

		if(element == null)
			throw new NullPointerException("Element value is null. Can`t set");

		if(index<0 || index>=size)
			throw new IndexOutOfBoundsException("Invalid index");

		for(currNode = head.next; currNode.next!=null; currNode=currNode.next){
			if(index!=currIndex){
				currIndex++;
				continue;
			}
			break;
		}

		E oldData = currNode.data;
		currNode.data = element;
		return oldData;
	}
}

class LLNode<E>
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// E.g. you might want to add another constructor
	public LLNode(E e)
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	public LLNode(E e, LLNode<E> prev, LLNode<E> next){
		this.data = e;
		this.prev = prev;
		this.next = next;
	}

}
