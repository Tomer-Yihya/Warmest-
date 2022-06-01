import java.util.HashMap;

public class WarmestHistoryList<K,V> {

	private node head = null;
	private node last = null;
	private HashMap<K,node> Map = new HashMap<K,node>();
	
	
	
	public void insert (K key ,V val) {
		if(key == null || val == null)
			return;
		
		// look for the key, if found it will be deleted 		
		remove(key);

		// insert as last
		insertAsLast(key,val);

	}
	
	public V remove (K key) {
		if(this.Map.size() == 0 || key == null) 
			return null;
		
		// check if the List contains the key
		node node = this.Map.get(key);
		if(node == null)
			return null;

		// value to return
		V val = node.val;
		
		// update pointers
		//if node == head =>update head
		if(node.equals(this.head)) {
			this.head = this.head.next;
		}
		// if node == last =>update last
		if(node.equals(this.last)) {
			this.last = this.last.prev;
		}
		node.next.prev = node.prev;
		node.prev.next = node.next;
		
		// remove from the map to
		this.Map.remove(node.key);
		node.key = null;
		node.val = null;
		
		return val;
	}
	
	private void insertAsLast(K key,V val) {
		
		if(key == null || val == null)
			return;
		
		node node = new node(key,val);
		if(this.Map.containsKey(key)) {
			this.Map.remove(key);
		}
		this.Map.put(key, node);
		
		// List.size() == 0
		if(head == null) {
			this.head = node;
			this.last = node;
			return;
		}
		
		//List.size() == 1
		if(this.head.equals(this.last)) {
			node.prev = this.head;
			node.next = this.head;
			this.head.next = node;
			this.head.prev = node;
			this.last = node;
			node = null;
			return;
		}
		
		//List.size() > 1
		node.prev = this.last;
		node.next = this.head;
		this.last.next = node;
		this.head.prev = node;
		this.last = node;
		node = null;
	}
	
	public K getKey (K key) {
		node node = this.Map.get(key);
		return node != null ? node.key : null;
	}
	
	public V getValue (K key) {
		node node = this.Map.get(key);
		return node != null ? node.val : null;
	}
	
	public node getLast () {
		return this.last;
	}
	
	public void setLast (node last) {
		this.last = last;
	}
	
	public node getHead () {
		return this.head;
	}
	
	public void setHead (node head) {
		this.head = head;
	}
	
	public class node {
		
		private K key;
		private V val;
		private node next;
		private node prev;
	
		
		public node(K key, V val) {
			this.key = key;
			this.val = val;
			this.next = this;
			this.prev = this;
		}
			
		public node getNext () {
			return this.next; 
		}
		
		public void setNext (node nextNode) {
			this.next = nextNode; 
		}
		
		public node getprev () { 
			return this.prev; 
		}
		
		public void setPrev (node prevNode) { 
			this.prev = prevNode; 
		}
		
		public V getVal () { 
			return this.val; 
		}
		
		public void setVal (V val) { 
			this.val = val; 
		}
		
		public K getKey () { 
			return this.key; 
		}
		
		public void setKey (K key) { 
			this.key = key; 
		}
		
		public boolean equals(node o1, node o2) {
			if(!o1.key.equals(o2.key))
				return false;
			
			if(!o1.val.equals(o2.val))
				return false;
			
			return true;
		}
	}
	
}
