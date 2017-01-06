/**
 * 
 */
package com.cubestack.social.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cubestack.social.model.Tweep;

/**
 * @author supal
 *
 */
public interface TweepleRepository extends JpaRepository<Tweep, Long> {
	List<Tweep> findByScreenNameIgnoreCase(String value);
	
	List<Tweep> findByTwitterId(long value);
}
