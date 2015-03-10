package project1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*****************************************************************
 * Simple CountDownTimer
 *
 * @author Bob Heine
 * @version Version 2.0
 *****************************************************************/
public class CountDownTimer {
	/** Value of hours. */
	private int hours;

	/** Value of minutes. */
	private int minutes;

	/** Value of seconds. */
	private int seconds;

	/** Value of hours, minutes, and seconds combined to one value. */
	private int totalSeconds;

	/** Boolean value that determines if some methods will run. */
	private static boolean suspend;

	/*****************************************************************
	 * Returns the totalSeconds, used in the panel.
	 *
	 * @return The variable totalSeconds.
	 *****************************************************************/
	public int getTotalSeconds() {
		return totalSeconds;
	}

	/*****************************************************************
	 * Returns the boolean value suspend, used in testing.
	 *
	 * @return The variable suspend.
	 *****************************************************************/
	public boolean getSuspend() {
		return suspend;
	}

	/*****************************************************************
	 * Default constructor that sets time to 0;
	 *****************************************************************/
	public CountDownTimer() {
		// Default constructor that sets the CountDownTimer to zero.
		reset();
		check(this);
	}

	/*****************************************************************
	 * Resets all variables to 0 and sets suspend to false.
	 *****************************************************************/
	private void reset() {
		hours = 0;
		minutes = 0;
		seconds = 0;
		suspend = false;
	}

	/*****************************************************************
	 * Checks to see if the number entered is valid.
	 *
	 * @param timer
	 *            CountDownTimer to be checked for validity
	 *****************************************************************/
	private void check(CountDownTimer timer) {
		if (timer.hours < 0 || timer.minutes < 0 || timer.seconds < 0)
			throw new IllegalArgumentException("Negative number, Captain");
		if (timer.minutes > 59 || timer.seconds > 59)
			throw new IllegalArgumentException("Too big of a number, Captain");
		total(timer);
	}

	/*****************************************************************
	 * Simplifies into seconds and sets variable totalSeconds.
	 *****************************************************************/
	private void total(CountDownTimer timer) {
		totalSeconds = (3600 * timer.hours) + (60 * timer.minutes)
				+ timer.seconds;
	}

	/*****************************************************************
	 * Reformats into proper hours/minutes/seconds
	 *****************************************************************/
	private void recount(CountDownTimer timer) {
		int tempSeconds = totalSeconds;
		this.hours = tempSeconds / 3600;
		tempSeconds -= hours * 3600;
		this.minutes = tempSeconds / 60;
		tempSeconds -= minutes * 60;
		this.seconds = tempSeconds;

	}

	/*************************************************************************
	 * A constructor that initializes the instance variables with the provided
	 * values
	 *
	 * @param hours
	 *            Integer hours will be set to.
	 * @param minutes
	 *            Integer minutes will be set to.
	 * @param seconds
	 *            Integer seconds will be set to.
	 *************************************************************************/
	public CountDownTimer(int hours, int minutes, int seconds) {
		reset();
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		check(this);
	}

	/*************************************************************************
	 * A constructor that initializes the instance variables with the provided
	 * values. Initialize hours to 0.
	 *
	 * @param minutes
	 *            Integer that minutes will be set to.
	 * @param seconds
	 *            Integer seconds will be set to.
	 *************************************************************************/
	public CountDownTimer(int minutes, int seconds) {
		reset();
		this.minutes = minutes;
		this.seconds = seconds;
		check(this);
	}

	/*************************************************************************
	 * A constructor that initializes the instance variables with the provided
	 * values. Initialize hours and minutes to 0.
	 *
	 * @param seconds
	 *            Integer that seconds will be set to.
	 *************************************************************************/
	public CountDownTimer(int seconds) {
		reset();
		this.seconds = seconds;
		check(this);
	}

	/**********************************************************************
	 * A constructor that initializes the instance variables with the other
	 * CountDownTimer parameter.
	 *
	 * @param other
	 *            CountDownTimer to be made as a new CountDownTimer
	 **********************************************************************/
	public CountDownTimer(CountDownTimer other) {
		reset();
		this.hours = other.hours;
		this.minutes = other.minutes;
		this.seconds = other.seconds;
		check(this);
	}

	/*************************************************************************
	 * Takes a user input string and converts it to a time, integers must be
	 * separated by ":"
	 *
	 * @param startTime
	 *            A string to be formatted to a CountDownTimer, integers given
	 *            must be separated by colons and minutes and seconds must be
	 *            below 60
	 *************************************************************************/
	public CountDownTimer(String startTime) {
		reset();
		int counter = 0;
		String[] time = startTime.split(":");

		for (int x = 0; x < startTime.length(); x++) {
			if (startTime.charAt(x) == ':')
				counter++;
		}
		if (counter == 2) {
			hours = Integer.parseInt(time[0]);
			minutes = Integer.parseInt(time[1]);
			seconds = Integer.parseInt(time[2]);
		}
		if (counter == 1) {
			minutes = Integer.parseInt(time[0]);
			seconds = Integer.parseInt(time[1]);
		}
		if (counter == 0) {
			seconds = Integer.parseInt(time[0]);
		}
		if (counter >= 3) {
			throw new IllegalArgumentException("Too many colons, Captain");
		}
		check(this);
	}

	/***************************************************************************
	 * A method that returns true if “this” CountDownTimer object is exactly the
	 * same as the other object
	 *
	 * @param other
	 *            Takes a CountDownTimer to be compared
	 * @return Returns true or false depending on equality
	 ***************************************************************************/
	public boolean equals(Object other) {
		CountDownTimer obj = (CountDownTimer) other;
		if (this.totalSeconds == obj.totalSeconds)
			return true;

		return false;
	}

