package io.msterhuj.minutedupeuple.events.commands;

import io.msterhuj.minutedupeuple.Main;
import io.msterhuj.minutedupeuple.musics.MusicManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.time.Instant;

public class StopCommand {
    public StopCommand(GuildMessageReceivedEvent event, String[] args){

        if (event.getMember().getVoiceState().inVoiceChannel()) {

            Member member = event.getMember();
            VoiceChannel voiceChannel = member.getVoiceState().getChannel();
            MusicManager musicManager = Main.musicManager;
            musicManager.stop(event.getGuild());
            event.getChannel().sendMessage("A la prochaine !").queue();

        } else {
            EmbedBuilder builder = new EmbedBuilder();

            builder.setColor(Color.RED);
            builder.setTitle("Attends quoi ? :confused:");
            builder.setFooter(event.getAuthor().getName(), event.getAuthor().getAvatarUrl());
            builder.setTimestamp(Instant.now());

            event.getChannel().sendMessage(builder.build()).queue();
        }
    }
}
