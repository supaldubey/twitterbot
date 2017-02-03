/**
 * 
 */
package com.cubestack.social.event;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.fn.Consumer;

import static reactor.bus.selector.Selectors.$;

/**
 * @author Supal Dubey
 *
 */
@Component
public class FollowReceiver implements Consumer<Event<FollowEvent>> {
    
    @Autowired
    private EventBus bus;
    
    @PostConstruct
    public void init() {
	bus.on($("Follow"), this);
    }

    @Override
    public void accept(Event<FollowEvent> event) {
	System.out.println("Followed");
    }

}
