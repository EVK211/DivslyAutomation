package com.divsly.pageobjects;

import com.divsly.utilities.ReadConfig;

public class Test {
	
	
	public static void main(String[] args) {
		ReadConfig rg = new ReadConfig();
		
		System.out.println(rg.getBaseUrl());
		System.out.println(rg.getBrowser());
		System.out.println(rg.getHeadless());
	}

}
