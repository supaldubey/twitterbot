/**
 * 
 */
package com.cubestack.social.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Supal Dubey
 *
 */
@Controller
public class AppWebController {
	
	@RequestMapping(path = "user", method = RequestMethod.GET)
	public ModelAndView findByUser() {
		return new ModelAndView("/index.html");
	}

}
