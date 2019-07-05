package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NumberArranger {

	public List<Integer> filter(List<Integer> numbers, int i) {
		return numbers.stream().filter(number -> number >= i).collect(Collectors.toList());
	}
}
