package com.keyholesoftware.apistats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableConfigurationProperties(ApiStatisticsProperties.class)
@EnableScheduling
public class EnableApiStatisticsConfiguration extends WebMvcConfigurerAdapter {

	@Value("${api.statistics.name}")
	private String name;

	@Value("${api.statistics.pattern-match:/.*}")
	private String patternMatch;

	@Value("${api.statistics.publish:true}")
	private Boolean publish;

	@Value("${api.statistics.publish-url}")
	private String publishUrl;

	@Value("${api.statistics.publish-threshold:10}")
	private int publishThreshold;

	@Value("${api.statistics.token}")
	private String token;

	@Autowired
	private StatisticsService statisticsService;

	@Autowired
	private ApiFilter filter;

	@Bean
	public StatisticsService statisticsService() {
		return new StatisticsService();
	}

	@Bean
	public ApiFilter filter() {
		return new ApiFilter();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public FilterRegistrationBean someFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setName(this.name);
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
		registration.addInitParameter("api-pattern", this.patternMatch);
		registration.addInitParameter("service-name", this.name);
		return registration;
	}

	@Scheduled(fixedDelayString = "${api.statistics.publish-interval:10000}")
	public void publishStatistics() {
		if (publish) {
			statisticsService.publish(this.publishUrl, this.publishThreshold, this.token);
		}
	}
}