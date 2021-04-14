package com.github.fabricservertools.discord.commands;

import net.dv8tion.jda.api.entities.MessageChannel;

public class LinkCommands {
    public static void sendCfLink(MessageChannel channel, String name) {
        channel.sendMessage(
                "https://curseforge.com/minecraft/mc-mods/" + name
        ).queue();
    }
    public static void sendGithubLink(MessageChannel channel, String args) {
        String[] split = args.split(" ");
        if(split.length != 2) channel.sendMessage("Invalid args - command requires <user> <repo>").queue();

        channel.sendMessage("https://github.com/" + split[0] + "/" + split[1]).queue();
    }
    public static void sendHelpCommand(MessageChannel channel) {
        channel.sendMessage(String.join("\n", "List of bot commands:", "React :red_circle: to a message in #welcome to get he/him role",
                "React :yellow_circle: in #welcome to get they/them role",
                "React :green_circle: in #welcome to get she/her role",
                "!help: lists commands",
                "!cf <mod>: links to the curseforge page of <mod>")).queue();
    }
}
