package com.jdc.mkt.test;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Account;

public class AccountTest extends JPAFactory {

	//@Test
	void testAccount() {
		
		var opeOne = getThreadOne(); 
		var opeTwo = getThreadTwo(); 
		opeOne.start();
		opeTwo.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testCallBack() {
		var acc = new Account("William",50_000);
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(acc);
		//em.remove(acc);
		em.getTransaction().commit();
		em.close();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		var acc1 = em.find(Account.class, 1);
		acc1.setBalance(1000_000);
		em.getTransaction().commit();
	}
	
	

	

	private Thread getThreadOne() {
		return new Thread(() ->{
			System.out.println("Starting Thread One");
			var em = emf.createEntityManager();		
			var acc = em.find(Account.class, 1);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			em.getTransaction().begin();
			System.out.println("Before add to Thread One balance :%s".formatted(acc.getBalance()));
			acc.setBalance(acc.getBalance()+50_000);
			em.flush();
			System.out.println("After add to Thread One balance :%s".formatted(acc.getBalance()));
			em.getTransaction().commit();
		});
		
	}
	
	private Thread getThreadTwo() {
		return new Thread(() ->{
			System.out.println("Starting Thread Two");
			var em = emf.createEntityManager();
			var acc = em.find(Account.class, 1);
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			em.getTransaction().begin();
			em.refresh(acc);
			System.out.println("Before minus to Thread Two balance :%s".formatted(acc.getBalance()));
			acc.setBalance(acc.getBalance()-50_000);
			System.out.println("After minus to Thread Two balance :%s".formatted(acc.getBalance()));
			em.getTransaction().commit();
		});
	}
	
}
