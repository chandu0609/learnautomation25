package com.crm.qa.paralleltests;

import org.testng.annotations.Test;

public class TestClass2 {
	
	
	@Test
	public void testMethod5() {
		System.out.println("TestClass2 -> TestMethod5 " + Thread.currentThread().getId());
	}
	
	@Test
	public void testMethod6() {
		System.out.println("TestClass2 -> TestMethod6 " + Thread.currentThread().getId());
	}
	@Test
	public void testMethod7() {
		System.out.println("TestClass2 -> TestMethod7 " + Thread.currentThread().getId());
	}
	
}
