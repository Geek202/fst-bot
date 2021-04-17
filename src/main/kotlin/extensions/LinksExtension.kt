package com.github.fabricservertools.discord.extensions

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.commands.converters.string
import com.kotlindiscord.kord.extensions.commands.parser.Arguments
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.utils.respond
import dev.kord.common.annotation.KordPreview

@KordPreview
class LinksExtension(bot: ExtensibleBot) : Extension(bot) {
    override val name = "Links"

    override suspend fun setup() {
        command(LinksExtension::CurseforgeArgs) {
            name = "curseforge"
            aliases = arrayOf(
                "cf",
                "curse"
            )

            description = "Generates a CurseForge mod link given a mod's slug"

            action {
                message.respond("https://curseforge.com/minecraft/mc-mods/${arguments.name}")
            }
        }

        command(LinksExtension::GithubArgs) {
            name = "github"
            aliases = arrayOf("gh")

            description = "Generates a GitHub url given a user and a repository name"

            action {
                message.respond("https://github.com/${arguments.owner}/${arguments.repo}")
            }
        }
    }

    class GithubArgs : Arguments() {
        val owner by string(displayName = "owner", description = "The name of the user or organisation the repository belongs to")
        val repo by string(displayName = "repository", description = "The name of the repository")
    }

    class CurseforgeArgs : Arguments() {
        val name by string(displayName = "slug", description = "Slug (URL part) of the mod to generate a link for")
    }
}
