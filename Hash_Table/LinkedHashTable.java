package Hash_Table;

public class LinkedHashTable<K,V> {

	private Hash_Node<K,V>[] array;
    private Hash_Node<K,V> first,last;
    private int hash;
    private K hdat;
	private int elements;
	private int size,actual;

	
	public LinkedHashTable(int arraySize) 
	{
        size=arraySize;
		array = new Hash_Node[actual=5*(size-1)];//was wrong
        first=last=null;
		elements = 0;
	}
	
	void getHash()
	{
            hash =(hash(hdat.hashCode()) % actual  );//was wrong
	}

    int hash(int h){
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

	public void add(K key,V value)
	{
        if(elements==size)//was wrong
            remove();
        hdat=key;
		getHash();
        if(array[hash]==null)
        {
            if(first==null )
            {
                array[hash]=new Hash_Node<K, V>(null,null);
                array[hash].nextA=new Hash_Node<K,V>(key,value);
                first=last=array[hash].nextA;
                array[hash].nextA.nextB=array[hash];

            }
            else if(first==last)
            {
                array[hash] = new Hash_Node<K, V>(null,null);
                array[hash].nextA=new Hash_Node<K,V>(key,value,last,null,null,array[hash]);
                last.next=array[hash].nextA;
                first=array[hash].nextA;
            }
            else
            {
                array[hash] = new Hash_Node<K, V>(null,null);
                array[hash].nextA=new Hash_Node<K,V>(key,value,first,null,null,array[hash]);
                first.next=array[hash].nextA;
                first=first.next;
            }
            elements++;
        }
        else {
            if(first==last)
            {
                array[hash].nextA=new Hash_Node<K,V>(key,value,last,null,array[hash].nextA,array[hash]);
                array[hash].nextA.nextA.nextB=array[hash].nextA;
                last.next=array[hash].nextA;
                first=array[hash].nextA;
                first.next=null;
            }
            else
            {
                array[hash].nextA=new Hash_Node<K,V>(key,value,first,null,array[hash].nextA,array[hash]);
                if(array[hash].nextA.nextA!=null)
                array[hash].nextA.nextA.nextB=array[hash].nextA;
                first.next=array[hash].nextA;
                first=first.next;
            }
            elements++;
        }
	}


	private void remove()   //the problem was here 10% speed increase hits stabilized at 1030
	{
        elements--;
        last.nextB.nextA=last.nextA;
        if(last.nextA!=null)
        {
            last.nextA.nextB=last.nextB;
        }
        last = last.next;
        last.previous = null;
    }

    public V find (K key){              //fixed!
        hdat=key;
        getHash();
        Hash_Node<K,V> node = array[hash];
        if(node!=null)
            node=node.nextA;
        while(node!=null)
        {
            if(node.key!=null && node.key.equals(key)){
                update(node);
                return node.getValue();
            }
            node=node.nextA;
        }
        return null;
    }

    private void update(Hash_Node<K,V> node) {
        if(node==first)
            return;
       else  if(node==last)
        {
            last=last.next;
            last.previous=null;
        }
        if(node.previous != null)  node.previous.next=node.next;
        if(node.next != null) node.next.previous=node.previous;
        first.next=node;
        node.previous=first;
        node.next=null;
        first=node;
    }

}
