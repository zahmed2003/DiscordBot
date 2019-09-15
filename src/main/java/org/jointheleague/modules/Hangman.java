package org.jointheleague.modules;

import java.util.ArrayList;

import org.javacord.api.event.message.MessageCreateEvent;

public class Hangman extends CustomMessageCreateListener {

	private String current_guess;
	private String answer_string;
	private int num_incorrect = 0;
	
	private ArrayList<Character> wrong_guesses = new ArrayList<Character>();
	
	private boolean GAME_RUNNING = false;
	
	private final int NUM_MAX = 6;
	
	
	public Hangman(String channelName) {
		super(channelName);
	}

	@Override
	public void handle(MessageCreateEvent event) {
		if (event.getMessageContent().contains("!begin hangman ")) {
			answer_string = event.getMessageContent().replace("!begin hangman ", "");
			event.deleteMessage();
			current_guess = generateUnknown(answer_string);
			GAME_RUNNING = true;
			event.getChannel().sendMessage("A hangman game has started! The format is " + current_guess + ". Begin guessing now!");
		}
		
		if (event.getMessageContent().contains("!guess ") && GAME_RUNNING) {
			String guess = event.getMessageContent().replace("!begin hangman ", "");
			if(guess.length() != 1) {event.deleteMessage();}
			else
			{
				boolean was_correct = false;
				char c_guess = guess.charAt(0);
				
				for(int i = 0; i < answer_string.length(); i++)
				{
					if(answer_string.charAt(i) == c_guess) {
						current_guess = current_guess.substring(0, i) + c_guess + current_guess.substring(i + 1, answer_string.length());
						was_correct = true;
					}
				}
				
				if(was_correct) {
					event.getChannel().sendMessage("The guess of " + c_guess + " was correct! The word is now " + current_guess + ".");
					checkGameState(event);
				}
				
				else if(!wrong_guesses.contains(c_guess))
				{
					num_incorrect++;
					event.getChannel().sendMessage("The guess of " + c_guess + " was incorrect!" + num_incorrect + " guesses remain.");
					checkGameState(event);
				}
				else
				{
					event.deleteMessage();
				}
			}
		}
		
		if (event.getMessageContent().contains("!see guesses") && GAME_RUNNING) {
			event.deleteMessage();
			event.getChannel().sendMessage("The current incorrect guesses are " + wrong_guesses);
		}
		
		if (event.getMessageContent().contains("!see word") && GAME_RUNNING) {
			event.deleteMessage();
			event.getChannel().sendMessage("The currently known part of the word is " + current_guess);
		}
	}
	
	public void checkGameState(MessageCreateEvent event)
	{
		if(answer_string.equals(current_guess)) {
			event.getChannel().sendMessage("The word has been guessed! It was " + answer_string);
			event.deleteMessage();
			GAME_RUNNING = false;
			}
		
		else if (num_incorrect == NUM_MAX) {
			event.getChannel().sendMessage("The game is lost! The word was " + answer_string);
			event.deleteMessage();
			GAME_RUNNING = false;
		}
	}
	
	public String generateUnknown(String s)
	{
		String ret = "";
		for(char c: s.toCharArray())
		{
			if(c == ' ') {ret = ret + " ";}
			else {ret = ret + "_";}
		}
		
		return ret;
	}
}