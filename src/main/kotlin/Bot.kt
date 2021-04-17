package com.github.fabricservertools.discord

import com.github.fabricservertools.discord.extensions.LinksExtension
import com.github.fabricservertools.discord.extensions.ShutdownExtension
import com.github.fabricservertools.discord.extensions.pronouns
import com.kotlindiscord.kord.extensions.ExtensibleBot
import dev.kord.common.annotation.KordPreview
import kotlin.system.exitProcess

@KordPreview
suspend fun main(args: Array<String>) {
    var token = System.getenv("FST_DISCORD_TOKEN")
    if (token != null) {
        println("Found token in environment variable")
    } else {
        for (arg in args) {
            if (arg.startsWith("-token")) {
                println("Found token in CLI args")
                token = arg.replace("-token", "").replace("\"".toRegex(), "")
            } else {
                exit("Failed to locate token in CLI args", -1)
            }
        }
    }
    val bot = ExtensibleBot(token) {
        commands {
            prefix { "!" }
        }

        extensions {
            add(::LinksExtension)
            pronouns {
                map("\uD83D\uDD34", "he/him")
                map("\uD83D\uDFE1", "they/them")
                map("\uD83D\uDFE2", "she/her")
            }
            add(::ShutdownExtension)
        }
    }

    // Begin!
    bot.start()
}

fun exit(reason: String?, code: Int) {
    println(reason)
    exitProcess(code)
}
