/**
 * 
 */
package com.cubestack.social.twitter;

import java.util.Date;

import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.MediaEntity;
import twitter4j.Place;
import twitter4j.RateLimitStatus;
import twitter4j.Scopes;
import twitter4j.Status;
import twitter4j.SymbolEntity;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;

/**
 * @author Supal Dubey
 *
 */
public class StatusUtils {

	public static Status create(final String text, final String screenName) {
		return new Status() {
			
			private static final long serialVersionUID = 528247840075243474L;

			@Override
			public UserMentionEntity[] getUserMentionEntities() {
				
				return null;
			}
			
			@Override
			public URLEntity[] getURLEntities() {
				
				return null;
			}
			
			@Override
			public SymbolEntity[] getSymbolEntities() {
				
				return null;
			}
			
			@Override
			public MediaEntity[] getMediaEntities() {
				
				return null;
			}
			
			@Override
			public HashtagEntity[] getHashtagEntities() {
				
				return null;
			}
			
			@Override
			public RateLimitStatus getRateLimitStatus() {
				
				return null;
			}
			
			@Override
			public int getAccessLevel() {
				
				return 0;
			}
			
			@Override
			public int compareTo(Status o) {
				
				return 0;
			}
			
			@Override
			public boolean isTruncated() {
				
				return false;
			}
			
			@Override
			public boolean isRetweetedByMe() {
				
				return false;
			}
			
			@Override
			public boolean isRetweeted() {
				
				return false;
			}
			
			@Override
			public boolean isRetweet() {
				
				return false;
			}
			
			@Override
			public boolean isPossiblySensitive() {
				
				return false;
			}
			
			@Override
			public boolean isFavorited() {
				
				return false;
			}
			
			@Override
			public String[] getWithheldInCountries() {
				
				return null;
			}
			
			@Override
			public User getUser() {
				
				return new User() {
					
					private static final long serialVersionUID = 5331696965721020021L;

					@Override
					public RateLimitStatus getRateLimitStatus() {
						
						return null;
					}
					
					@Override
					public int getAccessLevel() {
						
						return 0;
					}
					
					@Override
					public int compareTo(User arg0) {
						
						return 0;
					}
					
					@Override
					public boolean isVerified() {
						
						return false;
					}
					
					@Override
					public boolean isTranslator() {
						
						return false;
					}
					
					@Override
					public boolean isShowAllInlineMedia() {
						
						return false;
					}
					
					@Override
					public boolean isProtected() {
						
						return false;
					}
					
					@Override
					public boolean isProfileUseBackgroundImage() {
						
						return false;
					}
					
					@Override
					public boolean isProfileBackgroundTiled() {
						
						return false;
					}
					
					@Override
					public boolean isGeoEnabled() {
						
						return false;
					}
					
					@Override
					public boolean isFollowRequestSent() {
						
						return false;
					}
					
					@Override
					public boolean isDefaultProfileImage() {
						
						return false;
					}
					
					@Override
					public boolean isDefaultProfile() {
						
						return false;
					}
					
					@Override
					public boolean isContributorsEnabled() {
						
						return false;
					}
					
					@Override
					public String[] getWithheldInCountries() {
						
						return null;
					}
					
					@Override
					public int getUtcOffset() {
						
						return 0;
					}
					
					@Override
					public URLEntity getURLEntity() {
						
						return null;
					}
					
					@Override
					public String getURL() {
						
						return null;
					}
					
					@Override
					public String getTimeZone() {
						
						return null;
					}
					
					@Override
					public int getStatusesCount() {
						
						return 0;
					}
					
					@Override
					public Status getStatus() {
						
						return null;
					}
					
					@Override
					public String getScreenName() {
						
						return screenName;
					}
					
					@Override
					public String getProfileTextColor() {
						
						return null;
					}
					
					@Override
					public String getProfileSidebarFillColor() {
						
						return null;
					}
					
					@Override
					public String getProfileSidebarBorderColor() {
						
						return null;
					}
					
					@Override
					public String getProfileLinkColor() {
						
						return null;
					}
					
					@Override
					public String getProfileImageURLHttps() {
						
						return null;
					}
					
					@Override
					public String getProfileImageURL() {
						
						return null;
					}
					
					@Override
					public String getProfileBannerURL() {
						
						return null;
					}
					
					@Override
					public String getProfileBannerRetinaURL() {
						
						return null;
					}
					
					@Override
					public String getProfileBannerMobileURL() {
						
						return null;
					}
					
					@Override
					public String getProfileBannerMobileRetinaURL() {
						
						return null;
					}
					
					@Override
					public String getProfileBannerIPadURL() {
						
						return null;
					}
					
					@Override
					public String getProfileBannerIPadRetinaURL() {
						
						return null;
					}
					
					@Override
					public String getProfileBackgroundImageUrlHttps() {
						
						return null;
					}
					
					@Override
					public String getProfileBackgroundImageURL() {
						
						return null;
					}
					
					@Override
					public String getProfileBackgroundColor() {
						
						return null;
					}
					
					@Override
					public String getOriginalProfileImageURLHttps() {
						
						return null;
					}
					
					@Override
					public String getOriginalProfileImageURL() {
						
						return null;
					}
					
					@Override
					public String getName() {
						
						return null;
					}
					
					@Override
					public String getMiniProfileImageURLHttps() {
						
						return null;
					}
					
					@Override
					public String getMiniProfileImageURL() {
						
						return null;
					}
					
					@Override
					public String getLocation() {
						
						return null;
					}
					
					@Override
					public int getListedCount() {
						
						return 0;
					}
					
					@Override
					public String getLang() {
						
						return null;
					}
					
					@Override
					public long getId() {
						
						return 0;
					}
					
					@Override
					public int getFriendsCount() {
						
						return 0;
					}
					
					@Override
					public int getFollowersCount() {
						
						return 0;
					}
					
					@Override
					public int getFavouritesCount() {
						
						return 0;
					}
					
					@Override
					public String getEmail() {
						
						return null;
					}
					
					@Override
					public URLEntity[] getDescriptionURLEntities() {
						
						return null;
					}
					
					@Override
					public String getDescription() {
						
						return null;
					}
					
					@Override
					public Date getCreatedAt() {
						
						return null;
					}
					
					@Override
					public String getBiggerProfileImageURLHttps() {
						
						return null;
					}
					
					@Override
					public String getBiggerProfileImageURL() {
						
						return null;
					}
				};
			}
			
			@Override
			public String getText() {
				return text;
			}
			
			@Override
			public String getSource() {
				
				return null;
			}
			
			@Override
			public Scopes getScopes() {
				
				return null;
			}
			
			@Override
			public Status getRetweetedStatus() {
				
				return null;
			}
			
			@Override
			public int getRetweetCount() {
				
				return 0;
			}
			
			@Override
			public long getQuotedStatusId() {
				
				return 0;
			}
			
			@Override
			public Status getQuotedStatus() {
				
				return null;
			}
			
			@Override
			public Place getPlace() {
				
				return null;
			}
			
			@Override
			public String getLang() {
				
				return null;
			}
			
			@Override
			public long getInReplyToUserId() {
				
				return 0;
			}
			
			@Override
			public long getInReplyToStatusId() {
				
				return 0;
			}
			
			@Override
			public String getInReplyToScreenName() {
				
				return null;
			}
			
			@Override
			public long getId() {
				
				return 0;
			}
			
			@Override
			public GeoLocation getGeoLocation() {
				
				return null;
			}
			
			@Override
			public int getFavoriteCount() {
				
				return 0;
			}
			
			@Override
			public int getDisplayTextRangeStart() {
				
				return 0;
			}
			
			@Override
			public int getDisplayTextRangeEnd() {
				
				return 0;
			}
			
			@Override
			public long getCurrentUserRetweetId() {
				
				return 0;
			}
			
			@Override
			public Date getCreatedAt() {
				
				return null;
			}
			
			@Override
			public long[] getContributors() {
				
				return null;
			}
		};
	}
}
