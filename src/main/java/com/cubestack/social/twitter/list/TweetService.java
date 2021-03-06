/**
 * 
 */
package com.cubestack.social.twitter.list;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cubestack.social.candidate.TweetWrapper;
import com.cubestack.social.model.Tweet;
import com.cubestack.social.model.TweetList;
import com.cubestack.social.persistance.TweetRepository;

/**
 * @author Supal Dubey
 *
 */
@Service
@Transactional
public class TweetService {

	private final TweetRepository tweetRepository;

	public TweetService(TweetRepository tweetRepository) {
		this.tweetRepository = tweetRepository;
	}

	public void populateTweetsInWrapper(TweetWrapper wrapper, TweetList tweetList, int page) {
		Pageable pageable = new PageRequest(page, 10);
		Page<Tweet> pageResult = tweetRepository.findByListAndDeleted(tweetList, false, pageable);
		wrapper.setTotalPages(pageResult.getTotalPages());
		wrapper.setTweets(pageResult.getContent());
	}

}
