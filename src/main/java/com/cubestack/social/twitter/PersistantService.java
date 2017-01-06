/**
 * 
 */
package com.cubestack.social.twitter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubestack.social.model.Tweep;
import com.cubestack.social.repo.TweepleRepository;


/**
 * @author Supal Dubey
 *
 */
@Service
public class PersistantService {

	@Autowired
	private TweepleRepository repository;


	public Tweep findTweepByTwitterId(long twitterId) {
		List<Tweep> tweeples = repository.findByTwitterId(twitterId);
		if (tweeples != null && !tweeples.isEmpty()) {
			// Should be always one
			return tweeples.get(0);
		}
		return null;
	}

	public Tweep saveTweep(Tweep tweeple) {
		return repository.save(tweeple);
	}

}
