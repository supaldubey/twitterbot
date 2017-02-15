/**
 * 
 */
package com.cubestack.social.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.bus.Event;
import reactor.bus.EventBus;

/**
 * @author Supal Dubey
 *
 */
@Service
public class EventDispatcher {

    @Autowired
    private EventBus eventBus;

    public void dispatch(TwitterEvent event) {
	eventBus.notify(event.getType(), Event.wrap(event));
    }
}