	/**************************************************************************
	 * A static method that returns true if CountDownTimer object t1 is exactly
	 * the same as if CountDownTimer object t2
	 *
	 * @param t1
	 *            CountDown timer being compared
	 * @param t2
	 *            CountDown timer to be compared to
	 *
	 * @return true or false depending on equality
	 **************************************************************************/
	public static boolean equals(CountDownTimer t1, CountDownTimer t2) {
		if (t1.totalSeconds == t2.totalSeconds)
			return true;
		else
			return false;
	}

	/**************************************************************************
	 * Compares this CountDownTimer with the one in the parameter. Returns 0 if
	 * equal, 1 if the this is greater and -1 if other is greater.
	 *
	 * @param other
	 *            CountDownTimer to compare to
	 *
	 * @return An integer that represents the inequality of the timers.
	 **************************************************************************/
	public int compareTo(CountDownTimer other) {
		if (equals(this, other))
			return 0;
		if (this.totalSeconds > other.totalSeconds)
			return 1;
		if (this.totalSeconds < other.totalSeconds)
			return -1;
		return 7;
	}

	/*********************************************************************
	 * Compares two CountDownTimers, returns 0 if equal, 1 if the first is
	 * greater and -1 if the seconds is greater.
	 *
	 * @param t1
	 *            The first CountDownTimer
	 * @param t2
	 *            The second CountDownTimer
	 *
	 * @return An integer representing the inequality between the timers.
	 **********************************************************************/
	public static int compareTo(CountDownTimer t1, CountDownTimer t2) {

		if (equals(t1, t2) == true)
			return 0;
		if (t1.totalSeconds > t2.totalSeconds)
			return 1;
		if (t1.totalSeconds < t2.totalSeconds)
			return -1;
		return 7;
	}

	/*********************************************************************
	 * Subtracts a number of seconds from this CountDownTimer Only runs if
	 * suspend isn't toggled.
	 *
	 * @param seconds
	 *            The amount of seconds to be subtracted.
	 **********************************************************************/
	public void subtract(int seconds) {
		if (suspend == false) {
			if (this.totalSeconds < seconds) {
				throw new IllegalArgumentException(
						"Too high of a number, Captain");
			} else {
				this.totalSeconds -= seconds;
				recount(this);
			}
		} else
			throw new IllegalArgumentException("Suspend is on, Captain");
	}

	/*************************************************************************
	 * Subtracts a CountDownTimer value from this CountDownTimer. Only runs if
	 * suspend isn't toggled.
	 *
	 * @param other
	 *            CountDownTimer to be subtracted
	 *************************************************************************/
	public void subtract(CountDownTimer other) {
		if (suspend == false) {
			subtract(other.totalSeconds);
		} else
			throw new IllegalArgumentException("Suspend is on, Captain");
	}

	/************************************************************************
	 * Decreases the amount of totalSeconds by 1. Only runs if suspend is not
	 * active.
	 ************************************************************************/
	public void dec() {
		if (suspend == false) {
			if (totalSeconds == 0) {
				throw new IllegalArgumentException(
						"Can't decrease anymore, Captain");
			} else {
				this.totalSeconds--;
				recount(this);
			}
		} else
			throw new IllegalArgumentException("Suspend is on, Captain");
	}

	/*********************************************************************
	 * Adds the parameter timer to this timer. Only runs if suspend is not
	 * active.
	 *
	 * @param other
	 *            The CountDownTimer to be added.
	 *********************************************************************/
	public void add(CountDownTimer other) {
		if (suspend == false) {
			this.totalSeconds += other.totalSeconds;
			recount(this);
		} else
			throw new IllegalArgumentException("Suspend is on, Captain");
	}

	/*****************************************************************
	 * Adds 1 to the current timer.
	 *****************************************************************/
	public void inc() {
		if (suspend == false) {
			this.totalSeconds++;
			recount(this);
		} else
			throw new IllegalArgumentException("Suspend is on, Captain");
	}

	/********************************************************************
	 * Converts a CountDownTimer to a readable output String with correct
	 * formatting.
	 *
	 * @return CountDownTimer object in readable string format.
	 ********************************************************************/
	public String toString() {
		String minString = minutes + "";
		String secString = seconds + "";
		if (minutes < 10)
			minString = "0" + minString;
		if (seconds < 10)
			secString = "0" + secString;
		return hours + ":" + minString + ":" + secString;
	}

	/*****************************************************************
	 * Saves a CountDownTimer object to a file to be used later.
	 *
	 * @param fileName
	 *            The name of the file to be saved.
	 *****************************************************************/
	public void save(String fileName) throws IOException {
		PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter(
				fileName)));
		print.println(new Integer(hours).toString());
		print.println(new Integer(minutes).toString());
		print.println(new Integer(seconds).toString());

		print.close();
	}

	/*****************************************************************
	 * Loads a file and puts it into a CountDownTimer object
	 *
	 * @param fileName
	 *            The name of the file to be loaded.
	 *****************************************************************/
	public void load(String fileName) throws IOException {
		Scanner scan = new Scanner(new File(fileName));
		reset();
		this.hours = scan.nextInt();
		this.minutes = scan.nextInt();
		this.seconds = scan.nextInt();
		check(this);
		scan.close();
	}

	/*****************************************************************
	 * Manipulates the static variable "suspend"
	 *****************************************************************/
	public static void toggleSuspend() {
		suspend = !suspend;
	}
}
