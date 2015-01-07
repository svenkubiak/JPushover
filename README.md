[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.svenkubiak/jpushover/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.svenkubiak/jpushover)
[![Build Status](https://secure.travis-ci.org/svenkubiak/jpushover.png?branch=master)](http://travis-ci.org/svenkubiak/jpushover)

JPushover
================

Convenient class for sending messages to [Pushover][1] in Java project

Usage
------------------

1) Add the jpushover dependency to your pom.xml:

    <dependency>
        <groupId>de.svenkubiak</groupId>
        <artifactId>jpushover</artifactId>
        <version>x.x.x</version>
    </dependency>

2) Use the JPushover object were need

	new JPushover()
		.token("MyToken")
		.user("MyUser")
		.message("MyMessage")
		.push();
		
If you want more information and/or the response from Pushover API, use the JPushoverResponse object.

	JPushoverResponse jPushoverResponse = new JPushover()
		.token("MyToken")
		.user("MyUser")
		.message("MyMessage")
		.push();
		
JPushoverResponse will return the raw HTTP status code, along with the raw JSON response and a convenient boolean if the request was successful or not.	

[1]: https://pushover.net