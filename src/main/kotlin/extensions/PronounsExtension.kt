package com.github.fabricservertools.discord.extensions

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.builders.ExtensibleBotBuilder
import com.kotlindiscord.kord.extensions.extensions.Extension
import dev.kord.core.entity.channel.MessageChannel
import dev.kord.core.event.message.ReactionAddEvent
import dev.kord.core.event.message.ReactionRemoveEvent
import kotlinx.coroutines.flow.collect

class PronounsExtension(
    private val reactionMap: Map<String, String>,
    bot: ExtensibleBot
) : Extension(bot) {
    override val name = "Pronouns"

    override suspend fun setup() {
        event<ReactionAddEvent> {
            check {
                it.guild != null
                        && it.channel.asChannel().data.name.value == "welcome"
                        && it.emoji.name in reactionMap.keys
            }

            action {
                val roleName = reactionMap[event.emoji.name]!!

                event.guild!!.roles.collect { role ->
                    if (role.name == roleName) {
                        event.userAsMember?.addRole(role.id, reason = "Reaction roles")
                    }
                }
            }
        }

        event<ReactionRemoveEvent> {
            check {
                it.guild != null
                        && (it.channel.asChannel()).data.name.value == "welcome"
            }

            action {
                val roleName = reactionMap[event.emoji.name]!!

                event.guild!!.roles.collect { role ->
                    if (role.name == roleName) {
                        event.userAsMember?.removeRole(role.id, reason = "Reaction roles")
                    }
                }
            }
        }
    }

    class Builder(
        private val reactionMap: MutableMap<String, String>,
    ) {
        fun map(emoji: String, roleName: String) {
            reactionMap[emoji] = roleName
        }
    }
}

fun ExtensibleBotBuilder.ExtensionsBuilder.pronouns(builder: PronounsExtension.Builder.() -> Unit) {
    val map = HashMap<String, String>()
    val b = PronounsExtension.Builder(map)
    b.builder()
    add { bot -> PronounsExtension(map, bot) }
}
