/**
 * 
 */
package com.cubestack.social.persistance;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubestack.social.exception.ProfileNotFoundException;
import com.cubestack.social.model.TweetList;
import com.cubestack.social.model.TwitterUser;

import twitter4j.User;

/**
 * @author Supal Dubey
 *
 */
@Service
public class TwitterUserPersistantService {

	private final TwitterUserRepository repository;

	public TwitterUserPersistantService(TwitterUserRepository repository) {
		this.repository = repository;
	}

	public TwitterUser findUserByTwitterId(long twitterId) {
		List<TwitterUser> users = repository.findByTwitterId(twitterId);
		if (users != null && !users.isEmpty()) {
			// Should be always one
			return users.get(0);
		}
		return null;
	}

	public TwitterUser saveUser(User user) {
		TwitterUser twitterUser = new TwitterUser();
		twitterUser.setName(user.getScreenName());
		twitterUser.setScreenName(user.getScreenName());
		twitterUser.setTwitterId(user.getId());

		twitterUser.setTweetLists(new LinkedList<TweetList>());

		return repository.save(twitterUser);
	}

	public TwitterUser saveUser(TwitterUser twitterUser) {
		return repository.save(twitterUser);
	}

	public TwitterUser findUserByScreenName(String screenName) throws ProfileNotFoundException {
		TwitterUser user = repository.findUserByScreenName(screenName);
		if(user == null) {
			throw new ProfileNotFoundException();
		}
		return user;
	}

}
