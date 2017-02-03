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

    @Autowired
    private TagRegistry registry;

    public void processStatus(Status interactionStatus, Status processingStatus) {
	registry.handleStream(interactionStatus, processingStatus);
    }
}
