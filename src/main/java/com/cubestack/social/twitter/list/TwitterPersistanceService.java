/**
 * 
 */
package com.cubestack.social.twitter.list;

import java.util.List;

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

    @Autowired
    private TwitterUserRepository twitterRepository;

    public TwitterUser save(TwitterUser tweep) {
	return twitterRepository.save(tweep);
    }

    public TwitterUser find(long tweepId) throws ProfileNotFoundException {
	TwitterUser tweep = twitterRepository.findOne(tweepId);
	if (tweep == null) {
	    throw new ProfileNotFoundException();
	}
	return tweep;
    }

    public List<TwitterUser> findByScreenId(String screenName) {
	return twitterRepository.findByScreenNameIgnoreCase(screenName);
    }
}
