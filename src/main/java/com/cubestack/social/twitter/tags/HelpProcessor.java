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

	public static final String HELP_FILE_LOCATION = "/cubestack_bot_help.png";

	private static final String HELP = "HELP";

	@Autowired
	private TweetInteractionService tweetInteractionService;

	@Override
	public void handle(Status interactionStatus, Status status) {

		File file = fetch();

		try {
			tweetInteractionService.sendTweetTo(interactionStatus, "Howdy! See the tagged image. Check : http://cubestack.in/twitterui", file);
		} catch (Exception exception) {
			Logger.getLogger(HelpProcessor.class).error("Error Tweeting help", exception);
		}
	}

	// Somehow twitter4j API isn't able to fetch files from inside the Jar, moving to server for now
	private File fetch() {
		return new File("/jars/cubestack_bot_help.png");
	}

	@Override
	public String tag() {
		return HELP;
	}

}
