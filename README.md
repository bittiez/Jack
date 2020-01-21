[ ![Bugs, Issues, Feature Requests](https://www.mediafire.com/convkey/2320/x80qtabf3auhhjr6g.jpg) ](../../issues)
[ ![Donate](https://www.mediafire.com/convkey/910d/z8160kkzvezi4km6g.jpg) ](https://paypal.me/bittiez)

# Jack
A Spigot 1.15 plugin to answer player questions directly in chat.


# Description
Jack is a lightweight chat bot, he will try to answer questions your players ask in chat.


# Usage
Jack can be used in two ways:
### 1
Via the config, set up a prefix where the user would type something similar to:

`"?How can i vote?"` where the prefix is `?`

or `"Hey jack, do this server have discord?"` with the prefix being `Hey jack`.
### 2
If you do not set a prefix, Jack will check for answers to anything said in chat.

*Note: I recommend using method 1, but you are more than welcome to experiment with 2.*

# Permissions
[View permissions here](../../blob/master/src/plugin.yml)


# Installation

- Place the jar file in your plugins folder
- Restart your server
- Edit the `config.yml` file
- Edit the `chat.yml` file
- Restart your server

Alternatively if you create a folder within your plugins folder named Jack, and create the `config.yml` and `chat.yml`
 files first -> copy the contents from here\[[config](../../blob/master/src/config.yml)|[chat](../../blob/master/src/chat.yml)\] -> Place the jar in your plugins folder, restart and you are ready to roll.

# Configuration
[View default configuration file here](../../blob/master/src/config.yml)

[View default chat file here](../../blob/master/src/chat.yml)

# Planned features
- Add permissions(So Jack will only respond to players with the right permissions).
- Add a /reload command so you don't need to restart to update the config files.