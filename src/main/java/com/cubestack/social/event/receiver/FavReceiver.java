/**
 * 
 */
package com.cubestack.social.event.receiver;

import static reactor.bus.selector.Selectors.$;

import javax.annotation.PostConstruct;

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

    @Autowired
    private EventBus bus;

    @Autowired
    private TweetInteractionService tweetInteractionService;

    @PostConstruct
    public void init() {
	bus.on($("FAV"), this);
    }

    @Override
    public void accept(Event<FavEvent> event) {
	System.out.println("Tweet Faved, delete it");
	FavEvent favEvent = event.getData();
	Status status = favEvent.getStatus();
	
	//Proceed only if the person Fav-ing the tweet is the one tweet is replied to.
	if (status.getInReplyToUserId() != 0 && status.getInReplyToUserId() == favEvent.getTwitterId()) {
	    try {
		tweetInteractionService.deleteStatus(favEvent.getStatus().getId());
	    } catch (TwitterException e) {
		e.printStackTrace();
	    }
	}

    }

}
