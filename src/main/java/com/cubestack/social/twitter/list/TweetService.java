/**
 * 
 */
package com.cubestack.social.twitter.list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cubestack.social.model.Tweet;
import com.cubestack.social.model.TweetList;
import com.cubestack.social.persistance.TweetRepository;

/**
 * @author Supal Dubey
 *
 */
@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    public List<Tweet> findTweets(TweetList tweetList, int page) {
	Pageable pageable = new PageRequest(page, 10);
	return tweetRepository.findByList(tweetList, pageable);
    }

}
