/**
 * 
 */
package com.cubestack.social;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import reactor.Environment;
import reactor.bus.EventBus;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**
 * @author supal
 *
 */
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
	ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
	registration.addUrlMappings("/console/*");
	return registration;
    }

    @Bean
    public Twitter twitter() {
	return new TwitterFactory().getInstance();
    }

    @Bean
    Environment env() {
	return Environment.initializeIfEmpty().assignErrorJournal();
    }

    @Bean
    EventBus createEventBus(Environment env) {
	return EventBus.create(env, Environment.THREAD_POOL);
    }

}
