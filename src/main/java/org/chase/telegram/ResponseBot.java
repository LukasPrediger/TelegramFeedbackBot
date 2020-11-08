package org.chase.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class ResponseBot extends TelegramLongPollingBot {
    private final String creatorChat;
    private final String botToken;
    private final String botUsername;

    public ResponseBot(String creatorChat, String botToken, String botUsername) {
        this.creatorChat = creatorChat;
        this.botToken = botToken;
        this.botUsername = botUsername;
    }
    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                execute(SendMessage
                        .builder()
                        .chatId(update.getMessage().getChatId().toString())
                        .text(update.getMessage().getText())
                        .build()
                );
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
