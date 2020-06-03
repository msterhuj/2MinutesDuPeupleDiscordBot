package io.msterhuj.minutedupeuple.events.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.time.Instant;


public class HelpCommand {
    public HelpCommand(GuildMessageReceivedEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.decode("#2E86C1"));
        builder.setTitle("Liste des commandes");
        builder.setDescription("Bugs ? https://github.com/msterhuj/2MinutesDuPeupleDiscordBot/issues");
        builder.setFooter(event.getAuthor().getName(), event.getAuthor().getAvatarUrl());
        builder.setTimestamp(Instant.now());
        builder.addField("Liste des sketchs","" +
                "&2list [*id*] -> Ne pas mettre id pour voir toutes les catégories et remplacer id par le numéro du dossier", false);
        builder.addField("Ecouter", "" +
                "&2play [*dossier*] [*sketch/all*] -> remplacer le dossier pas son numéro et de même pour le sketch ou mettre all pour écouter toute la catégorie\n" +
                "&2skip -> passe au sketch suivant\n" +
                "&2purge -> nettoyer la liste\n" +
                "&2stop -> arrête les sketchs en cours et fait partir le bot", false);
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
