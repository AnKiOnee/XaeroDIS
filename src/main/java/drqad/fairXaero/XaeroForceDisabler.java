package drqad.fairXaero;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class XaeroForceDisabler extends JavaPlugin implements Listener {

    private boolean disableMinimap;
    private boolean disableWorldMap;
    private boolean disableRadar;
    private boolean disableCaveMode;

    @Override
    public void onEnable() {
        // Сохраняем и загружаем конфигурацию
        saveDefaultConfig();
        loadSettings();

        // Регистрируем события
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("XaeroForceDisabler успешно адаптирован и запущен на Paper 26.3!");
    }

    private void loadSettings() {
        this.disableMinimap = getConfig().getBoolean("settings.disable-minimap", false);
        this.disableWorldMap = getConfig().getBoolean("settings.disable-worldmap", false);
        this.disableRadar = getConfig().getBoolean("settings.disable-radar", true);
        this.disableCaveMode = getConfig().getBoolean("settings.disable-cave-mode", true);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        // Отправка пакетов Xaero Fair-Play через современные Adventure компоненты чата.
        // Мод Xaero считывает определенные последовательности цветовых кодов при входе.
        
        if (disableMinimap) {
            player.sendMessage(Component.text("§f§a§i§r§x§a§e§r§o§m"));
        }
        if (disableWorldMap) {
            player.sendMessage(Component.text("§f§a§i§r§x§a§e§r§o§w"));
        }
        if (disableRadar) {
            // Блокировка радара сущностей/игроков
            player.sendMessage(Component.text("§f§a§i§r§x§a§e§r§o§e"));
        }
        if (disableCaveMode) {
            // Блокировка карты пещер (рендеринга под землей)
            player.sendMessage(Component.text("§f§a§i§r§x§a§e§r§o§c"));
        }
    }
}
