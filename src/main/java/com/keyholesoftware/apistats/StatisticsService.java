package com.keyholesoftware.apistats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyholesoftware.apistats.model.StatisticEntry;

@Service
public class StatisticsService {

	private static final Logger LOG = LoggerFactory.getLogger(StatisticsService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	private List<StatisticEntry> stats = new ArrayList<StatisticEntry>();

	public void add(String serviceName, String uri, String method, long duration) {
		stats.add(new StatisticEntry(serviceName, uri, method, "" + duration));
	}

	public void publish(String publishUrl, int threshold, String token) {
		if (stats.size() >= threshold) {
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				headers.set("token", token);
				objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
				String json = objectMapper.writeValueAsString(stats);
				HttpEntity<String> requestBody = new HttpEntity<String>(json, headers);

				restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
				restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
				restTemplate.postForObject(publishUrl, requestBody, String.class);
				
				stats.clear();
			} catch (HttpClientErrorException hcee) {
				LOG.error(hcee.getMessage());
			} catch (JsonProcessingException e) {
				LOG.error(e.getMessage());
			}
		}
	}
}
