package org.jointheleague.modules;

import org.javacord.api.entity.message.MessageAuthor;
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
}
