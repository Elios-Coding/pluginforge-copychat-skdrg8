package com.pluginforge.copychat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import io.papermc.paper.event.player.PlayerSystemMessageEvent;

public class SystemMessageListener implements Listener {

    /**
     * Intercepts system messages (broadcasts, command feedback, etc.) sent to players.
     * Note: This requires Paper's PlayerSystemMessageEvent.
     */
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onSystemMessage(PlayerSystemMessageEvent event) {
        Component original = event.getMessage();
        
        // Extract plain text for the clipboard
        String plainText = PlainTextComponentSerializer.plainText().serialize(original);
        
        // Apply the click and hover events to the existing component structure
        // This preserves existing colors/formatting while adding the interaction
        Component modified = original
                .clickEvent(ClickEvent.copyToClipboard(plainText))
                .hoverEvent(HoverEvent.showText(Component.text("§eClick to copy system message")));
        
        event.setMessage(modified);
    }
}
