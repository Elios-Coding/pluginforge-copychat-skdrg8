package com.pluginforge.copychat;

import org.bukkit.plugin.java.JavaPlugin;

public class CopyChat extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register the chat renderer for player chat
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        
        // Register the system message interceptor
        getServer().getPluginManager().registerEvents(new SystemMessageListener(), this);
        
        getLogger().info("CopyChat enabled. All chat and system messages are now clickable to copy.");
    }

    @Override
    public void onDisable() {
        getLogger().info("CopyChat disabled.");
    }
}
