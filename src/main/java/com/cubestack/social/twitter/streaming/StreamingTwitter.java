/**
 * 
 */
package com.cubestack.social.twitter.streaming;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

/**
 * @author Supal Dubey
 *
 */

@Service
public class StreamingTwitter {

	@Autowired
	private TwitterStreamListener listener;

	@PostConstruct
	public void init() {

		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		twitterStream.addListener(listener);
//		twitterStream.user();
	}
}
