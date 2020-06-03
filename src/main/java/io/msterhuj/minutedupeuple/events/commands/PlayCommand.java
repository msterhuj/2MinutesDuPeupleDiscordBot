package io.msterhuj.minutedupeuple.events.commands;

import io.msterhuj.minutedupeuple.musics.MusicManager;
import io.msterhuj.minutedupeuple.Main;
import io.msterhuj.minutedupeuple.models.Sketch;
import io.msterhuj.minutedupeuple.models.SketchCategory;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.time.Instant;
import java.util.NoSuchElementException;

public class PlayCommand {
    public PlayCommand(GuildMessageReceivedEvent event, String[] args) {

        if (event.getMember().getVoiceState().inVoiceChannel()) {
            if (args.length == 3) {
                System.out.println(args.length);
                try {

                    SketchCategory category = Main.sketches.stream().filter(sketchCategory -> sketchCategory.getId() == Integer.parseInt(args[1])).findFirst().get();

                    if (args[2].equalsIgnoreCase("all")) { // load all sketch
                        MusicManager musicManager = Main.musicManager;
                        musicManager.loadAndPlayAll(event.getChannel(), category.getSketches(), event.getMember().getVoiceState().getChannel());
                    } else { // load one sketch
                        Sketch sketch = category.getSketches().stream().filter(sond -> sond.getId() == Integer.parseInt(args[2])).findFirst().get();

                        EmbedBuilder builder = new EmbedBuilder();
                        builder.setColor(Color.decode("#7bdd69"));
                        builder.setTitle("Écoute du sketch " + sketch.getName() + " de la catégorie " + category.getName());
                        builder.setDescription("taper &2stop pour stopper le sketch");
                        builder.setFooter(event.getAuthor().getName(), event.getAuthor().getAvatarUrl());
                        builder.setTimestamp(Instant.now());

                        event.getChannel().sendMessage(builder.build()).queue();

                        Member member = event.getMember();
                        VoiceChannel voiceChannel = member.getVoiceState().getChannel();
                        Main.musicManager.loadAndPlay(event.getChannel(), sketch.getSketch(), voiceChannel);
                    }
                } catch (NoSuchElementException e) {
                    EmbedBuilder builder = new EmbedBuilder();

                    builder.setColor(Color.RED);
                    builder.setTitle("Une erreur est survenue :confused:");
                    builder.setDescription("Je n'ai rien trouvé...");
                    builder.setFooter(event.getAuthor().getName(), event.getAuthor().getAvatarUrl());
                    builder.setTimestamp(Instant.now());

                    event.getChannel().sendMessage(builder.build()).queue();
                }
            } else {
                event.getChannel().sendMessage("&2 play (id folder) (id sketch)").queue();
            }
        } else {
            EmbedBuilder builder = new EmbedBuilder();

            builder.setColor(Color.RED);
            builder.setTitle("Tu ne peux pas l'ecouter si tu n'es pas connecté :confused:");
            builder.setFooter(event.getAuthor().getName(), event.getAuthor().getAvatarUrl());
            builder.setTimestamp(Instant.now());

            event.getChannel().sendMessage(builder.build()).queue();
        }
    }
}
