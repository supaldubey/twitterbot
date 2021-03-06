/**
 * 
 */
package com.cubestack.social.event.receiver;

import static reactor.bus.selector.Selectors.$;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cubestack.social.event.DirectMessageEvent;

import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.fn.Consumer;

/**
 * @author Supal Dubey
 *
 */
@Component
public class DirectMessageReciever implements Consumer<Event<DirectMessageEvent>> {

	private final EventBus bus;

	public DirectMessageReciever(EventBus bus) {
		this.bus = bus;
	}

	@PostConstruct
	public void init() {
		bus.on($("DM"), this);
	}

	@Override
	public void accept(Event<DirectMessageEvent> t) {
	}

}
