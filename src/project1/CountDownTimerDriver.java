package project1;

import static junit.framework.Assert.assertEquals;

public class CountDownTimerDriver {
	public static void main(String[] args){
		CountDownTimer s = new CountDownTimer(5, 10, 30);
		assertEquals(s.toString(), "5:10:30");
		
		s = new CountDownTimer("20:10:8");
		assertEquals(s.toString(), "20:10:08");

		s = new CountDownTimer("20:8");
		assertEquals(s.toString(), "0:20:08");
		
		CountDownTimer s1 = new CountDownTimer(5, 01, 30);
		s1.subtract(2000);
		assertEquals(s1.toString(), "4:28:10");
	}
}
