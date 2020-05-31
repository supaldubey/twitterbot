package com.cubestack.social.twitter.streaming;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cubestack.social.event.DirectMessageEvent;
import com.cubestack.social.event.EventDispatcher;
import com.cubestack.social.event.FavEvent;
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

	private final Twitter twitter;
	private final TwitterStatusProcessor statusProcessor;
	private final EventDispatcher eventDispatcher;
	
	private static final Logger LOG = Logger.getLogger(TwitterStreamListener.class);

	public TwitterStreamListener(Twitter twitter, TwitterStatusProcessor statusProcessor, EventDispatcher eventDispatcher) {
		this.twitter = twitter;
		this.statusProcessor = statusProcessor;
		this.eventDispatcher = eventDispatcher;
	}

	@Override
	public void onException(Exception ex) {
		LOG.error("Stream error", ex);
	}

	@Override
	public void onStatus(final Status status) {

		Status processingStatus = null;

		if (status.getInReplyToStatusId() > 0) {
			try {
				processingStatus = twitter.showStatus((status.getInReplyToStatusId()));
			} catch (TwitterException e) {
				LOG.error("Stream error", e);
			}
		} else if (status.getQuotedStatus() != null) {
			processingStatus = status.getQuotedStatus();
		}
		statusProcessor.processStatus(status, processingStatus);
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		LOG.info("Status delete" + statusDeletionNotice);
	}

	@Override
	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
		LOG.info("Status onTrackLimitationNotice" + numberOfLimitedStatuses);
	}

	@Override
	public void onScrubGeo(long userId, long upToStatusId) {
		LOG.info("Status onScrubGeo" + userId);
	}

	@Override
	public void onStallWarning(StallWarning warning) {
		LOG.info("Status onStallWarning" + warning);
	}

	@Override
	public void onDeletionNotice(long directMessageId, long userId) {
		LOG.info("Status onDeletionNotice" + directMessageId);
	}

	@Override
	public void onFriendList(long[] friendIds) {
		LOG.info("Status onFriendList" + friendIds);
	}

	@Override
	public void onFavorite(User source, User target, Status favoritedStatus) {
		LOG.info("Status onFavorite" + source);
		FavEvent event = new FavEvent();
		event.setTwitterId(source.getId());
		event.setTwitterScreenName(source.getScreenName());
		event.setStatus(favoritedStatus);

		eventDispatcher.dispatch(event);
	}

	@Override
	public void onUnfavorite(User source, User target, Status unfavoritedStatus) {
		LOG.info("Status onUnfavorite" + source);
	}

	@Override
	public void onFollow(User source, User followedUser) {

		FollowEvent event = new FollowEvent();
		event.setTwitterId(source.getId());
		event.setTwitterScreenName(source.getScreenName());
		event.setTwitterUser(source);

		eventDispatcher.dispatch(event);
	}

	@Override
	public void onUnfollow(User source, User unfollowedUser) {
		LOG.info("Status onUnfollow" + source);
	}

	@Override
	public void onDirectMessage(DirectMessage directMessage) {
		eventDispatcher.dispatch(new DirectMessageEvent());
	}

	@Override
	public void onUserListMemberAddition(User addedMember, User listOwner, UserList list) {
		LOG.info("Status onUserListMemberAddition" + addedMember);
	}

	@Override
	public void onUserListMemberDeletion(User deletedMember, User listOwner, UserList list) {
		LOG.info("Status onUserListMemberDeletion" + deletedMember);
	}

	@Override
	public void onUserListSubscription(User subscriber, User listOwner, UserList list) {
		LOG.info("Status onUserListSubscription" + subscriber);
	}

	@Override
	public void onUserListUnsubscription(User subscriber, User listOwner, UserList list) {
		LOG.info("Status onUserListUnsubscription" + subscriber);
	}

	@Override
	public void onUserListCreation(User listOwner, UserList list) {
		LOG.info("Status onUserListCreation" + list);
	}

	@Override
	public void onUserListUpdate(User listOwner, UserList list) {
		LOG.info("Status onUserListUpdate" + list);
	}

	@Override
	public void onUserListDeletion(User listOwner, UserList list) {
		LOG.info("Status onUserListDeletion" + list);
	}

	@Override
	public void onUserProfileUpdate(User updatedUser) {
		LOG.info("Status onUserProfileUpdate" + updatedUser);
	}

	@Override
	public void onUserSuspension(long suspendedUser) {
		LOG.info("Status onUserSuspension" + suspendedUser);
	}

	@Override
	public void onUserDeletion(long deletedUser) {
		LOG.info("Status onUserDeletion" + deletedUser);
	}

	@Override
	public void onBlock(User source, User blockedUser) {
		LOG.info("Status onBlock" + blockedUser);
	}

	@Override
	public void onUnblock(User source, User unblockedUser) {
		LOG.info("Status onUnblock" + unblockedUser);
	}

	@Override
	public void onRetweetedRetweet(User source, User target, Status retweetedStatus) {
		LOG.info("Status onRetweetedRetweet" + target);
	}

	@Override
	public void onFavoritedRetweet(User source, User target, Status favoritedRetweeet) {
		LOG.info("Status onFavoritedRetweet" + target);
	}

	@Override
	public void onQuotedTweet(User source, User target, Status quotingTweet) {
		LOG.info("Status onQuotedTweet" + target);
	}

}
