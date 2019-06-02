package org.jointheleague.modules;

import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.event.message.MessageCreateEvent;
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
			ServerVoiceChannel c = (ServerVoiceChannel) event.getApi().getChannelById(m.getId()).get();
			event.getApi().getServerById("").get().moveYourself(c);
			
		}
	}

}
