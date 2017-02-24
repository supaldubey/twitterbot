# @cubestackbot #

A bot for Twitter to organize your tweets and also would tweet you Unsplash photos (because why not! and also ♡ Unsplash).

### Why? ###

Over the years we have been passionate about twitter platform and the potential it has. Although as primary developer (The one updating Readme) I don't tweet much bit still spend lot of time reading and following people across locations, political opinions, technologies etc. 

There are times when you are traveling, are at work when you stumble upon a tweet with a video link you cannot play, an article you want to read in peace etc. So what do you do?

-> Fav it? (Still not comfortable with using like) The fav system cannot be sorted, looked up quickly?
-> Organize or add it to list from Tweet Deck? (Too crappy and too much effort)

Hence the solution or the Bot which would do it for you. Just tweet to the Bot with #List (Followed by list name) and it will do it for you. 

Also it handles #Unsplash tags. Tweet to it with #Unsplash (Followed by optional Category name) and it will reply with a random pic. :) 

### Versions ###

Current master version is running in Production as of now (with configuration changes, such as databases, ports security etc)...
The next version is in development with laundry list of items pending, you may request a feature with issues. :) 

### How to run in local? ###

* First things first, get your bot account ready on Twitter and generate twitter4j configuration files from here [Generate from here](http://twitter4j.org/en/configuration.html) 
* Place the configuration file in src/main/resources folder
* Create database if using MySQL else umcomment the H2 database options.
* Run TwitterBotApplication as main application, you can control ports etc via application properties file. 

# Terms #
While the source and all artifacts are open source, this is not meant to be commercially used. You are free to use source, certain parts of it for research, tests or any PoCs (if you will). But any commercial use is not appreciated. In case you need to send me an email and we can discuss it further. 

### Contribution guidelines ###

* I know there are very few Unit tests, the reason is there is very less business logic. Most of the code is just Glue to certain other components. That said I really need to add tests and would be done with next releases. Never thought of it as a full product while I started :) 
* Pull requests welcome.
* See the general flow and formatting of the code and try to submit Pull requests which match the overall style.
* Be gentle, while criticism is welcome calling someone dumb, stupid is not. :)

### Who do I talk to? ###

* If you have any questions, feature requests or bugs for Bot, shoot email to cubestacklabs@gmail.com
* EMail me at supal.dubey at Gmail.com if you want to discuss anything else. 
