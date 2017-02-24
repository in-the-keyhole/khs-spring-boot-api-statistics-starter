package com.keyholesoftware.apistats;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiFilter implements Filter {

	private String apiPattern;
	private String serviceName;

	@Autowired
	private StatisticsService statisticsService;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		String uri = request.getRequestURI();
		long start = System.currentTimeMillis();
		filterChain.doFilter(servletRequest, servletResponse);
		
		if (uri.matches(apiPattern)) {
			long duration = System.currentTimeMillis() - start;
			this.statisticsService.add(serviceName, uri, request.getMethod(), duration);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.apiPattern = config.getInitParameter("api-pattern");
		this.serviceName = config.getInitParameter("service-name");
	}
}
