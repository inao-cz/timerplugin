package me.inao.plugin.timer.events;

import lombok.AllArgsConstructor;
import me.inao.plugin.timer.Main;
import me.inao.plugin.timer.Timer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import java.time.Instant;
@AllArgsConstructor
public class PlayerStartEvent implements Listener {
    private final Main main;
    @EventHandler
    public void onPlayerStart(PlayerInteractEvent e){
        if(main.getConstants().size() == 0){
            main.getConstants().put("SPEED", 4.898);
            main.getConstants().put("STEP0.5", 5.798);
            main.getConstants().put("STEP1.0", 15.195);
        }
        if(Action.RIGHT_CLICK_BLOCK == e.getAction() && (e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN)){
            Sign sign = ((Sign) e.getClickedBlock().getState());
            Location loc = e.getClickedBlock().getLocation();
            loc.setY(loc.getBlockY() - 2.00);
            loc.setZ(loc.getBlockZ() + 3.50);
            loc.setX(loc.getBlockX() + 0.50);
            String name = (sign.getLine(0) + sign.getLine(1)).toUpperCase();
            e.getPlayer().teleport(loc);
            Entity entity = e.getPlayer().getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            entity.setPassenger(e.getPlayer());
            e.getPlayer().sendMessage("§4You will be released in 5 seconds!");
            new BukkitRunnable(){
                public void run(){
                    new Timer(main, Instant.now().toEpochMilli(), name, e.getPlayer());
                    entity.remove();
                }
            }.runTaskLater(main, 20*5);
        }
    }
}
