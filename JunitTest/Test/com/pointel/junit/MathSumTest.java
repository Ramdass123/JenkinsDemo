package com.pointel.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MathSumTest {

	@Test
	void test() {
		
		MathSum sum = new MathSum();
		 int value =sum.sumofvalues(10, 10);
		int actualvalues = 10 ;
		assertEquals(actualvalues, value);
	}

}
	