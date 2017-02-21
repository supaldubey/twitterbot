/**
 * 
 */
package com.cubestack.social.twitter.tags;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cubestack.social.twitter.streaming.TweetInteractionService;

import twitter4j.Status;

/**
 * @author Supal Dubey
 *
 */
@Component
public class HelpProcessor extends BaseTagProcessor {

	private static final String HELP_FILE_LOCATION = "/cubestack_bot_help.png";

	private static final String HELP = "HELP";

	@Autowired
	private TweetInteractionService tweetInteractionService;

	@Override
	public void handle(Status interactionStatus, Status status) {
		try {
			tweetInteractionService.sendTweetTo(interactionStatus, "Howdy! See the tagged image.", new File(HelpProcessor.class.getResource(HELP_FILE_LOCATION).getFile()));
		} catch (Exception exception) {
			Logger.getLogger(HelpProcessor.class).error("Error Tweeting help", exception);
		}
	}

	@Override
	public String tag() {
		return HELP;
	}

}
