/**
 * 
 */
package com.cubestack.social.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cubestack.social.converter.TweetConverter;
import com.cubestack.social.converter.TwitterUserConverter;
import com.cubestack.social.exception.ProfileNotFoundException;
import com.cubestack.social.model.Tweet;
import com.cubestack.social.model.TwitterUser;
import com.cubestack.social.persistance.TwitterUserPersistantService;
import com.cubestack.social.twitter.list.TweetListService;
import com.cubestack.social.util.GenericUtil;

/**
 * @author supal
 *
 */
@RestController
@RequestMapping("/rest")
public class AppRestController {

	private static final String MSG_SEPARATOR = ", ";

	@Autowired
	private TwitterUserPersistantService persistantService;

	@Autowired
	private TweetListService tweetListService;

	@RequestMapping(path = "{screenName}", method = RequestMethod.GET)
	@PreAuthorize("hasIpAddress('127.0.01')")
	// Not best way to secure, but for initial setup lets restrict to only localhost
	// untill we figure out the size and scale
	public Response findByUser(@PathVariable("screenName") String screenName) throws ProfileNotFoundException {
		TwitterUser user = persistantService.findUserByScreenName(screenName);
		if (user != null) {
			Response response = new Response();
			response.setTwitterUserCandidate(TwitterUserConverter.convertToCandidate(user));
			return response;
		}
		return null;
	}

	@RequestMapping(path = "{screenName}/{listName}/{pageIndex}", method = RequestMethod.GET)
	@PreAuthorize("hasIpAddress('127.0.01')")
	public Response findTweets(@PathVariable("screenName") String screenName,
			@PathVariable("listName") String listName, @PathVariable("pageIndex") String pageIndex) throws ProfileNotFoundException {
		int pageNo = GenericUtil.parseSafe(pageIndex);
		List<Tweet> tweets = tweetListService.findTweets(screenName, listName, pageNo);

		Response response = new Response();
		response.setTwitterUserCandidate(TwitterUserConverter.convertToCandidate(persistantService.findUserByScreenName(screenName)));
		response.setTweetCandidates(TweetConverter.convertToCandidates(tweets));
		return response;
	}
	
	

	@ExceptionHandler
	public ResponseEntity<Response> handle(Exception exception) {
		exception.printStackTrace();

		Response response = new Response();
		response.setSuccess(false);

		if (exception instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException validationException = (MethodArgumentNotValidException) exception;
			StringBuilder errorMsg = new StringBuilder();
			for (FieldError error : validationException.getBindingResult().getFieldErrors()) {
				errorMsg.append(error.getDefaultMessage()).append(MSG_SEPARATOR);
			}
			// Trim the last Comma
			errorMsg = new StringBuilder(errorMsg.substring(0, errorMsg.length() - 2));
			response.setMessage(errorMsg.toString());
		} else if (exception instanceof ProfileNotFoundException) {
			response.setMessage(exception.getMessage());
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
