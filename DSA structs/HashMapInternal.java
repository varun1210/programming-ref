package com.company;
import java.util.*;
public class HashMapInternal {

    public static void main(String[] args) {
        MyHashMap<String, Integer> h1 = new MyHashMap<String, Integer>();
        h1.insert("varun", 13);
        System.out.println(h1.size());
        System.out.println(h1.getValue("varun"));
        h1.removeKey("varun");
        System.out.println(h1.size());
    }
}

class MyHashMap<K, V> {
    ArrayList<MapNode<K, V>> bucketList;
    private int bucketCount;
    private int entries;

    MyHashMap() {
        bucketList = new ArrayList<MapNode<K, V>>(20);
        bucketCount = 20;
        entries = 0;
        for(int i = 0; i < bucketCount; i++) {
            bucketList.add(null);
        }
    }

    public int size() {
        return entries;
    }

    private int getBucketIndex(K key) {
        int returnVal = key.hashCode();
        return returnVal % bucketCount;
    }

    public void insert(K key, V value) {
        int index = getBucketIndex(key);
        MapNode<K, V> head = bucketList.get(index);
        while(head != null) {
            if(head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        head = bucketList.get(index);
        MapNode<K, V> thisNode = new MapNode<K, V>(key, value);
        thisNode.next = head;
        bucketList.set(index, thisNode);
        entries++;
    }
    
    private void rehash() {
        ArrayList<MapNode<K, V>> temp = bucketList;
        bucketList = new ArrayList<MapNode<K, V>>();
        for(int i = 0; i < 2 * temp.size(); i++) {
            bucketList.add(null);
        }
        bucketCount *= 2;
        entries = 0;
        for(int i = 0; i < temp.size(); i++) {
            MapNode<K, V> thisNode = temp.get(i);
            while(thisNode != null) {
                K key = thisNode.key;
                V value = thisNode.value;
                insert(key, value);
                thisNode = thisNode.next;
            }
        }
    }

    public V getValue(K key) {
        int index = getBucketIndex(key);
        MapNode<K, V> head = bucketList.get(index);
        while(head != null) {
            if(head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V removeKey(K key) {
        int index = getBucketIndex(key);
        MapNode<K, V> head = bucketList.get(index);
        if(head.equals(null)) {
            return null;
        }
        if(head.key.equals(key)) {
            V returnVal = head.value;
            bucketList.set(index, head.next);
            entries--;
            return returnVal;
        }
        while(head.next != null) {
            if(head.next.key.equals(key)) {
                V returnVal = head.next.value;
                head.next = head.next.next;
                entries--;
                return returnVal;
            }
            head = head.next;
        }
        return null;
    }
}


class MapNode<K, V> {
    K key;
    V value;
    MapNode<K, V> next;
    MapNode(K key, V value) {
        this.key = key;
        this.value = value;
        next = null;
    }
}
