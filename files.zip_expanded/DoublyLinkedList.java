public class DoublyLinkedList<T> implements List<T> {
	private Node head, tail;
	private int numberOfElements;

	
	public DoublyLinkedList() {
		head = null;
		tail = null;
		numberOfElements = 0;
	}
	
	@Override
	public void addLast(T item) {
		// TODO 
		Node newNode = new Node(item);
        if (head == null) { 
            head = newNode;
            tail = newNode;
        } else { 
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        numberOfElements++;
	}

	@Override
	public void addFirst(T item) {
		// TODO 
	    Node newNode = new Node(item);
	    if (head == null) {
	        head = newNode;
	        tail = newNode;
	    } else {
	        newNode.next = head;
	        head.previous = newNode;
	        head = newNode;
	    }
	    numberOfElements++;
	}

	@Override
	public T get(int position) {
		// TODO 
		if (position < 0 || position >= numberOfElements) {
	        return null;
	    } else {
	        Node current = head;
	        for (int i = 0; i < position; i++) {
	            current = current.next;	
	        }
	        return current.data;
	    }
	}

	@Override
	public void print() {
		// TODO
		if (head == null) {
			System.out.println("List is empty.");
		} else {
			Node current = head;
			while (current != null) {
				System.out.print(current.data + " ");
				current = current.next;
				}
			System.out.println();
			}			
	}

	@Override
	public void printBackwards() {
		// TODO
		if (tail == null) {
	        System.out.println("List is empty.");
	    } else {
	        Node current = tail;
	        while (current != null) {
	            System.out.print(current.data + " ");
	            current = current.previous;
	            }
	        System.out.println();
	        }
	}

	@Override
	public boolean remove(T item) {
		// TODO 
		if (head == null) {
	        // List is empty
	        return false;
	    }
		
	    Node current = head;
	    
	    // Traverse the list until the item is found or the end of the list is reached
	    while (current != null && !current.data.equals(item)) {
	        current = current.next;
	    }
	    if (current == null) {
	        // Item not found in list
	        return false;
	    }
	    if (current == head) {
	        // Item is at the head of the list
	        head = current.next;
	        if (head != null) {
	            head.previous = null;
	        } else {
	            tail = null;
	        }
	    } else if (current == tail) {
	        // Item is at the tail of the list
	        tail = current.previous;
	        tail.next = null;
	    } else {
	        // Item is in the middle of the list
	        current.previous.next = current.next;
	        current.next.previous = current.previous;
	    }
	    numberOfElements--;
	    return true;
		
	}

	@Override
	public boolean isEmpty() {
		// TODO 
		if(numberOfElements == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getLength() {
		// TODO 
		return numberOfElements;
	}
	
	/** 
	 * Inner class representing a node in the linked list
	 */

	private class Node
	{
		private T data;
		private Node next, previous;

		private Node(T data) {
			this(data,null,null);
		}

		private Node (T data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.previous = prev;
		}
	}

}