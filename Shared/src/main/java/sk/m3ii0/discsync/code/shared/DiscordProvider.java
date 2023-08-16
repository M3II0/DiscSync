package sk.m3ii0.discsync.code.shared;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.requests.GatewayIntent;
import sk.m3ii0.discsync.code.shared.connection.ConnectionResult;

import javax.security.auth.login.LoginException;
import java.util.*;

public class DiscordProvider {
	
	/*
	*
	* Values
	*
	* */
	
	private static final DiscordProvider provider = new DiscordProvider();
	private static final Map<String, String> channelsNameId = new HashMap<>();
	
	private JDA jda;
	
	/*
	*
	* Constructor
	*
	* */
	
	public DiscordProvider() {}
	
	/*
	*
	* Methods
	*
	* */
	
	public static DiscordProvider get() {
		return provider;
	}
	
	/*
	*
	* Connections
	*
	* */
	
	public ConnectionResult connect(String token) {
		Set<GatewayIntent> intents = new HashSet<>();
		intents.add(GatewayIntent.GUILD_PRESENCES);
		intents.add(GatewayIntent.GUILD_EMOJIS_AND_STICKERS);
		JDABuilder builder = JDABuilder.create(token, intents);
		try {
			jda = builder.build();
		} catch (LoginException e) {
			return ConnectionResult.INVALID_TOKEN;
		}
		try {
			jda.awaitReady();
		} catch (InterruptedException e) {
			return ConnectionResult.INVALID_ERROR;
		}
		return ConnectionResult.CONNECTED;
	}
	
	public void disconnect() {
		jda.shutdown();
	}
	
	/*
	*
	* Messages
	*
	* */
	
	public void sendMessage(String channelId, String text) {
		if (jda == null) {
			return;
		}
		TextChannel textChannel = jda.getTextChannelById(channelId);
		if (textChannel == null) {
			return;
		}
		textChannel.sendMessage(text).queue();
	}
	
	public void sendMessage(String channelId, Collection<MessageEmbed> embeds) {
		if (jda == null) {
			return;
		}
		TextChannel textChannel = jda.getTextChannelById(channelId);
		if (textChannel == null) {
			return;
		}
		textChannel.sendMessageEmbeds(embeds).queue();
	}
	
	public void sendMessage(String channelId, MessageEmbed embed) {
		sendMessage(channelId, Collections.singleton(embed));
	}
	
	public void sendTestMessage(String channelId) {
		if (jda == null) {
			return;
		}
		TextChannel textChannel = jda.getTextChannelById(channelId);
		if (textChannel == null) {
			return;
		}
		textChannel.sendMessage("Test message")
		 .addActionRow(
		  Button.primary("test", "Click here"),
		  Button.secondary("test1", Emoji.fromFormatted("<:amazingtitles:1128333067749183589>"))
		 )
		 .queue();
	}
	
}
