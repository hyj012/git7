package com.itwillbs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//JUnit : 자바 단위 테스트

public class JTest {

	@DisplayName("1 + 2 = 3")
	@Test
	public void test1() {
		int a = 1;
		int b = 2;
		int sum = 3;
		Assertions.assertEquals(sum, a + b);
	}
	
	@DisplayName("1 + 3 = 4")
	@Test
	public void test2() {
		int a = 1;
		int b = 3;
		int sum = 3;
		Assertions.assertEquals(sum, a + b);
	}
	
}
