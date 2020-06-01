package io.msterhuj.minutedupeuple.events.commands;

import io.msterhuj.minutedupeuple.Main;
import io.msterhuj.minutedupeuple.models.Sketch;
import io.msterhuj.minutedupeuple.models.SketchCategory;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.time.Instant;
import java.util.NoSuchElementException;

public class ListCommand {
    public ListCommand(GuildMessageReceivedEvent event, String[] args) {
        if (args.length == 1) {
            getAllFolders(event, args);
        } else if (args.length == 2) {
            getFolderById(event, Integer.parseInt(args[1]));
        }
    }

    private void getFolderById(GuildMessageReceivedEvent event, int folderId) {
        try {

            SketchCategory category = Main.sketches.stream().filter(sketchCategory -> sketchCategory.getId() == folderId).findFirst().get();

            EmbedBuilder builder = new EmbedBuilder();
            StringBuilder sketchsBuilder = new StringBuilder();

            builder.setColor(Color.decode("#7bdd69"));
            builder.setTitle("Liste des sketch de " + category.getName());
            builder.setDescription("taper &2play suivi du numéro de dossier et du sketch pour l'écouter");
            builder.setFooter(event.getAuthor().getName(), event.getAuthor().getAvatarUrl());
            builder.setTimestamp(Instant.now());

            for (Sketch sketch : category.getSketches()) {
                sketchsBuilder
                        .append("**[")
                        .append(sketch.getId())
                        .append("]** ")
                        .append(sketch.getName())
                        .append("\n");
            }
            builder.addField("Liste des sketchs disponibles", sketchsBuilder.toString(), true);

            event.getChannel().sendMessage(builder.build()).queue();
        } catch (NoSuchElementException e) {
            EmbedBuilder builder = new EmbedBuilder();

            builder.setColor(Color.RED);
            builder.setTitle("Une erreur est survenue :confused:");
            builder.setDescription("L'Index : " + folderId + " n'est pas valide");
            builder.setFooter(event.getAuthor().getName(), event.getAuthor().getAvatarUrl());
            builder.setTimestamp(Instant.now());

            event.getChannel().sendMessage(builder.build()).queue();
        }
    }

    private void getAllFolders(GuildMessageReceivedEvent event, String[] args) {

        EmbedBuilder builder = new EmbedBuilder();
        StringBuilder categoriesBuilder = new StringBuilder();

        builder.setColor(Color.decode("#7bdd69"));
        builder.setTitle("Liste des catégories");
        builder.setDescription("taper &2list suivi du numéro de dossier que vous voulez voir");
        builder.setFooter(event.getAuthor().getName(), event.getAuthor().getAvatarUrl());
        builder.setTimestamp(Instant.now());

        for (SketchCategory category : Main.sketches) {
            categoriesBuilder
                    .append("**[")
                    .append(category.getId())
                    .append("]** ")
                    .append(category.getName())
                    .append(" ")
                    .append(category.getSketches().size())
                    .append("\n");
        }

        builder.addField("Liste des catégories ",categoriesBuilder.toString(),true);

        event.getChannel().sendMessage(builder.build()).queue();
    }
}
