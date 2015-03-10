package project1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MyTimerPanel extends JPanel {

	private CountDownTimer countdowntimer, countdowntimer1, countdowntimer2;
	private Timer javaTimer;
	private TimerListener timer;
	private JButton suspend;
	private JButton suspend1;
	private JButton suspend2;
	private JButton edit;
	private JButton edit1;
	private JButton edit2;
	private JLabel label;
	private JLabel label1;
	private JLabel label2;
	private boolean time, time1, time2;

	public MyTimerPanel() {
		// CountDownTimers
		countdowntimer = new CountDownTimer(0, 0, 10);
		countdowntimer1 = new CountDownTimer(0, 0, 10);
		countdowntimer2 = new CountDownTimer(0, 0, 10);
		// boolean values for stop/start
		time = false;
		time1 = false;
		time2 = false;
		// other stuff
		ButtonListener listen = new ButtonListener();
		timer = new TimerListener();
		javaTimer = new Timer(1000, timer);
		javaTimer.start();

		label = new JLabel(countdowntimer.toString());
		add(label);
		add(Box.createRigidArea(new Dimension(50, 0)));

		label1 = new JLabel(countdowntimer1.toString());
		add(label1);
		add(Box.createRigidArea(new Dimension(50, 0)));

		label2 = new JLabel(countdowntimer2.toString());
		add(label2);

		suspend = new JButton("Start/Stop");
		add(suspend);
		suspend.addActionListener(listen);

		suspend1 = new JButton("Start/Stop");
		add(suspend1);
		suspend1.addActionListener(listen);

		suspend2 = new JButton("Start/Stop");
		add(suspend2);
		suspend2.addActionListener(listen);

		edit = new JButton("     Edit     ");
		add(edit);
		edit.addActionListener(listen);

		edit1 = new JButton("     Edit     ");
		add(edit1);
		edit1.addActionListener(listen);

		edit2 = new JButton("     Edit     ");
		add(edit2);
		edit2.addActionListener(listen);

		setPreferredSize(new Dimension(350, 100));
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// all the stop/start stuff
			if (e.getSource() == suspend)
				time = !time;
			if (e.getSource() == suspend1)
				time1 = !time1;
			if (e.getSource() == suspend2)
				time2 = !time2;
			
			// the edit buttons all with try catch blocks for errors
			if (e.getSource() == edit) {
				try {
					String timer = JOptionPane
							.showInputDialog("Enter a timer, Captain:");
					CountDownTimer other = new CountDownTimer(timer);
					countdowntimer = other;
				} catch (Exception a) {
					JOptionPane.showMessageDialog(null, "Invalid Input, Captain");
				}
			}
			if (e.getSource() == edit1) {
				try {
					String timer = JOptionPane
							.showInputDialog("Enter a timer, Captain:");
					CountDownTimer other = new CountDownTimer(timer);
					countdowntimer1 = other;
				} catch (Exception a) {
					JOptionPane.showMessageDialog(null, "Invalid Input, Captain");
				}
			}
			if (e.getSource() == edit2) {
				try {
					String timer = JOptionPane
							.showInputDialog("Enter a timer, Captain:");
					CountDownTimer other = new CountDownTimer(timer);
					countdowntimer2 = other;
				} catch (Exception a) {
					JOptionPane.showMessageDialog(null, "Invalid Input, Captain");
				}
			}
		}//many brackets

	}

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//decrementing each one unless boolean value is true or if it's 0
			if (countdowntimer.getTotalSeconds() != 0 && time == false)
				countdowntimer.dec();
			if (countdowntimer1.getTotalSeconds() != 0 && time1 == false)
				countdowntimer1.dec();
			if (countdowntimer2.getTotalSeconds() != 0 && time2 == false)
				countdowntimer2.dec();
			//label updates
			label.setText(countdowntimer.toString());
			label1.setText(countdowntimer1.toString());
			label2.setText(countdowntimer2.toString());
		}
	}
}