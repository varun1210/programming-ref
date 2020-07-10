package com.company;

import java.util.*;

public class LinkedListInput{
    //removing consecutive duplicates
    public static Node<Integer> removeDuplicates(Node<Integer> head) {
        if(head.next == null){
            return head;
        }
        Node<Integer> temp = head;
        Node<Integer> temp2 = head.next;
        while(temp.next != null && temp2.next != null){
            if(temp.data.equals(temp2.data)){
                temp2 = temp2.next;
            }
            else{
                temp.next = temp2;
                temp2 = temp2.next;
                temp = temp.next;
            }
        }
        if(temp2.next == null){
            if(temp.data.equals(temp2.data)){
                temp.next = null;
            }
            else{
                temp.next = temp2;
            }
        }
        return head;
    }

    //printing the linked list and taking input linked list
    public static void linkedListPrinter(Node x){
        Node temp = x;
        while(temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    public static Node<Integer> linkedListInput(){
        Scanner input = new Scanner(System.in);
        int data = input.nextInt();
        Node<Integer> head = null;
        while(data != -1){
            Node<Integer> currentNode = new Node<Integer>(data);
            if(head == null){
                head = currentNode;
                data = input.nextInt();
            }
            else{
                Node<Integer> tail = head;
                while(tail.next != null){
                    tail = tail.next;
                }
                tail.next = currentNode;
                data = input.nextInt();
            }
        }
        return head;
    }

    //inserting node in linked list
    public static Node<Integer> linkedListInserter(Node x, int data, int pos){
        Node<Integer> temp = x;
        if(pos == 0){
            temp = x;
            Node<Integer> newNode = new Node(data);
            newNode.next = x;
            return newNode;
        }
        else{
            Node<Integer> newNode = new Node(data);
            Node<Integer> prevNode = x;
            int count = 0;
            while(count < pos - 1 && prevNode != null){
                prevNode = prevNode.next;
                count++;
            }
            if(prevNode == null){
                return x;
            }
            else {
                newNode.next = prevNode.next;
                prevNode.next = newNode;
            }
            return x;
        }
    }
    //reversing a linkedList

    public static Node<Integer> reverseLinkedList(Node<Integer> head) {
        if(head == null){
            return null;
        }
        if(head.next == null){
            return head;
        }
        Node<Integer> nextHead = reverseLinkedList(head.next);
        head.next.next = head;
        head.next = null;
        return nextHead;
    }
    public static void main(String args[]){
        Node<Integer> z = likedListInput();
        linkedListprinter(z);
        Node<Integer> y = reverseLinkedList(z);
        linkedListPrinter(y);
    }
}
class Node<T>{
    T data;
    Node next;
    Node(T data) {
        this.data = data;
        next = null;
    }
}
