package com.crm.qa.paralleltests;

import org.testng.annotations.Test;

public class TestClass1 {
	
	
	@Test
	public void testMethod1() {
		System.out.println("TestClass1 -> TestMethod1 " + Thread.currentThread().getId());
	}
	
	@Test
	public void testMethod2() {
		System.out.println("TestClass1 -> TestMethod2 " + Thread.currentThread().getId());
	}
	@Test
	public void testMethod3() {
		System.out.println("TestClass1 -> TestMethod3 " + Thread.currentThread().getId());
	}
	@Test
	public void testMethod4() {
		System.out.println("TestClass1 -> TestMethod4 " + Thread.currentThread().getId());
	}

}
