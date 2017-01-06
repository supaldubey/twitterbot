/**
 * 
 */
package com.cubestack.social.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cubestack.social.model.TwitterUser;

/**
 * @author supal
 *
 */
public interface TweepleRepository extends JpaRepository<TwitterUser, Long> {
	List<TwitterUser> findByScreenNameIgnoreCase(String value);
	
	List<TwitterUser> findByTwitterId(long value);
}
