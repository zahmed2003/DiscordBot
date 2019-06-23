package org.jointheleague.modules;

import org.javacord.api.entity.message.MessageAuthor;

import org.javacord.api.event.message.MessageCreateEvent;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;


import org.javacord.api.entity.channel.*;
public class MusicMessageListener extends CustomMessageCreateListener{

	public MusicMessageListener(String channelName) {
		super(channelName);
		
	}
	@Override
	public void handle(MessageCreateEvent event) {
		if (event.getMessageContent().startsWith("!join")) {
			event.getChannel().sendMessage("Joining");
			MessageAuthor m = event.getMessageAuthor();
			ServerVoiceChannel c = event.getApi().getServerVoiceChannels().iterator().next();
			//event.getApi().getServerVoiceChanneById("").get().moveYourself(c);
			AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
			AudioSourceManagers.registerRemoteSources(playerManager);
			AudioPlayer player = playerManager.createPlayer();
			String sb = "";
			if (event.getMessageContent().contains("!play")) {
				 event.getChannel().sendMessage("Playing");
				String s = event.getMessageContent();
				
			 sb = s.substring(s.indexOf(" "), s.length()-1);
			
			}
	TrackScheduler trackScheduler = new TrackScheduler(player);
			player.addListener(trackScheduler);
	
			playerManager.loadItem("ytsearch: "+sb, new AudioLoadResultHandler() {
				  public void trackLoaded(AudioTrack track) {
				
				    trackScheduler.queue(track);
				  }

				  public void playlistLoaded(AudioPlaylist playlist) {
				    for (AudioTrack track : playlist.getTracks()) {
				      trackScheduler.queue(track);
				    }
				  }

				  public void noMatches() {
				    // Notify the user that we've got nothing
					  event.getChannel().sendMessage("Nothing found");
				  }

				  public void loadFailed(FriendlyException throwable) {
				    // Notify the user that everything exploded
					 event.getChannel().sendMessage("Error");
				  }
			});
			trackScheduler.startTrack();

		}
		
	}


}
