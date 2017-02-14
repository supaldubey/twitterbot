/**
 * 
 */
package com.cubestack.social.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cubestack.social.util.PasswordGenerator;

/**
 * @author Supal Dubey
 *
 */
@Entity
@Table(name = "TWITTER_USER")
public class TwitterUser {

    public TwitterUser() {
	this.pin = new String(PasswordGenerator.generatePswd());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TWTR_ID")
    private long twitterId;

    @Column(name = "SCREEN_NAME")
    private String screenName;

    @Column(name = "PIN")
    private String pin;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TweetList> tweetLists;

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

    public String getScreenName() {
	return screenName;
    }

    public void setScreenName(String screenName) {
	this.screenName = screenName;
    }

    public String getPin() {
	return pin;
    }

    public void setPin(String pin) {
	this.pin = pin;
    }

    public List<TweetList> getTweetLists() {
	return tweetLists;
    }

    public void setTweetLists(List<TweetList> tweetLists) {
	this.tweetLists = tweetLists;
    }

    public long getTwitterId() {
	return twitterId;
    }

    public void setTwitterId(long twitterId) {
	this.twitterId = twitterId;
    }

}
