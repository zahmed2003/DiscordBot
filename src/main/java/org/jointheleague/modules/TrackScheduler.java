package org.jointheleague.modules;

import java.util.ArrayList;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEvent;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventListener;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class TrackScheduler implements AudioEventListener
{
AudioPlayer player;
ArrayList<AudioTrack> tracks = new ArrayList<>();
	public TrackScheduler(AudioPlayer player) {
		this.player = player;
	}

	@Override
	public void onEvent(AudioEvent event) {
		player.playTrack(tracks.get(0));
		
	}

	public void queue(AudioTrack track) {
		tracks.add(track);
		
	}
	public void startTrack() {
		player.playTrack(tracks.get(0));
	}
	

}
