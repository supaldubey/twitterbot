/**
 * 
 */
package com.cubestack.social.twitter.tags;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import twitter4j.HashtagEntity;
import twitter4j.Status;

/**
 * @author Supal Dubey
 *
 */
@Service
public class TagRegistry {

    private static final Map<String, List<TagProcessor>> NOTIF_MAP = new HashMap<String, List<TagProcessor>>();

    public void register(TagProcessor tagProcessor) {

	if (!NOTIF_MAP.containsKey(tagProcessor.tag())) {
	    NOTIF_MAP.put(tagProcessor.tag(), new LinkedList<TagProcessor>());
	}
	NOTIF_MAP.get(tagProcessor.tag()).add(tagProcessor);
    }

    public void handleStream(Status interactionStatus, Status status) {
	// We can process only one hashTag for now
	if (interactionStatus.getHashtagEntities() == null || !(interactionStatus.getHashtagEntities().length == 1)) {
	    // Reject and move on
	}

	HashtagEntity hashTag = interactionStatus.getHashtagEntities()[0];
	if (hashTag != null) {
	    // Process the Tags
	    String tagText = hashTag.getText().toUpperCase();
	    if (NOTIF_MAP.get(tagText) != null) {
		for (TagProcessor processor : NOTIF_MAP.get(tagText)) {
		    // Pass on to processors.
		    processor.handle(interactionStatus, status);
		}
	    }
	}
    }

}
