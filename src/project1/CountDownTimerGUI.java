package project1;

import javax.swing.JFrame;

public class CountDownTimerGUI{
	public static void main(String[] args){
		JFrame frame = new JFrame("CountDown Timer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyTimerPanel panel = new MyTimerPanel();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
	}

}
