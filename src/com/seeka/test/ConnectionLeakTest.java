package com.seeka.test;

public class ConnectionLeakTest {

	
	 ConnectionLeakUtil connectionLeakUtil;
		 {
		        connectionLeakUtil = new ConnectionLeakUtil();
		}
	 

	public  void assertNoLeaks() {
	        try {
				connectionLeakUtil.assertNoLeaks();
			} catch (ConnectionLeakException e) {
				e.printStackTrace();
			}
	    }
}
