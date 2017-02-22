/**
 * 
 */
package com.cubestack.social.unsplash;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.cubestack.social.async.Task;
import com.cubestack.social.twitter.streaming.TweetInteractionService;

import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * @author Supal Dubey
 *
 */
public class UnsplashImageTask implements Task {
	
	private static final Logger LOG = Logger.getLogger(UnsplashImageTask.class);

	public UnsplashImageTask(String url, TweetInteractionService interactionService, Status status, String search) {
		this.remoteUrl = url;
		this.interactionService = interactionService;
		this.status = status;
		this.search = search;
	}

	private String remoteUrl;
	private TweetInteractionService interactionService;
	private Status status;
	private String search;

	@Override
	public void perform() {

		try {
			URL url = new URL(remoteUrl);
			URLConnection con = url.openConnection();
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			InputStream in = con.getInputStream();

			BufferedImage img = ImageIO.read(in);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, "png", os);
			InputStream fis = new ByteArrayInputStream(os.toByteArray());

			String response = "Here you go, random image from unsplash";
			if(search != null && ! search.trim().isEmpty()) {
				response = String.format("Here you go, random image for category %s from unsplash", search );
			}
			interactionService.sendTweetStream(status, response, fis);
		} catch (Exception e) {
			handleException(e);
		}
	}

	@Override
	public void handleException(Exception exception) {
		LOG.error("Error Tweeting image", exception);
		try {
			if (search != null && search.trim().length() > 2) {
				interactionService.sendTweetTo(status, 
						"Unable to fetch image of category: " + search+". Valid categories: buildings, nature, food, people, technology, objects", null);
			} else {
				interactionService.sendTweetTo(status, "Unable to fetch random image as no search key was provided.", null);
			}
		} catch (TwitterException ex) {
			LOG.error("Error Tweeting image", ex);
		}
	}
}

