/**
 * 
 */
package com.cubestack.social.event.receiver;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cubestack.social.event.FollowEvent;
import com.cubestack.social.model.TwitterUser;
import com.cubestack.social.persistance.TwitterUserPersistantService;
import com.cubestack.social.twitter.streaming.TweetInteractionService;

import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.fn.Consumer;
import twitter4j.TwitterException;

import static reactor.bus.selector.Selectors.$;

/**
 * @author Supal Dubey
 *
 */
@Component
public class FollowReceiver implements Consumer<Event<FollowEvent>> {

	@Autowired
	private EventBus bus;

	@Autowired
	private TweetInteractionService tweetInteractionService;

	@Autowired
	private TwitterUserPersistantService twitterUserPersistantService;

	@PostConstruct
	public void init() {
		bus.on($("Follow"), this);
	}

	@Override
	public void accept(Event<FollowEvent> event) {
		System.out.println("Followed");
		FollowEvent followEvent = event.getData();
		String screenName = followEvent.getTwitterScreenName();

		TwitterUser twitterUser = twitterUserPersistantService.findUserByTwitterId(followEvent.getTwitterId());
		if (twitterUser == null) {
			twitterUser = twitterUserPersistantService.saveUser(followEvent.getTwitterUser());
		}

		try {
			tweetInteractionService.sendDirectMsg(
					"Welcome! Thanks for the follow, Your account is created, your PIN is: " + twitterUser.getPin(),
					screenName);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}
