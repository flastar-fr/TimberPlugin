package fr.flastar.timberplugin;

import fr.flastar.timberplugin.Listeners.LogBrokenListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class TimberPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Timber Plugin enabled");
        getServer().getPluginManager().registerEvents(new LogBrokenListener(), this);
    }
}
