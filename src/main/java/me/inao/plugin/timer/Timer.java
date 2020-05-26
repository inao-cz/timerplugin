package me.inao.plugin.timer;

import lombok.Getter;
import org.bukkit.entity.Player;
@Getter
public class Timer {
    private final Main main;
    private long start;
    private String mode;
    public Timer(Main main, long start, String mode, Player player){
        this.main = main;
        this.start = start;
        this.mode = mode;
        main.getTimers().put(player, this);
    }
    public long stop(long stop, Player player){
        main.getTimers().remove(player);
        return stop-this.start;
    }
}
