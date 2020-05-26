package me.inao.plugin.timer.events;

import lombok.RequiredArgsConstructor;
import me.inao.plugin.timer.Main;
import me.inao.plugin.timer.Timer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import java.time.Instant;

@RequiredArgsConstructor
public class PlayerMovementEvent implements Listener {
    private final Main main;
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(main.getTimers().containsKey(e.getPlayer())){
            Location location = e.getPlayer().getLocation();
            location.setY(location.getY()-1);
            if(location.getBlock().getType() == Material.EMERALD_BLOCK){
                Timer timer = main.getTimers().get(e.getPlayer());
                double end = timer.stop(Instant.now().toEpochMilli(), e.getPlayer());
                e.getPlayer().sendMessage("§bLegit time is: ~" + main.getConstants().get(timer.getMode()) + "s");
                double timeEnd = (end / 1000);
                e.getPlayer().sendMessage("§bYour time is " + timeEnd + "s");
                double percentil = (main.getConstants().get(timer.getMode()) / timeEnd - 1) * 100;
                if(!(percentil > 0.04) && !(percentil < -0.04)) e.getPlayer().sendMessage("§3Are you legit? You were vanilla jumping");
                else if(percentil > 0.06)e.getPlayer().sendMessage("§3You were faster for: " + percentil+"%");
                else if(percentil < -0.06)e.getPlayer().sendMessage("§3You were slower for: " + percentil + "%");
                e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
            }
        }
    }
}
