package com.keyholesoftware.apistats.model;

public class StatisticEntry {

	private String service;
	private String uri;
	private String method;
	private String duration;

	public StatisticEntry(String service, String uri, String method, String duration) {
		super();
		this.service = service;
		this.uri = uri;
		this.method = method;
		this.duration = duration;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
}
