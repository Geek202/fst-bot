package com.github.fabricservertools.discord;

import com.github.fabricservertools.discord.listeners.CommandListeners;
import com.github.fabricservertools.discord.listeners.PronounListeners;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class FstDiscord {

    public static void main(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("-token")) {
                String token = arg.replace("-token", "").replaceAll("\"", "");
                System.out.println("Found token in CLI args");
                JDABuilder builder = JDABuilder.createDefault(token);

                builder.addEventListeners(new PronounListeners());
                builder.addEventListeners(new CommandListeners());

                try {
                    builder.build();
                } catch (LoginException e) {
                    e.printStackTrace();
                    exit("Invalid token in CLI args", -1);
                }
            } else {
                exit("Failed to locate token in CLI args", -1);
            }
        }
    }
    public static void exit(String reason, int code) {
        System.out.println(reason);
        System.exit(code);
    }
}
