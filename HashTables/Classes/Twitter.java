import java.util.ArrayList;

public class Twitter {
    
    ArrayList<Tweet> tweets;
    ArrayList<String> stopWords;
    MyHashTable<String, Tweet> authorTable; // the key is an author; the value is a tweet made by author
    MyHashTable<String, ArrayList<Tweet>> dateTable;
    MyHashTable<String, ArrayList<String>> wordTable;
    MyHashTable<String, String> stopWordsTable; 
    
    // O(n+m) where n is the number of tweets, and m the number of stopWords
    public Twitter(ArrayList<Tweet> tweets, ArrayList<String> stopWords) {
        this.tweets = tweets;
        this.stopWords = stopWords;
        
        this.stopWordsTable = new MyHashTable<String, String>(this.stopWords.size());
        for (String stopWord: stopWords) {
            addToStopWordsTable(stopWord);
        }
        
        this.authorTable = new MyHashTable<String, Tweet>(this.tweets.size());
        for (Tweet tweet: tweets) {
            addToAuthorTable(tweet);            
        }
        
        this.dateTable = new MyHashTable<String, ArrayList<Tweet>>(this.tweets.size());
        for (Tweet tweet: tweets) {
            addToDateTable(tweet);
        }
        
        this.wordTable = new MyHashTable<String, ArrayList<String>>(this.tweets.size());
        for (Tweet tweet: tweets) {
            addToWordTable(tweet);
        }
    }

    private void addToStopWordsTable(String stopWord) {
        String s = this.stopWordsTable.get(stopWord.toLowerCase());
        if (s == null) {
            this.stopWordsTable.put(stopWord.toLowerCase(), stopWord.toLowerCase());
        }
    }
    
    /**
     * Adds a tweet to the author table making sure to overwrite the least recent tweet with the most recent one.
     * @param tweet
     */
    private void addToAuthorTable(Tweet tweet) {
        Tweet t = this.authorTable.get(tweet.getAuthor());
        
        if (t != null && tweet.getDateAndTime().compareTo(t.getDateAndTime()) > 0) {
            this.authorTable.put(tweet.getAuthor(), tweet);
        }
        else if (t == null) {
            this.authorTable.put(tweet.getAuthor(), tweet);
        }
    }
    
    /**
     * Adds a tweet to the date. 
     * @param tweet
     */
    private void addToDateTable(Tweet tweet) {
        ArrayList<Tweet> t = this.dateTable.get(tweet.getDateAndTime().substring(0,10));
        
        if (t == null) {
            this.dateTable.put(tweet.getDateAndTime().substring(0,10), new ArrayList<Tweet>());
            this.dateTable.get(tweet.getDateAndTime().substring(0,10)).add(tweet);
        }
        else {
            t.add(tweet);
        }
    }
    
    /**
     * Dissects a tweet into individual words (not including stop words) and places each word into the
     * hash table. The key is the hash code of the word and the value is an ArrayList of words. The size
     * of the ArrayList is the frequency of words. After the words are all in the ArrayList, another 
     * parsing is necessary to remove all the stop word HashPairs from the hash table. 
     */
    private void addToWordTable(Tweet tweet) {
        MyHashTable<String, String> noDuplicates = new MyHashTable<String, String>(30);
        for (String word: getWords(tweet.getMessage())) {
            noDuplicates.put(word.toLowerCase(), word.toLowerCase());
        }
        
        for (HashPair<String, String> word: noDuplicates) {
            if (this.stopWordsTable.get(word.getKey()) == null) {
                if (this.wordTable.get(word.getKey()) == null) {
                    this.wordTable.put(word.getKey(), new ArrayList<String>());
                    this.wordTable.get(word.getKey()).add(word.getKey());
                }
                else {
                    this.wordTable.get(word.getKey()).add(word.getKey());
                }
            }
        }
    }
    
    /**
     * Add Tweet t to this Twitter
     * O(1)
     */
    public void addTweet(Tweet t) {
        addToAuthorTable(t);
        addToDateTable(t);
        addToWordTable(t);
    }
    
    /**
     * Search this Twitter for the latest Tweet of a given author.
     * If there are no tweets from the given author, then the 
     * method returns null. 
     * O(1)  
     */
    public Tweet latestTweetByAuthor(String author) {
        return this.authorTable.get(author);
    }

    /**
     * Search this Twitter for Tweets by `date' and return an 
     * ArrayList of all such Tweets. If there are no tweets on 
     * the given date, then the method returns null.
     * O(1)
     */
    public ArrayList<Tweet> tweetsByDate(String date) {
        return this.dateTable.get(date);
    }
    
    /**
     * Returns an ArrayList of words (that are not stop words!) that
     * appear in the tweets. The words should be ordered from most 
     * frequent to least frequent by counting in how many tweet messages
     * the words appear. Note that if a word appears more than once
     * in the same tweet, it should be counted only once. 
     */
    public ArrayList<String> trendingTopics() {
        MyHashTable<String, Integer> wordCountTable= new MyHashTable<String, Integer>(this.wordTable.size());
        for (HashPair<String, ArrayList<String>> pair: this.wordTable) {
            System.out.println("SIZE " + pair.getValue().size() + "      " + "WORD " + pair.getKey());
            wordCountTable.put(pair.getKey(), pair.getValue().size());          
        }       
        return MyHashTable.fastSort(wordCountTable);    
    }

    /**
     * An helper method you can use to obtain an ArrayList of words from a 
     * String, separating them based on apostrophes and space characters. 
     * All character that are not letters from the English alphabet are ignored. 
     */
    private static ArrayList<String> getWords(String msg) {
        msg = msg.replace('\'', ' ');
        String[] words = msg.split(" ");
        ArrayList<String> wordsList = new ArrayList<String>(words.length);
        for (int i=0; i<words.length; i++) {
            String w = "";
            for (int j=0; j< words[i].length(); j++) {
                char c = words[i].charAt(j);
                if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
                    w += c;
                
            }
            wordsList.add(w);
        }
        return wordsList;
    }
}
