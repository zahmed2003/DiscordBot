package org.jointheleague.modules;

import org.javacord.api.entity.message.MessageAuthor;
<<<<<<< HEAD
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

public class MusicMessageListener extends CustomMessageCreateListener{
	private static final String COMMAND = "!join";
	private static final String RESPONSE = "Joining";

	public MusicMessageListener(String channelName) {
		super(channelName);
	}

	@Override
	public void handle(MessageCreateEvent event) {
		if (event.getMessageContent().equalsIgnoreCase(COMMAND)) {
			event.getChannel().sendMessage(RESPONSE);
			MessageAuthor u = event.getMessageAuthor();
			
}
	}
=======
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

>>>>>>> development
}
