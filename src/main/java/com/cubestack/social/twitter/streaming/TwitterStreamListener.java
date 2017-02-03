/**
 * 
 */
package com.cubestack.social.twitter.streaming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cubestack.social.twitter.tags.TwitterStatusProcessor;

import twitter4j.DirectMessage;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserStreamListener;

/**
 * @author Supal Dubey
 *
 */
@Component
public class TwitterStreamListener implements UserStreamListener {

    @Autowired
    private Twitter twitter;

    @Autowired
    private TwitterStatusProcessor statusProcessor;

    @Override
    public void onException(Exception ex) {
	ex.printStackTrace();
    }

    @Override
    public void onStatus(final Status status) {
	System.out.println(status.getUser().getName() + " : " + status.getText());

	Status processingStatus = null;

	if (status.getInReplyToStatusId() > 0) {
	    try {
		processingStatus = twitter.showStatus((status.getInReplyToStatusId()));
		if (processingStatus == null) { //
		    // don't know if needed - T4J docs are very bad
		} else {
		    System.out.println("Nested Twter");
		    System.out.println(
			    "@" + processingStatus.getUser().getScreenName() + " - " + processingStatus.getText());
		}
	    } catch (TwitterException e) {
		e.printStackTrace();

	    }
	} else if (status.getQuotedStatus() != null) {
	    processingStatus = status.getQuotedStatus();
	}
	// process only if we have valid status
	if (processingStatus != null) {
	    statusProcessor.processStatus(status, processingStatus);
	}
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

    }

    @Override
    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {

    }

    @Override
    public void onScrubGeo(long userId, long upToStatusId) {

    }

    @Override
    public void onStallWarning(StallWarning warning) {

    }

    @Override
    public void onDeletionNotice(long directMessageId, long userId) {

    }

    @Override
    public void onFriendList(long[] friendIds) {

    }

    @Override
    public void onFavorite(User source, User target, Status favoritedStatus) {

    }

    @Override
    public void onUnfavorite(User source, User target, Status unfavoritedStatus) {

    }

    @Override
    public void onFollow(User source, User followedUser) {

    }

    @Override
    public void onUnfollow(User source, User unfollowedUser) {

    }

    @Override
    public void onDirectMessage(DirectMessage directMessage) {

    }

    @Override
    public void onUserListMemberAddition(User addedMember, User listOwner, UserList list) {

    }

    @Override
    public void onUserListMemberDeletion(User deletedMember, User listOwner, UserList list) {

    }

    @Override
    public void onUserListSubscription(User subscriber, User listOwner, UserList list) {

    }

    @Override
    public void onUserListUnsubscription(User subscriber, User listOwner, UserList list) {

    }

    @Override
    public void onUserListCreation(User listOwner, UserList list) {

    }

    @Override
    public void onUserListUpdate(User listOwner, UserList list) {

    }

    @Override
    public void onUserListDeletion(User listOwner, UserList list) {

    }

    @Override
    public void onUserProfileUpdate(User updatedUser) {

    }

    @Override
    public void onUserSuspension(long suspendedUser) {

    }

    @Override
    public void onUserDeletion(long deletedUser) {

    }

    @Override
    public void onBlock(User source, User blockedUser) {

    }

    @Override
    public void onUnblock(User source, User unblockedUser) {

    }

    @Override
    public void onRetweetedRetweet(User source, User target, Status retweetedStatus) {

    }

    @Override
    public void onFavoritedRetweet(User source, User target, Status favoritedRetweeet) {

    }

    @Override
    public void onQuotedTweet(User source, User target, Status quotingTweet) {

    }

}
