package com.codingdojo.web.models;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Timer {
	private Date start;
	private Date stop;
	
	public Timer() {
		start = null;
		stop = null;
	}
	
	public Date getStart() {
		return start;
	}
	public void setStart() {
		this.start = new Date();
	}
	public Date getStop() {
		return stop;
	}
	public void setStop() {
		this.stop = new Date();
	}
	public String getTotal() {
		long diff = stop.getTime() - start.getTime();
		long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
		long min = seconds / 60;
		long sec = seconds % 60;
		
		String total = Long.toString(min) + "m " + Long.toString(sec) + "s";
		return total;
	}
	
	public static String getFromCurrentTime(Timer time) {
		Date now = new Date();
		Date start = time.getStart();
		if(start == null) {
			start = new Date();
		}
		long diff = now.getTime() - start.getTime();
		long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
		long min = seconds / 60;
		long sec = seconds % 60;
		
		String total = Long.toString(min) + "m " + Long.toString(sec) + "s";
		return total;
	}
	
}
