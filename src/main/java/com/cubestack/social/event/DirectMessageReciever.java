/**
 * 
 */
package com.cubestack.social.event;

import static reactor.bus.selector.Selectors.$;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.fn.Consumer;

/**
 * @author Supal Dubey
 *
 */
@Component
public class DirectMessageReciever implements Consumer<Event<DirectMessageEvent>> {

    @Autowired
    private EventBus bus;
    
    @PostConstruct
    public void init() {
	bus.on($("DM"), this);
    }
    
    @Override
    public void accept(Event<DirectMessageEvent> t) {
	System.out.println("DM");
    }

    
    
}
