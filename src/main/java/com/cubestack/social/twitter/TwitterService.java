/**
 * 
 */
package com.cubestack.social.twitter;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubestack.social.exception.ProfileNotFoundException;
import com.cubestack.social.model.Tweeple;
import com.cubestack.social.repo.TwitterRepository;

/**
 * @author supal
 *
 */
@Service
@Transactional
public class TwitterService {
	
	@Autowired
	private TwitterRepository twitterRepository;
	
	
	public Tweeple save(Tweeple tweep) {
		return twitterRepository.save(tweep);
	}
	
	
	public List<Tweeple> findAll() {
		return twitterRepository.findAll();
	}
	
	public Tweeple find(long tweepId) throws ProfileNotFoundException {
		Tweeple tweep = twitterRepository.findOne(tweepId);
		if(tweep == null) {
			throw new ProfileNotFoundException();
		}
		return tweep;
	}
	

	public List<Tweeple> findByScreenId(String value) {
		return twitterRepository.findByScreenNameIgnoreCase(value);
	}
}
