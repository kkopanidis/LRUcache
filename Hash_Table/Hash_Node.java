package Hash_Table;

/**
 * ListNode represents a signe-link list node
 * Each node contains an Object reference to data and a reference to the nextNode in the list. 
 */
class Hash_Node<K,V>
{
	protected K key;
	private V value;
    protected Hash_Node<K,V> next,previous,nextA,nextB;//node A is next downwards nodeB is previous upwards


    /**
	 * Constructor. It initializes data and sets next node to null 
	 * @param object a reference to node's data
	 */
	Hash_Node( K key,V value) {
        this(key,value,null,null,null,null);

	}
    Hash_Node(K key,V value,Hash_Node<K,V> nodep,Hash_Node<K,V> noden,Hash_Node<K,V> nodeA,Hash_Node<K,V> nodeB){
        this.key=key;
        this.value=value;
        this.previous=nodep;
        this.next=noden;
        this.nextA=nodeA;
        this.nextB=nodeB;
    }

	/**
	 * Get reference to next node
	 * @return the next node
	 */
	V getValue()
	{
		return value;
	}
    boolean equal(K key)
    {
        return this.key.equals(key);
    }

}