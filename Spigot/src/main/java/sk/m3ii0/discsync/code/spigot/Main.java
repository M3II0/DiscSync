package sk.m3ii0.discsync.code.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import sk.m3ii0.discsync.code.shared.DiscordProvider;

public class Main extends JavaPlugin {
	
	@Override
	public void onLoad() {
		super.onLoad();
	}
	
	@Override
	public void onEnable() {
		
		DiscordProvider.get().connect("MTEzNjQwNTYyOTYzMjc4NjU1NA.Gv4y-X.FUDcWZpFxeT8ZS-Nghen_azn3Mw1el4onmgqTU");
		DiscordProvider.get().sendTestMessage("992799204521017415");
		
	}
	
	@Override
	public void onDisable() {
		
		DiscordProvider.get().disconnect();
		
	}
	
}