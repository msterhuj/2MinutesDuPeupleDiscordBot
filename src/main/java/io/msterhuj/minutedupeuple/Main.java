package io.msterhuj.minutedupeuple;

import io.msterhuj.minutedupeuple.events.GuildMessageReceived;
import io.msterhuj.minutedupeuple.models.Sketch;
import io.msterhuj.minutedupeuple.models.SketchCategory;
import io.msterhuj.minutedupeuple.musics.MusicManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static File path;
    public static List<SketchCategory> sketches;
    public static MusicManager musicManager;

    public static void main(String[] args) throws LoginException {
        if (!args[0].isEmpty() && !args[1].isEmpty()) {
            Main.path = new File(args[1]);
            Main.sketches = loadSketches();
            Main.musicManager = new MusicManager();

            JDA jda = new JDABuilder()
                    .setToken(args[0])
                    .setAutoReconnect(true)
                    .setStatus(OnlineStatus.ONLINE)
                    .setActivity(Activity.playing("faire des calembours"))
                    .addEventListeners(new GuildMessageReceived())
                    .build();
        } else {
            System.out.println("Require args !");
            System.out.println("java -jar <jarfile>.jar <discord token> <path file to all sketches>");
        }
    }

    public static List<SketchCategory> loadSketches() {

        Main.sketches = new ArrayList<>();

        List<SketchCategory> categories = new ArrayList<>();

        int indexFolders = 0;
        int indexSketches = 0;

        for (File folder : Main.path.listFiles()) {
            if (folder.isDirectory()) {

                SketchCategory category = new SketchCategory();
                category.setId(indexFolders);
                category.setName(folder.getName().replace("_", " "));
                category.setFolder(folder);

                List<Sketch> sketches = new ArrayList<>();
                for (File file : new File(folder.getAbsolutePath()).listFiles()) {
                    sketches.add(new Sketch().builder()
                            .id(indexSketches)
                            .name(file.getName().replace("_", " ").replace(".mp3", ""))
                            .sketch(file)
                            .build());
                    indexSketches++;
                }
                category.setSketches(sketches);
                categories.add(category);
                indexSketches=0;
                indexFolders++;
            }
        }
        return categories;
    }
}
