/**
 * 
 */
package com.cubestack.social.twitter.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import twitter4j.Status;

/**
 * @author Supal Dubey
 *
 */
@Component
public class TwitterStatusProcessor {

	private final TagRegistry registry;

	public TwitterStatusProcessor(TagRegistry registry) {
		this.registry = registry;
	}

	public void processStatus(Status interactionStatus, Status processingStatus) {
		registry.handleStream(interactionStatus, processingStatus);
	}
}
