/**
 * 
 */
package com.cubestack.social.twitter.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cubestack.social.candidate.TwitterUserCandidate;
import com.cubestack.social.twitter.list.TweetListService;

import twitter4j.Status;

/**
 * @author Supal Dubey
 *
 */
@Component
public class TweetListProcessor extends BaseTagProcessor {

    @Autowired
    private TweetListService tweetListService;

    private static final String LIST_TAG = "LIST";

    @Override
    public void handle(Status interactionStatus, Status status) {
	if (status == null) {
	    return;
	}
	try {

	    String text = interactionStatus.getText().toUpperCase();
	    // Fetch the word after the #List hash Tag

	    int init = text.indexOf(LIST_TAG) + LIST_TAG.length() + 1;
	    // Next space from the Tag, which would be our List Name
	    int end = text.indexOf(" ", init);

	    String listName = "";
	    if (end == -1) {
		listName = text.substring(init);
	    } else {
		listName = text.substring(init, end);
	    }
	    if (listName != null && listName.trim().length() > 0) {
		TwitterUserCandidate candidate = new TwitterUserCandidate();
		// List Name to which Tweet must be saved to
		candidate.setScreenName(interactionStatus.getUser().getScreenName());
		candidate.setStatus(status);
		candidate.setTwitterId(interactionStatus.getUser().getId());

		tweetListService.addTweetToList(listName, interactionStatus, candidate);
	    } else {
		// Unable to extract text or invalid format, Move on... reject
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    @Override
    public String tag() {
	return LIST_TAG;
    }

}
