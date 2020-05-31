/**
 *
 */
package com.cubestack.social.unsplash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cubestack.social.async.AsyncWorker;
import com.cubestack.social.twitter.streaming.TweetInteractionService;
import com.cubestack.social.twitter.tags.BaseTagProcessor;

import twitter4j.Status;

/**
 * @author Supal Dubey
 *
 */

@Component
public class UnsplashTagProcessor extends BaseTagProcessor {

    private static final String RANDOM = "random";
    private static final String CATEGORY = "category/";
    private static final String UNSPLASH_TAG = "UNSPLASH";

    private final String unsplashUrl;
    private final String resolution;
    private final AsyncWorker asyncWorker;
    private final TweetInteractionService interactionService;

    public UnsplashTagProcessor(AsyncWorker asyncWorker, TweetInteractionService interactionService, @Value("${unsplash.base.url}")
            String unsplashUrl, @Value("${unsplash.default.resolution}") String resolution) {
        this.asyncWorker = asyncWorker;
        this.interactionService = interactionService;
        this.resolution = resolution;
        this.unsplashUrl = unsplashUrl;
    }


    @Override
    public void handle(Status interactionStatus, Status status) {
        String text = interactionStatus.getText().toUpperCase().trim().replaceAll(" +", " ");
        // Fetch the word after the unsplash hash Tag

        int init = text.indexOf(UNSPLASH_TAG) + UNSPLASH_TAG.length() + 1;
        // Next space from the Tag, which would be our search key
        int end = text.indexOf(" ", init);
        String search = null;

        if (init < text.length()) {
            if (end == -1) {
                search = text.substring(init);
            } else {
                search = text.substring(init, end);
            }
        }
        if (search != null && search.trim().length() > 0) {
            // we have what we need, fire up unsplash search
            sendImage(search, interactionStatus, unsplashUrl + CATEGORY + search.trim() + resolution);
        } else {
            // Unable to extract text show random
            sendImage(search, interactionStatus, unsplashUrl + RANDOM + resolution);
        }

    }

    private void sendImage(String search, Status interactionStatus, String unsplashUrl) {
        //Do in background
        asyncWorker.submit(new UnsplashImageTask(unsplashUrl, interactionService, interactionStatus, search));
    }

    @Override
    public String tag() {
        return UNSPLASH_TAG;
    }

}
