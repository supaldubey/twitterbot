/**
 * 
 */
package com.cubestack.social.twitter.tags;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cubestack.social.model.TwitterUser;
import com.cubestack.social.persistance.TwitterUserRepository;
import com.cubestack.social.twitter.streaming.TweetInteractionService;
import com.cubestack.social.util.PasswordGenerator;

import twitter4j.DirectMessage;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * @author Supal Dubey
 *
 */
@Component
public class ResetPasswordProcessor extends PasswordProcessor {

    private static final String RESET_PASSWORD = "RESET";

    @Autowired
    private TwitterUserRepository userRepository;

    @Autowired
    private TweetInteractionService tweetInteractionService;

    @Override
    public void handle(Status interactionStatus, Status status) {
	long twitterId = interactionStatus.getUser().getId();
	// Technically there should be only one.
	List<TwitterUser> users = userRepository.findByTwitterId(twitterId);

	if (users != null && !users.isEmpty()) {
	    // Send the password in DM
	    TwitterUser user = users.get(0);
	    //Change the password
	    user.setPin(new String(PasswordGenerator.generatePswd()));
	    userRepository.save(user);
	    
	    try {
		DirectMessage msg = tweetInteractionService.sendDirectMsg("PIN reset, your new access password is " + user.getPin(),
			interactionStatus.getUser().getScreenName());
		if (msg == null) {
		    sendFailureTweet(interactionStatus, user);
		}
	    } catch (TwitterException e) {
		e.printStackTrace();
		sendFailureTweet(interactionStatus, user);
	    }
	}
    }


    @Override
    public String tag() {
	return RESET_PASSWORD;
    }

}
