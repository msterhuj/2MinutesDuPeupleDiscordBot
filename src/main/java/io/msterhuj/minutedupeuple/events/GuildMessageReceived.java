package io.msterhuj.minutedupeuple.events;

import io.msterhuj.minutedupeuple.events.commands.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class GuildMessageReceived extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String[] args = event.getMessage().getContentDisplay().toLowerCase().split(" ");

        if (args[0].startsWith("&2")) {
            args[0] = args[0].substring(2);
            switch (args[0]) {
                case "help":
                    new HelpCommand(event);
                    break;
                case "list":
                    new ListCommand(event, args);
                    break;
                case "play":
                    new PlayCommand(event, args);
                    break;
                case "skip":
                    new SkipCommands(event, args);
                    break;
                case "purge":
                    new PurgeCommands(event, args);
                    break;
                case "stop":
                    new StopCommand(event, args);
                    break;
                default:
                    System.out.println("Hum hum... " + event.getAuthor().getName());
            }
        }
    }
}
