package com.jape.course;

public class ThreadSaleOfTickets {
	public static void main(String[] args) {
		saleThread sale = new saleThread();//����
		Thread thread1 = new Thread(sale,"����1");
		Thread thread2 = new Thread(sale,"����2");
		Thread thread3 = new Thread(sale,"����3");
		Thread thread4 = new Thread(sale,"����4");
		Thread thread5 = new Thread(sale,"����5");
		thread1.start();
		System.out.println(thread1.getName()+":�Ѿ�������");
		thread2.start();
		System.out.println(thread2.getName()+":�Ѿ�������");
		thread3.start();
		System.out.println(thread3.getName()+":�Ѿ�������");
		thread4.start();
		System.out.println(thread4.getName()+":�Ѿ�������");
		thread5.start();
		System.out.println(thread5.getName()+":�Ѿ�������");
	}
}

class saleThread implements Runnable{
	static int tickets = 100;
	public void run() {
		while(true) {
			System.out.println(Thread.currentThread().getName()+":׼������ͬ����Ʊ����飡");
			saleTicket();
			if(tickets == 0) {
				System.out.println(Thread.currentThread().getName()+":���̽�����");
				break;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public synchronized void saleTicket() {
		if(tickets > 0) {
			System.out.println(Thread.currentThread().getName()+":������"+tickets+"��Ʊ��");
			tickets--;
		}
		else {
			System.out.println(Thread.currentThread().getName()+":Ʊ�Ѿ����꣡");
		}
		
	}
}