/**
 * 
 */
package com.cubestack.social.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cubestack.social.model.Tweet;
import com.cubestack.social.model.TweetList;

/**
 * @author Supal Dubey
 *
 */
public interface TweetRepository extends JpaRepository<Tweet, Long> {

	Page<Tweet> findByListAndDeleted(TweetList tweetList, boolean deleted, Pageable pageable);

}
