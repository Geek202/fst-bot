package com.github.fabricservertools.discord.listeners;

import com.github.fabricservertools.discord.commands.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CommandListeners extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentDisplay();
        if (!message.startsWith("!")) return;
        message = message.substring(1);
        if (message.equals("help")) {
            LinkCommands.sendHelpCommand(event.getChannel());
        } else if (message.startsWith("cf ")) {
            LinkCommands.sendCfLink(event.getChannel(), message.substring("cf ".length()));
        } else if (message.startsWith("github ")) {
            LinkCommands.sendGithubLink(event.getChannel(), message.substring("github ".length()));
        } else if (message.startsWith("stop") && (event.getChannel().getName().equals("bot") || Objects.requireNonNull(event.getMember()).isOwner())) {
            event.getChannel().sendMessage("Stopping FabricServerToolsBot").queue();
            event.getJDA().shutdownNow();
            System.exit(0);
        }
    }

}
