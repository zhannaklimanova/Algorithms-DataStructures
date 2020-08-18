import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
    // num of entries to the table (preferable to have this value be smaller than numBuckets)
    private int numEntries;
    // num of buckets 
    private int numBuckets; // also called capacity
    // load factor needed to check for rehashing 
    private static final double MAX_LOAD_FACTOR = 0.75;
    // ArrayList of buckets. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<HashPair<K,V>>> buckets; 
    
    // constructor
    public MyHashTable(int initialCapacity) {
        try {
            this.numBuckets = initialCapacity;
        }
        catch(Exception e) {
            System.out.println("Cannot divide by zero!");
        }   
        this.numEntries = 0;
        this.buckets = new ArrayList<LinkedList<HashPair<K,V>>>(this.numBuckets);
        for (int i = 0; i < this.numBuckets; i++) {
            this.buckets.add(i, new LinkedList<HashPair<K,V>>());
        }
    }
        
    
    public int size() {
        return this.numEntries;
    }
    
    public boolean isEmpty() {
        return this.numEntries == 0;
    }
    
    public int numBuckets() {
        return this.numBuckets;
    }
    
    /**
     * Returns the buckets variable. Useful for testing  purposes.
     */
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    
    /**
     * Given a key, return the bucket position for the key. 
     */
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {
        int keyLocation = hashFunction(key);
        if ((float) this.numEntries/this.numBuckets > MAX_LOAD_FACTOR) {
            rehash();
        }
        
        if (this.buckets.get(keyLocation).isEmpty()) {
            this.buckets.get(keyLocation).add(new HashPair<K,V>(key, value));
            this.numEntries++;
            return null;
        }
        else {
            for(HashPair<K,V> h : this.buckets.get(keyLocation)) {
                if (h.getKey().equals(key) && h.getValue() != null) {
                    V oldValue = h.getValue();
                    h.setValue(value);
                    return oldValue;
                }
            }
        }
        this.buckets.get(keyLocation).addLast(new HashPair<K, V>(key, value));
        this.numEntries++;
        return null;
    }
    
    
    /**
     * Get the value corresponding to key. Expected average runtime O(1)
     */
    
    public V get(K key) {
        //ADD YOUR CODE BELOW HERE
        
        return null;
        
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Remove the HashPair corresponding to key . Expected average runtime O(1) 
     */
    public V remove(K key) {
        //ADD YOUR CODE BELOW HERE
        
        return null;
        
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /** 
     * Method to double the size of the hashtable if load factor increases
     * beyond MAX_LOAD_FACTOR.
     * Made public for ease of testing.
     * Expected average runtime is O(m), where m is the number of buckets
     */
    public void rehash() {
        ArrayList<LinkedList<HashPair<K,V>>> newBuckets = new ArrayList<LinkedList<HashPair<K,V>>>(this.numBuckets * 2);
        this.numBuckets *= 2;
        for (int i = 0; i < this.numBuckets; i++) {
            newBuckets.add(i, new LinkedList<HashPair<K,V>>());
        }
        
        for (LinkedList<HashPair<K,V>> list: this.buckets) {
            for (HashPair<K,V> pair: list) {
                newBuckets.get(hashFunction(pair.getKey())).add(pair);
            }
        }
        this.buckets = newBuckets;
    }
    
    
    /**
     * Return a list of all the keys present in this hashtable.
     * Expected average runtime is O(m), where m is the number of buckets
     */
    
    public ArrayList<K> keys() {
        //ADD YOUR CODE BELOW HERE
        
        return null;
        
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(m) where m is the number of buckets
     */
    public ArrayList<V> values() {
        //ADD CODE BELOW HERE
        
        return null;
        
        //ADD CODE ABOVE HERE
    }
    
    
    /**
     * This method takes as input an object of type MyHashTable with values that 
     * are Comparable. It returns an ArrayList containing all the keys from the map, 
     * ordered in descending order based on the values they mapped to. 
     * 
     * The time complexity for this method is O(n^2), where n is the number 
     * of pairs in the map. 
     */
    public static <K, V extends Comparable<V>> ArrayList<K> slowSort (MyHashTable<K, V> results) {
        ArrayList<K> sortedResults = new ArrayList<>();
        for (HashPair<K, V> entry : results) {
            V element = entry.getValue();
            K toAdd = entry.getKey();
            int i = sortedResults.size() - 1;
            V toCompare = null;
            while (i >= 0) {
                toCompare = results.get(sortedResults.get(i));
                if (element.compareTo(toCompare) <= 0 )
                    break;
                i--;
            }
            sortedResults.add(i+1, toAdd);
        }
        return sortedResults;
    }
    
    
    /**
     * This method takes as input an object of type MyHashTable with values that 
     * are Comparable. It returns an ArrayList containing all the keys from the map, 
     * ordered in descending order based on the values they mapped to.
     * 
     * The time complexity for this method is O(n*log(n)), where n is the number 
     * of pairs in the map. 
     */
    
    public static <K, V extends Comparable<V>> ArrayList<K> fastSort(MyHashTable<K, V> results) {
        //ADD CODE BELOW HERE
        
        return null;
        
        //ADD CODE ABOVE HERE
    }

    
    
    
    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }   
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {
        //ADD YOUR CODE BELOW HERE
        
        //ADD YOUR CODE ABOVE HERE
        
        /**
         * Expected average runtime is O(m) where m is the number of buckets
         */
        private MyHashIterator() {
            //ADD YOUR CODE BELOW HERE
            
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        /**
         * Expected average runtime is O(1)
         */
        public boolean hasNext() {
            //ADD YOUR CODE BELOW HERE
            
            return false;
            
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        /**
         * Expected average runtime is O(1)
         */
        public HashPair<K,V> next() {
            //ADD YOUR CODE BELOW HERE
            
            return null;
            
            //ADD YOUR CODE ABOVE HERE
        }
        
    }
}
