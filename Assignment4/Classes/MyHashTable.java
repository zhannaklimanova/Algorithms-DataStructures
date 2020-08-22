import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;


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
        this.numBuckets = initialCapacity;
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
    
    public int hashValueFunction(V key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {
        if ((float) this.numEntries/this.numBuckets > MAX_LOAD_FACTOR) {
            rehash();
        }
        int keyLocation = hashFunction(key);
        if (this.buckets.get(keyLocation).isEmpty()) {
            this.buckets.get(keyLocation).add(new HashPair<K,V>(key, value));
            this.numEntries++;
            return null;
        }
        else {
            for(HashPair<K,V> h: this.buckets.get(keyLocation)) {
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
        int keyLocation = hashFunction(key);
        for (HashPair<K,V> h: this.buckets.get(keyLocation)) {
            if (h.getKey().equals(key) && h.getValue() != null) {
                return h.getValue();
            }
        }   
        return null;    
    }
    
    /**
     * Remove the HashPair corresponding to key . Expected average runtime O(1) 
     */
    public V remove(K key) {
        int keyLocation = hashFunction(key);
        for (HashPair<K,V> h: this.buckets.get(keyLocation)) {
            if (h.getKey().equals(key) && h.getValue() != null) {
                V storedValue = h.getValue();
                this.buckets.get(keyLocation).remove(h);
                this.numEntries--;
                return storedValue;
            }
        } 
        return null;
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
        ArrayList<K> keys = new ArrayList<K>(this.numEntries);
        
        for (HashPair<K,V> pair: this) {
            keys.add(pair.getKey());
        }
        return keys;    
    }
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(m) where m is the number of buckets
     */
    public ArrayList<V> values() {
        MyHashTable<V,V> table = new MyHashTable<V,V>(this.numBuckets);

        for (HashPair<K,V> pair: this) {
            table.put(pair.getValue(), pair.getValue());
        }

        ArrayList<V> values = new ArrayList<V>(table.size());
        for (HashPair<V,V> valuePair: table) {
            values.add(valuePair.getValue());
        }
            
        return values;
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
        ArrayList<HashPair<K,V>> unsortedPairs = new ArrayList<HashPair<K,V>>(results.size());
        ArrayList<K> sortedResults  = new ArrayList<K>(results.size());
        
        for (HashPair<K,V> pair: results) {
            unsortedPairs.add(pair);
        }
        quickSort(unsortedPairs, 0, unsortedPairs.size()-1);
        
        for (HashPair<K,V> pair: unsortedPairs) {
            sortedResults.add(pair.getKey());
        }
        
        return sortedResults;
    }
    
    private static <K, V extends Comparable<V>> void quickSort(ArrayList<HashPair<K,V>> unsortedPairs, int leftSide, int rightSide) {
        if (leftSide >= rightSide) { // base case where there is only one element which does not need sorting
            return;
        } 
        else {
            int i = placeAndDivide(unsortedPairs, leftSide, rightSide); // i is index where the pivot is placed
            quickSort(unsortedPairs, leftSide, i-1); 
            quickSort(unsortedPairs, i+1, rightSide);
        }
    }
     
    private static <K, V extends Comparable<V>> int placeAndDivide(ArrayList<HashPair<K,V>> list, int leftSide, int rightSide) {
        HashPair<K,V> pivot = list.get(rightSide); // pick the rightmost element for the pivot
        int wall = leftSide-1; // place wall to the left 
        
        for (int i = leftSide; i < rightSide; i++) { // go through all elements and compare them to the pivot
            if (list.get(i).getValue().compareTo(pivot.getValue()) > 0) {
                wall++;  // move wall 
                HashPair<K,V> temp = list.get(wall); // move element behind wall
                list.set(wall, list.get(i));
                list.set(i, temp);
            }
        }
        
        HashPair<K,V> temp = list.get(wall+1); // move pivot next to the wall
        list.set(wall+1,list.get(rightSide));
        list.set(rightSide, temp);
        
        return wall+1;
    }

    
    
    
    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }   
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {
        private ArrayList<HashPair<K,V>> pairs = new ArrayList<HashPair<K,V>>(size());
        private int i = 0;
        /**
         * Expected average runtime is O(m) where m is the number of buckets
         */
        private MyHashIterator() {
            for (LinkedList<HashPair<K,V>> list: getBuckets()) {
                for (HashPair<K,V> pair: list) {
                    pairs.add(pair);
                }
            }
        }
        
        @Override
        /**
         * Expected average runtime is O(1)
         */
        public boolean hasNext() {
            if (i < pairs.size()) {
                return true;
            }
            return false;
        }
        
        @Override
        /**
         * Expected average runtime is O(1)
         */
        public HashPair<K,V> next() {
            if (pairs.get(i) != null) {
                return pairs.get(i++);
            }
            else {
                throw new NoSuchElementException("There's no more elements!");
            }
        }
        
    }
}
