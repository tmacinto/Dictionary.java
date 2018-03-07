// Tyler MacIntosh, tmacinto, 13B, 2.8.18
// Dictionary.java
// implements a Dictionary ADT

public class Dictionary implements DictionaryInterface{

        // private inner Node class
        private class Node{
                String key;
                String value;
                Node next;

                Node(String k, String v){
                        key = k;
                        value = v;
                        next = null;
                }
        }

        // Fields for the Dictionary class
        private Node head;      //reference to first Node in List
        private int numItems;   //number of items in this Dictionary

        // Dictionary()
        // constructor for the Dictionary class
        public Dictionary(){
                head = null;
                numItems = 0;
        }

        private Node findKey(String key){
                Node n = head;
                while(n != null) {
                        if(n.key.equals(key)) {
                                return n;
                        } else {
                                n = n.next;
                        }
                }
                return null;
        }

        // isEmpty()
        // pre: none
        // post: returns true if this Dictionary is empty, false otherwise
        public boolean isEmpty() {
                return(numItems == 0);
        }

        // size()
        // pre: none
        // post: returns the number of entries in this Dictionary
        public int size() {
                return numItems;
        }

        //lookup()
        //pre: none
        //returns value associated key, or null reference if no such key exists
        public String lookup(String key) {
                Node N = findKey(key);
                if(N == null) {
                        return null;
                }else {
                        return N.value;
                }
        }

        //insert()
        //inserts new (key,value) pair into this Dictionary
        //pre: lookup(key)==null
        public void insert(String key, String value) throws DuplicateKeyException{
                if( findKey(key) != null) {
                        throw new DuplicateKeyException(
                                "Dictionary Error: cannot insert duplicate keys");
                }
                if( lookup(key) == null) {
                        // if list is empty, create first Node head
                        if( isEmpty() == true) {
                                Node N = new Node(key, value);
                                N.next = head;
                                head = N;
                        }
                        // if there are already keys, make last Node this pair
                        else {
                                Node N = new Node(key, value);
                                Node n = head;
                                while(n.next != null) {
                                        n = n.next;
                                }
                                n.next = N;
                                N.next = null;
                        }
                }
                numItems++;
        }

        //delete()
        //deletes pair with the given key
        //pre: lookup(key)!=null
        public void delete(String key) throws KeyNotFoundException{
                if( findKey(key) == null) {
                        throw new KeyNotFoundException(
                                "Dictionary Error: cannot delete non-existent key");
                }
                Node N = findKey(key);
                if(lookup(key) != null) {
                        // if key == head, remove it from list
                        // set head == head.next
                        if(head.key.equals(key)) {
                                Node temp = head;
                                head = head.next;
                                temp.next = null;
                        } else {
                                Node P; //parent
                                Node S; //successor
                                P = head;
                                S = head.next;
                                while(S != N) {
                                        P = S;
                                        S = S.next;
                                }
                                P.next = S.next;
                        }
                }
                numItems--;
        }

        // makeEmpty()
        // pre: none
        public void makeEmpty(){
                head = null;
                numItems = 0;
        }

        //toString()
        //returns a string representation of this dictionary
        //overrides Object's toString() method
        //pre: none
        public String toString(){
                StringBuffer sb = new StringBuffer();
                Node N = head;
                for( ; N!=null; N=N.next){
                        sb.append(N.key).append(" ").append(N.value).append("\n");
                }
                return new String(sb);
        }
}
