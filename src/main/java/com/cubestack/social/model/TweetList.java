/**
 * 
 */
package com.cubestack.social.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Supal Dubey
 *
 */
@Entity
@Table(name = "TWEET_LIST")
public class TweetList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(cascade = { CascadeType.ALL }) // LAZY
    private List<Tweet> tweets = new LinkedList<Tweet>();

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<Tweet> getTweets() {
	return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
	this.tweets = tweets;
    }

}
