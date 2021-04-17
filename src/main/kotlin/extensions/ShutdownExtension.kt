package com.github.fabricservertools.discord.extensions

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.utils.respond
import kotlinx.coroutines.cancel
import kotlin.system.exitProcess

class ShutdownExtension(bot: ExtensibleBot) : Extension(bot) {
    override val name = "Shutdown"

    override suspend fun setup() {
        command {
            name = "stop"
            description = "Shuts down the bot, owner only"

            check {
                it.message.getAuthorAsMember()?.isOwner() == true
            }

            action {
                message.respond("Shutting down...")
                bot.kord.logout()
                bot.kord.cancel()
                exitProcess(0)
            }
        }
    }
}
