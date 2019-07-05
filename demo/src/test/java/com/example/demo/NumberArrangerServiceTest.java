package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NumberArrangerServiceTest {

	@InjectMocks
	private NumberArrangerService service;

	@Mock
	private NumberArranger numberArranger;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void withGivenListAndNumber_shouldReturnTheFilteredList() {
		List<Integer> integers = Arrays.asList(10, 5, 60, 30, 1);
		int i = 10;
		List<Integer> expectedResult = Arrays.asList(10, 2, 50, 100);
		when(numberArranger.filter(integers, i)).thenReturn(expectedResult);

		List<Integer> result = service.filter(integers, i);

		assertEquals(1, result.size());
		assertEquals(10, result.get(0).intValue());
//		assertEquals(result, expectedResult);

	}
}