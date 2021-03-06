/**
 * 
 */
package com.cubestack.social.event.receiver;

import static reactor.bus.selector.Selectors.$;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cubestack.social.event.FavEvent;
import com.cubestack.social.twitter.streaming.TweetInteractionService;

import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.fn.Consumer;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * @author Supal Dubey
 *
 */
@Component
public class FavReceiver implements Consumer<Event<FavEvent>> {

	private final EventBus bus;
	private final TweetInteractionService tweetInteractionService;

	public FavReceiver(EventBus bus, TweetInteractionService tweetInteractionService) {
		this.bus = bus;
		this.tweetInteractionService = tweetInteractionService;
	}

	@PostConstruct
	public void init() {
		bus.on($("FAV"), this);
	}

	@Override
	public void accept(Event<FavEvent> event) {
		FavEvent favEvent = event.getData();
		Status status = favEvent.getStatus();

		// Proceed only if the person Fav-ing the tweet is the one tweet is
		// replied to.
		if (status.getInReplyToUserId() != 0 && status.getInReplyToUserId() == favEvent.getTwitterId()) {
			try {
				tweetInteractionService.deleteStatus(favEvent.getStatus().getId());
			} catch (TwitterException exception) {
				Logger.getLogger(FavReceiver.class).error("Error Handling Fav", exception);
			}
		}

	}

}
