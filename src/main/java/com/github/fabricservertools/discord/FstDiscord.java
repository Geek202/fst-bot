package com.github.fabricservertools.discord;

import com.github.fabricservertools.discord.listeners.CommandListeners;
import com.github.fabricservertools.discord.listeners.PronounListeners;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class FstDiscord {

    public static void main(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("-token")) {
                String token = arg.replace("-token", "");
                System.out.println("Found token in CLI args");
                JDABuilder builder = JDABuilder.createDefault(token);

                builder.addEventListeners(new PronounListeners());
                builder.addEventListeners(new CommandListeners());

                try {
                    builder.build();
                } catch (LoginException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Failed to locate token in CLI args");
                System.exit(-1);
            }
        }
    }
}
