# fst-bot
 A simple discord bot used on the Fabric Server Tools discord server

Requires Java version 15 or greater

### Build

##### Windows

`gradlew shadowJar`

##### Linux/MacOS

`./gradlew shadowJar`

### Use

`java -jar fst-bot-version-shadow.jar -token"<your_bot_token>"`

OR 

Set a system environment variable named `FST_DISCORD_TOKEN` with your discord token and run:

`java -jar fst-bot-version-shadow.jar`

### Commands

Run `!help` in your discord to see a list of features

This bot is pretty much custom-made for our server: I may however expose more commands to config values in the future
