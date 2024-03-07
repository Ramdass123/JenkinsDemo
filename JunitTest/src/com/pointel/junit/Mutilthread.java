package com.pointel.junit;

public class Mutilthread extends Thread {
	
	
	public void run() {
		
		
			
			for (int i = 0 ; i<10 ;i++) {
				try {
					Thread.sleep(1000);
					System.out.println("value "+ i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		
		
	}
	
	

	
	
	public static void main(String[] args) {
		
		Mutilthread m = new Mutilthread();
		
		Mutilthread m1 = new Mutilthread();
		
		m1.start();
		
		m.start();
	}


}
