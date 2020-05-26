package me.inao.plugin.timer;

import lombok.Getter;
import me.inao.plugin.timer.load.LoaderEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

@Getter
public class Main extends JavaPlugin {
    private HashMap<Player, Timer> timers = new HashMap<>();
    private HashMap<String, Double> constants = new HashMap<>();
    @Override
    public void onEnable() {
        sendMessage("Starting up :)");
        new LoaderEvents(this).load();
    }

    @Override
    public void onDisable() {
        sendMessage("Shutting down :(");
    }

    public void sendMessage(String message){
        Bukkit.getConsoleSender().sendMessage(message);
    }
}
