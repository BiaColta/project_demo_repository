package com.example.demo;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class NumberArrangerTest {
	private NumberArranger numberArranger;

	@Before
	public void setup(){
		numberArranger=new NumberArranger();
	}

	@Test
	public void withGivenListAndNumber_shouldReturnTheFilteredList(){
		List<Integer> integers = Arrays.asList(50,5,4, 10, 80, 90);
		int i = 10;

		List<Integer> result = numberArranger.filter(integers, i);

		assertEquals(4, result.size());
		assertTrue(result.containsAll(Arrays.asList(10, 50, 80, 90)));
	}
}