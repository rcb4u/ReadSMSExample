package com.rahul.android.apps.data;

/**
 * This class represents SMS.
 * 
 * @author itcuties
 *
 */
public class SMSData {

	// Number from witch the sms was send
	private String number;
	// SMS text body
	private String body;
	private String time;
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}


	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return time;
	}
}
