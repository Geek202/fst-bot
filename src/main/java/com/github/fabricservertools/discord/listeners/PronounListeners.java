package com.github.fabricservertools.discord.listeners;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.Objects;

public class PronounListeners extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {
        // Cancel processing if message isn't in #welcome
        if (!event.getChannel().getName().equals("welcome")) return;

        Role role;
        switch (event.getReactionEmote().toString()) {
            case "RE:U+1f534":
                role = event.getGuild().getRolesByName("he/him", false).get(0);
                event.getGuild().addRoleToMember(Objects.requireNonNull(event.getMember()), role).queue();
            case "RE:U+1f7e1":
                role = event.getGuild().getRolesByName("they/them", false).get(0);
                event.getGuild().addRoleToMember(Objects.requireNonNull(event.getMember()), role).queue();
            case "RE:U+1f7e2":
                role = event.getGuild().getRolesByName("she/her", false).get(0);
                event.getGuild().addRoleToMember(Objects.requireNonNull(event.getMember()), role).queue();
        }
    }

    @Override
    public void onMessageReactionRemove(@Nonnull MessageReactionRemoveEvent event) {
        if (!event.getChannel().getName().equals("welcome")) return;
        Role role;
        switch (event.getReactionEmote().toString()) {
            case "RE:U+1f534":
                role = event.getGuild().getRolesByName("he/him", false).get(0);
                event.getGuild().removeRoleFromMember(Objects.requireNonNull(event.getMember()), role).queue();
            case "RE:U+1f7e1":
                role = event.getGuild().getRolesByName("they/them", false).get(0);
                event.getGuild().removeRoleFromMember(Objects.requireNonNull(event.getMember()), role).queue();
            case "RE:U+1f7e2":
                role = event.getGuild().getRolesByName("she/her", false).get(0);
                event.getGuild().removeRoleFromMember(Objects.requireNonNull(event.getMember()), role).queue();
        }
    }
}
