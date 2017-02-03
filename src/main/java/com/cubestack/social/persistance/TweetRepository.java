/**
 * 
 */
package com.cubestack.social.persistance;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cubestack.social.model.Tweet;
import com.cubestack.social.model.TweetList;

/**
 * @author Supal Dubey
 *
 */
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findByList(TweetList tweetList, Pageable pageable);

}
