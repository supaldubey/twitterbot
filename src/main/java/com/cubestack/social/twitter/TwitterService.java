/**
 * 
 */
package com.cubestack.social.twitter;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubestack.social.exception.ProfileNotFoundException;
import com.cubestack.social.model.Tweep;
import com.cubestack.social.repo.TweepleRepository;

/**
 * @author supal
 *
 */
@Service
@Transactional
public class TwitterService {
	
	@Autowired
	private TweepleRepository twitterRepository;
	
	
	public Tweep save(Tweep tweep) {
		return twitterRepository.save(tweep);
	}
	
	
	public Tweep find(long tweepId) throws ProfileNotFoundException {
		Tweep tweep = twitterRepository.findOne(tweepId);
		if(tweep == null) {
			throw new ProfileNotFoundException();
		}
		return tweep;
	}
	

	public List<Tweep> findByScreenId(String screenName) {
		return twitterRepository.findByScreenNameIgnoreCase(screenName);
	}
}
