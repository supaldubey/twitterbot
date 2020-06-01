/**
 * 
 */
package com.cubestack.social.twitter.list;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubestack.social.exception.ProfileNotFoundException;
import com.cubestack.social.model.TwitterUser;
import com.cubestack.social.persistance.TwitterUserRepository;

/**
 * @author supal
 *
 */
@Service
@Transactional
public class TwitterPersistanceService {

	private final TwitterUserRepository twitterRepository;

	public TwitterPersistanceService(TwitterUserRepository twitterRepository) {
		this.twitterRepository = twitterRepository;
	}

	public TwitterUser save(TwitterUser tweep) {
		return twitterRepository.save(tweep);
	}

	public TwitterUser find(long tweepId) throws ProfileNotFoundException {
		TwitterUser twitterUser = twitterRepository.findOne(tweepId);
		if (twitterUser == null) {
			throw new ProfileNotFoundException();
		}
		return twitterUser;
	}

}
