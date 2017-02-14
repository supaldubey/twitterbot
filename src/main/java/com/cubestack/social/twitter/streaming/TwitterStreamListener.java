/**
 * 
 */
package com.cubestack.social.twitter.streaming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cubestack.social.event.DirectMessageEvent;
import com.cubestack.social.event.EventDispatcher;
import com.cubestack.social.event.FollowEvent;
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
    
    @Autowired
    private EventDispatcher eventDispatcher;

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
	statusProcessor.processStatus(status, processingStatus);
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
	System.out.println("Status delete" + statusDeletionNotice);
    }

    @Override
    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
	System.out.println("Status onTrackLimitationNotice" + numberOfLimitedStatuses);
    }

    @Override
    public void onScrubGeo(long userId, long upToStatusId) {
	System.out.println("Status onScrubGeo" + userId);
    }

    @Override
    public void onStallWarning(StallWarning warning) {
	System.out.println("Status onStallWarning" + warning);
    }

    @Override
    public void onDeletionNotice(long directMessageId, long userId) {
	System.out.println("Status onDeletionNotice" + directMessageId);
    }

    @Override
    public void onFriendList(long[] friendIds) {
	System.out.println("Status onFriendList" + friendIds);
    }

    @Override
    public void onFavorite(User source, User target, Status favoritedStatus) {
	System.out.println("Status onFavorite" + source);
    }

    @Override
    public void onUnfavorite(User source, User target, Status unfavoritedStatus) {
	System.out.println("Status onUnfavorite" + source);
    }

    @Override
    public void onFollow(User source, User followedUser) {
	eventDispatcher.dispatch(new FollowEvent());
    }

    @Override
    public void onUnfollow(User source, User unfollowedUser) {
	System.out.println("Status onUnfollow" + source);
    }

    @Override
    public void onDirectMessage(DirectMessage directMessage) {
	eventDispatcher.dispatch(new DirectMessageEvent());
    }

    @Override
    public void onUserListMemberAddition(User addedMember, User listOwner, UserList list) {
	System.out.println("Status onUserListMemberAddition" + addedMember);
    }

    @Override
    public void onUserListMemberDeletion(User deletedMember, User listOwner, UserList list) {
	System.out.println("Status onUserListMemberDeletion" + deletedMember);
    }

    @Override
    public void onUserListSubscription(User subscriber, User listOwner, UserList list) {
	System.out.println("Status onUserListSubscription" + subscriber);
    }

    @Override
    public void onUserListUnsubscription(User subscriber, User listOwner, UserList list) {
	System.out.println("Status onUserListUnsubscription" + subscriber);
    }

    @Override
    public void onUserListCreation(User listOwner, UserList list) {
	System.out.println("Status onUserListCreation" + list);
    }

    @Override
    public void onUserListUpdate(User listOwner, UserList list) {
	System.out.println("Status onUserListUpdate" + list);
    }

    @Override
    public void onUserListDeletion(User listOwner, UserList list) {
	System.out.println("Status onUserListDeletion" + list);
    }

    @Override
    public void onUserProfileUpdate(User updatedUser) {
	System.out.println("Status onUserProfileUpdate" + updatedUser);
    }

    @Override
    public void onUserSuspension(long suspendedUser) {
	System.out.println("Status onUserSuspension" + suspendedUser);
    }

    @Override
    public void onUserDeletion(long deletedUser) {
	System.out.println("Status onUserDeletion" + deletedUser);
    }

    @Override
    public void onBlock(User source, User blockedUser) {
	System.out.println("Status onBlock" + blockedUser);
    }

    @Override
    public void onUnblock(User source, User unblockedUser) {
	System.out.println("Status onUnblock" + unblockedUser);
    }

    @Override
    public void onRetweetedRetweet(User source, User target, Status retweetedStatus) {
	System.out.println("Status onRetweetedRetweet" + target);
    }

    @Override
    public void onFavoritedRetweet(User source, User target, Status favoritedRetweeet) {
	System.out.println("Status onFavoritedRetweet" + target);
    }

    @Override
    public void onQuotedTweet(User source, User target, Status quotingTweet) {
	System.out.println("Status onQuotedTweet" + target);
    }

}
