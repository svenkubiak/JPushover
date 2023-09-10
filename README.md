[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.svenkubiak/jpushover/badge.svg)](https://mvnrepository.com/artifact/de.svenkubiak/jpushover)

JPushover
================

A simple, minimal (18 KB), zero-dependency convenient class for sending messages to [Pushover][1] in Java.

Supports [Messages API][3], [Glances API][4] and [OpenClient API][5]. Send synchronous or asynchronous.

Requires Java 17.

Usage
------------------

Add the jpushover dependency to your pom.xml:

```
<dependency>
    <groupId>de.svenkubiak</groupId>
    <artifactId>jpushover</artifactId>
    <version>x.x.x</version>
</dependency>
```

Use the JPushover object with the required informations were you want

**Message API**

```
JPushover.messageAPI()
	.withToken("MyToken")
	.withUser("MyUser")
	.withMessage("MyMessage")
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

If you are using pushAsync remember to shutdown the ExecutorService that is handling the tasks by calling:

```
AsyncService.getInstance().shutdown();	
```

**Glance API**

```
JPushover.glanceAPI()
	.withToken("MyToken")
	.withUser("MyUser")
	.withText("MyText")
	.push();		
```

**OpenClient API**

Using the OpenClient API is a little bit more complex than Message API or Glance API.

```
//Prequisites - You require a message listener that listens for incoming messages

import de.svenkubiak.jpushover.listener.MessageListener;

public class MyMessageListener implements MessageListener {

    @Override
    public void onMessage() {
        // TODO Auto-generated method stub
    }

    @Override
    public void onError() {
        // TODO Auto-generated method stub   
    }
}


//1 - Login (required once!)
PushoverResponse pushoverResponse = JPushover
	.openClientAPI()
	.login("YourUsername", "YourPassword");

//1 - Login with Two-Factor authentication enabled (required once!)
PushoverResponse pushoverResponse = JPushover
	.openClientAPI()
	.login("YourUsername", "YourPassword", "123456");

//2 - Parse secret from JSON response and store it
String secret = MyJsonParser(pushoverResponse.getResponse());

//3 - Register a new device (required once)
PushoverResponse pushoverResponse = JPushover
	.openClientAPI()
	.registerDevice(secret, "YourDeviceName");

//4 - Parse deviceId from JSON response and store it
String deviceId = MyJsonParser(pushoverResponse.getResponse());

//5 - Create a new listener class which implements de.svenkubiak.jpushover.listener.MessageListener
MyMessageListener listener = new MyMessageListener();

//6 - Open a new WebSocket connection passing an instance of your message listener
JPushover.openClientAPI().open(secret, deviceId, listener)

//7 - Handle messages and errors via your listener
listener.onMessage();
lsitener.onError();

//Fetch existing messages via message()
JPushover
	.openClientAPI()
     .messages(secret, deviceId);

```

Once new messages are pushed to your device the onMessage method of your message listener is called. If an error occurs, the onError message of your message listener is called.

If you require to close the WebSocket connection, call the close() method and re-open the connection.


```
//Close connection
JPushover.openClientAPI().close();

//Re-open
JPushover.openClientAPI().open(secret, deviceId, new MyMessageListener())

```


[1]: https://pushover.net
[2]: https://pushover.net/api
[3]: https://pushover.net/api
[4]: https://pushover.net/api/glances
[5]: https://pushover.net/api/client