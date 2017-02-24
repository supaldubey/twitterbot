/**
 * 
 */
package com.cubestack.social;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import reactor.Environment;
import reactor.bus.EventBus;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**
 * @author Supal Dubey
 *
 */
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class AppConfig {


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
