/**
 * 
 */
package com.cubestack.social.twitter.tags;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Supal Dubey
 *
 */
public abstract class BaseTagProcessor implements TagProcessor {
	
	@Autowired
	private TagRegistry tagRegistry;

	@PostConstruct
	public void init() {
		tagRegistry.register(this);
	}

}
