package com.jafa.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jafa.repository.TestRepository;

@Service
public class TestService {

	@Autowired
	TestRepository testRepository;
	
	public Date date1() {
		return testRepository.getDate1();
	}
	
	public Date date2() {
		return testRepository.getDate2();
	}
	
}
