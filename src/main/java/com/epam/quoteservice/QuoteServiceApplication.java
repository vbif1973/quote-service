package com.epam.quoteservice;

import com.epam.quoteservice.bootstrap.QuoteServletContextListener;
import com.epam.quoteservice.dto.QuoteDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletContextListener;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class QuoteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuoteServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public Set quoteCache() {
		return ConcurrentHashMap.newKeySet();
	}

	@Bean
	public ConcurrentHashMap<String, Float> elvlCache() {
		return new ConcurrentHashMap<>();
	}

	@Bean
	ServletListenerRegistrationBean<ServletContextListener> servletListener() {
		ServletListenerRegistrationBean<ServletContextListener> srb
				= new ServletListenerRegistrationBean<>();
		srb.setListener(new QuoteServletContextListener());
		return srb;
	}

}
