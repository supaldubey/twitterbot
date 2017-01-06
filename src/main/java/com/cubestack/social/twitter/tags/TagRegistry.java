/**
 * 
 */
package com.cubestack.social.twitter.tags;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import twitter4j.Status;

/**
 * @author Supal Dubey
 *
 */
@Service
public class TagRegistry {
	
	private static final Map<String, List<TagProcessor>> NOTIF_MAP = new HashMap<String, List<TagProcessor>>();
	
	public void register(TagProcessor tagProcessor) {
		
		if(! NOTIF_MAP.containsKey(tagProcessor.tag())) {
			NOTIF_MAP.put(tagProcessor.tag(), new LinkedList<TagProcessor>());
		}
		NOTIF_MAP.get(tagProcessor.tag()).add(tagProcessor);
	}
	
	public void handleStream(Status status) {
		
	}

}
