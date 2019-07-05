package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NumberArrangerService {

	@Autowired
	private NumberArranger numberArranger;

	public List<Integer> filter(List<Integer> numbers, int i) {
		List<Integer> filter = numberArranger.filter(numbers, i);
		return Collections.singletonList(filter.get(0));
	}
}
