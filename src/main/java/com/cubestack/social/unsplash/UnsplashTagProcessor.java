/**
 * 
 */
package com.cubestack.social.unsplash;

import org.springframework.stereotype.Component;

import com.cubestack.social.twitter.tags.BaseTagProcessor;

import twitter4j.Status;

/**
 * @author Supal Dubey
 *
 */

@Component
public class UnsplashTagProcessor extends BaseTagProcessor {

	private static final String UNSPLASH_TAG = "UNSPLASH";

	@Override
	public void handle(Status interactionStatus, Status status) {
		String text = status.getText().toUpperCase();
		// Fetch the word after the unsplash hash Tag

		int init = text.indexOf(text + UNSPLASH_TAG.length());
		// Next space from the Tag, which would be our search key
		int end = text.indexOf(" ", init);

		String search = text.substring(init, end);
		if (search != null && search.trim().length() > 0) {
			// we have what we need, fire up unsplash search
		} else {
			// Unable to extract text or invalid format, throw howto
		}

	}

	@Override
	public String tag() {
		return UNSPLASH_TAG;
	}

}
