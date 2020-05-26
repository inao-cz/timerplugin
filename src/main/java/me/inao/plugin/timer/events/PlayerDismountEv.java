package me.inao.plugin.timer.events;

import lombok.RequiredArgsConstructor;
import me.inao.plugin.timer.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

@RequiredArgsConstructor
public class PlayerDismountEv implements Listener {
    private final Main main;
    @EventHandler
    public void onDismount(EntityDismountEvent e){
        if(main.getTimers().containsKey((Player)e.getDismounted().getPassenger())){
            ((Cancellable)e).setCancelled(true);
        }
    }
}
