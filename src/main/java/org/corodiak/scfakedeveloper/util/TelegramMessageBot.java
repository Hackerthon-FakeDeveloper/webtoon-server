package org.corodiak.scfakedeveloper.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramMessageBot extends TelegramLongPollingBot {
	@Value("${bot.BOT_TOKEN}")
	private String botToken;

	@Value("${bot.BOT_USERNAME}")
	private String botUsername;

	@Value("${bot.CHAT_ID}")
	private String chatId;

	@Override
	public String getBotUsername() {
		return botUsername;
	}

	@Override
	public String getBotToken() {
		return botToken;
	}

	@Override
	public void onUpdateReceived(Update update) {

	}

	public void sendMessage(String message) throws TelegramApiException {
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(chatId);
		sendMessage.setText(message);

		execute(sendMessage);
	}
}
