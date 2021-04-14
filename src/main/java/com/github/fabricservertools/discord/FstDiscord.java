package com.github.fabricservertools.discord;

import com.github.fabricservertools.discord.listeners.CommandListeners;
import com.github.fabricservertools.discord.listeners.PronounListeners;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.util.Map;

public class FstDiscord {

    public static void main(String[] args) {
        String token = System.getenv("FST_DISCORD_TOKEN");
        if (token != null) {
            System.out.println("Found token in environment variable");
        } else {
            for (String arg : args) {
                if (arg.startsWith("-token")) {
                    System.out.println("Found token in CLI args");
                    token = arg.replace("-token", "").replaceAll("\"", "");

                } else {
                    exit("Failed to locate token in CLI args", -1);
                }
            }
        }
        JDABuilder builder = JDABuilder.createDefault(token);

        builder.addEventListeners(new PronounListeners());
        builder.addEventListeners(new CommandListeners());

        try {
            builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
            exit("Invalid token in either CLI args or environment variables", -1);
        }

    }
    public static void exit(String reason, int code) {
        System.out.println(reason);
        System.exit(code);
    }
}
