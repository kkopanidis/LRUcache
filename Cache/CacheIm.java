package Cache;
import Hash_Table.*;

public class CacheIm<K,V> implements Cache<K,V>{

    private LinkedHashTable<K,V> table;
    private double ratio;
    private long hits,misses;

    public CacheIm(int size){
        table=new LinkedHashTable<K,V>(size);
    }
    @Override
    public V lookUp(K key) {
        V temp;
        temp= table.find(key);
        if(temp==null)
        misses++;
        else
        hits++;
        return temp;
    }

    @Override
    public void store(K key, V value) {
    table.add(key,value);
    }

    @Override
    public double getHitRatio() {
        return (hits/(double)(misses+hits));
    }

    @Override
    public long getHits() {
        return hits;
    }

    @Override
    public long getMisses() {
        return misses;
    }

    @Override
    public long getNumberOfLookUps() {
        return (hits+misses);
    }
}
