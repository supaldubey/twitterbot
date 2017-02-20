/**
 * 
 */
package com.cubestack.social.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cubestack.social.candidate.TweetCandidate;
import com.cubestack.social.candidate.TwitterUserCandidate;
import com.cubestack.social.converter.TweetConverter;
import com.cubestack.social.converter.TwitterUserConverter;
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
	public TwitterUserCandidate findByUser(@PathVariable("screenName") String screenName) {
		TwitterUser user = persistantService.findUserByScreenName(screenName);
		if (user != null) {
			return TwitterUserConverter.convertToCandidate(user);
		}
		return null;
	}

	@RequestMapping(path = "{screenName}/{listName}/{pageIndex}", method = RequestMethod.GET)
	public List<TweetCandidate> findTweets(@PathVariable("screenName") String screenName,
			@PathVariable("listName") String listName, @PathVariable("pageIndex") String pageIndex) {
		int pageNo = GenericUtil.parseSafe(pageIndex);
		List<Tweet> tweets = tweetListService.findTweets(screenName, listName, pageNo);

		return TweetConverter.convertToCandidates(tweets);
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
		} else {
			response.setMessage(exception.getMessage());
		}
		return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
