package com.keyholesoftware.apistats;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("api.statistics")
public class ApiStatisticsProperties {

	/**
	 * The name of the service serving the API.
	 */
	private String name;
	
	/**
	 * The (Regex) uri pattern to filter watched APIs.
	 * 
	 * Default: /.*
	 */
	private String patternMatch = "/.*";
	
	/**
	 * Do you want to publish the captured statistics.
	 * 
	 * Default: true
	 */
	private boolean publish = true;
	
	/**
	 * The url to POST statistics to.
	 */
	private String publishUrl;
	
	/**
	 * The interval (ms) in which to publish statistics. 
	 * 
	 * Default: 10000
	 */
	private int publishInterval = 10000;
	
	/**
	 * The threshold of statistics that should be banked before publishing.
	 * 
	 * Default: 10
	 */
	private int publishThreshold = 10;
	
	/**
	 * The security token required by the publishUrl.
	 */
	private String token;

	public int getPublishInterval() {
		return publishInterval;
	}

	public int getPublishThreshold() {
		return publishThreshold;
	}

	public void setPublishThreshold(int publishThreshold) {
		this.publishThreshold = publishThreshold;
	}

	public void setPublishInterval(int publishInterval) {
		this.publishInterval = publishInterval;
	}

	public String getPatternMatch() {
		return patternMatch;
	}

	public void setPatternMatch(String patternMatch) {
		this.patternMatch = patternMatch;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPublish() {
		return publish;
	}

	public void setPublish(boolean publish) {
		this.publish = publish;
	}

	public String getPublishUrl() {
		return publishUrl;
	}

	public void setPublishUrl(String publishUrl) {
		this.publishUrl = publishUrl;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}