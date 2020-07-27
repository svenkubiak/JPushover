[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.svenkubiak/jpushover/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.svenkubiak/jpushover)

JPushover
================

A simple, minimal (18 KB), zero-dependency convenient class for sending messages to [Pushover][1] in Java.

Supports [Messages API][3] and [Glances API][4], synchronous and asynchronous sending.

Requires Java 11.

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
JPushover.newMessage()
	.withToken("MyToken")
	.withUser("MyUser")
	.withMessage("MyMessage")
	.push();

JPushover.newGlance()
	.withToken("MyToken")
	.withUser("MyUser")
	.withText("MyText")
	.push();		
```

When using the Message API you can additionally add available options from the official [Pushover documentation][2]	

You can also validate a user and token using the following method

```
boolean valid = JPushover.newMessage()
	.withToken("MyToken")
	.withUser("MyUser")
	.validate();
```		

If you want more information and/or the response from the Pushover API, use the PushoverResponse object.

```
PushoverResponse PushoverResponse = JPushover.newMessage()
	.withToken("MyToken")
	.withUser("MyUser")
	.withMessage("MyMessage")
	.push();
```		

The PushoverResponse will return the raw HTTP status code, along with the raw JSON response and a convenient boolean if the request was successful or not. Additionally you get the rate limit information along with the response (rate limit, remaining, reset timestamp).

Sending a Message or Glance asynchronous is simple. Just call the pushAsync() method instead of push(). Examples:

```
JPushover.newMessage()
	.withToken("MyToken")
	.withUser("MyUser")
	.withMessage("MyMessage")
	.pushAsync();

JPushover.newGlance()
	.withToken("MyToken")
	.withUser("MyUser")
	.withText("MyText")
	.pushAsync();		
```

[1]: https://pushover.net
[2]: https://pushover.net/api
[3]: https://pushover.net/api
[4]: https://pushover.net/api/glances
