package com.planner.rest.webservices.helloWorld;

public class HelloWorldBean {
	private String message;

	public HelloWorldBean(String Message) {
		this.message = Message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message.toString();
	}
}
