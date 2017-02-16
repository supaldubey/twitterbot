/**
 * 
 */
package com.cubestack.social.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cubestack.social.model.TwitterUser;

/**
 * @author supal
 *
 */
public interface TwitterUserRepository extends JpaRepository<TwitterUser, Long> {
	List<TwitterUser> findByScreenNameIgnoreCase(String value);

	List<TwitterUser> findByTwitterId(long value);

	TwitterUser findUserByScreenName(String screenName);
}
