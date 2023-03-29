package com.jafa.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.AppTest;

public class TestRepositoryTest extends AppTest {

	@Autowired
	TestRepository testRepository;

	@Test
	public void test() {
		System.out.println("=======================");
		System.out.println(testRepository.getDate1());
		System.out.println("=======================");
		System.out.println(testRepository.getDate2());
		System.out.println("=======================");
	}

}
