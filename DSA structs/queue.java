package com.company;
import java.util.*;

public class Queue {
    private int[] data;
    public int front; //front index
    public int rear; //rear index
    public int size; //size of the queue

    private Queue(int capacity){
        data = new int[capacity];
        front = -1;
        rear = -1;
        size = 0;
    }

    private Queue(){
        data = new int[5];
        front = -1;
        rear = -1;
        size = 0;
    }
    
    public int peek() {
        if(size == 0) {
            System.out.println("Queue is empty!");
            return -1;
        }
        else {
            return data[front];
        }
    }

    public void enqueue(int a){
        if(size == data.length) {
            doubleCapacity();
        }
        if(front == -1){
            front++;
            rear++;
            size++;
            data[rear] = a;
        }
        else {
            if(rear == data.length - 1) {
                rear = 0;
                data[rear] = a;
            }
            else {
                rear++;
                data[rear] = a;
            }
            size++;
        }
    }

    private void doubleCapacity() {
        int[] temp = data;
        data = new int[temp.length * 2];
        int index = 0;
        for(int i = front; i < temp.length; i++) {
            data[index] = temp[i];
            index++;
        }
        if(front > rear) {
            for(int i = 0; i <= rear; i++) {
                data[index] = temp[i];
                index++;
            }
        }
        front = 0;
        rear = temp.length - 1;
    }

    public int dequeue(){
        if(size == 0) {
            System.out.println("Queue is full!");
            return -1;
        }
        int returnVal = data[front];
        size--;
        front++;
        if(front == data.length) {
            front = 0;
        }
        return returnVal;
    }

    public int size(){
        return size;
    }

    public boolean empty(){
        if(size == 0){
            return true;
        }
        else return false;
    }

    public static void main(String[] args) {
        Queue q = new Queue();
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for(int i = 1; i <= n; i++) {
            q.enqueue(i);
        }
        while(!q.empty()) {
            System.out.println(q.dequeue());
        }
    }
}
