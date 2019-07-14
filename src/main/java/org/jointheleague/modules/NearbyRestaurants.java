package org.jointheleague.modules;

import java.util.Random;

import org.javacord.api.event.message.MessageCreateEvent;

public class NearbyRestaurants extends CustomMessageCreateListener {

	public NearbyRestaurants(String channelName) {
		super(channelName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(MessageCreateEvent event) {
		// TODO Auto-generated method stub
		if(event.getMessageContent().startsWith("!food")) {
			
			String keyword = event.getMessageContent().replaceAll(" ", "").replace("!food","").toLowerCase();
			
			event.getChannel().sendMessage("Order here: https://www.zomato.com/san-diego/restaurants/" + keyword);
		}
		
		if(event.getMessageContent().contains("hungry")) {
			
			String[] cuisines = {"chinese", "mexican", "sushi", "burger", "pizza", "seafood", "italian", "fast-food"};
			Random rand = new Random();
			
			
			event.getChannel().sendMessage("Hungry? Here's some options: https://www.zomato.com/san-diego/restaurants/" + cuisines[rand.nextInt(7)]);
			
		}
	}

}
