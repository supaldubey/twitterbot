/**
 * 
 */
package com.cubestack.social.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cubestack.social.model.Tweeple;

/**
 * @author supal
 *
 */
public interface TwitterRepository extends JpaRepository<Tweeple, Long> {

	List<Tweeple> findByScreenNameIgnoreCase(String value);


}
