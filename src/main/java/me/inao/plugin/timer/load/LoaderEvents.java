package me.inao.plugin.timer.load;

import lombok.RequiredArgsConstructor;
import me.inao.plugin.timer.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.Set;

@RequiredArgsConstructor
public class LoaderEvents {
    private final Main main;
    public void load(){
        Reflections reflections = new Reflections("me.inao.plugin.timer.events", new SubTypesScanner());
        Set<Class<? extends Listener>> events = reflections.getSubTypesOf(Listener.class);
        for (Class<? extends Listener> event : events){
            try{
                Bukkit.getPluginManager().registerEvents(event.getDeclaredConstructor(new Class[]{Main.class}).newInstance(main), main);
                main.sendMessage("Loaded event " + event.getSimpleName());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
