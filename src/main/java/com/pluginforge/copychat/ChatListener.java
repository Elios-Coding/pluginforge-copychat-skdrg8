package com.pluginforge.copychat;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChat(AsyncChatEvent event) {
        // We modify the renderer to wrap the final message in our copy-to-clipboard logic
        event.renderer((source, sourceDisplayName, message, viewer) -> {
            // Reconstruct a standard chat format: <Name> Message
            Component fullMessage = Component.text()
                    .append(Component.text("<"))
                    .append(sourceDisplayName)
                    .append(Component.text("> "))
                    .append(message)
                    .build();

            String plainText = PlainTextComponentSerializer.plainText().serialize(fullMessage);

            return fullMessage
                    .clickEvent(ClickEvent.copyToClipboard(plainText))
                    .hoverEvent(HoverEvent.showText(Component.text("§eClick to copy message")));
        });
    }
}
