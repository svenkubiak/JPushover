[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.svenkubiak/jpushover/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.svenkubiak/jpushover)
[![Build Status](https://secure.travis-ci.org/svenkubiak/JPushover.png?branch=master)](http://travis-ci.org/svenkubiak/JPushover)


JPushover
================

Convenient class for sending messages to [Pushover][1] in Java.

Usage
------------------

1) Add the jpushover dependency to your pom.xml:
```
<dependency>
	<groupId>de.svenkubiak</groupId>
        <artifactId>jpushover</artifactId>
        <version>x.x.x</version>
</dependency>
```
2) Use the JPushover object with the required informations were you want
```
JPushover.build()
	.withToken("MyToken")
	.withUser("MyUser")
	.withMessage("MyMessage")
	.push();
```		
You can additionally add all available options from the official [Pushover documentation][2]	

You can also validate a user and token using the following method

	boolean valid = JPushover.build()
		.withToken("MyToken")
		.withUser("MyUser")
		.validate();
		
If you want more information and/or the response from the Pushover API, use the JPushoverResponse object.

	JPushoverResponse jPushoverResponse = JPushover.build()
		.withToken("MyToken")
		.withUser("MyUser")
		.withMessage("MyMessage")
		.push();
		
The JPushoverResponse will return the raw HTTP status code, along with the raw JSON response and a convenient boolean if the request was successful or not.	

[1]: https://pushover.net
[2]: https://pushover.net/api
