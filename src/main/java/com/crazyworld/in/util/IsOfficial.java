package com.crazyworld.in.util;


public enum IsOfficial {
	T("T"),
	F("F");
	
	private String value;
	
	 private IsOfficial(String value) {
	        this.value = value;
	    }
	 public String getValue() {
	        return value;
	    }

	
}

