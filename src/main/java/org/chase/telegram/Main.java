package org.chase.telegram;

import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String... args) throws TelegramApiException {
        String botToken = getEnv("BOT_TOKEN");
        String botUsername = getEnv("BOT_USERNAME");
        String creatorChat = getEnv("CREATOR_CHAT");

        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new ResponseBot(creatorChat, botToken, botUsername));
    }

    private static String getEnv(String key) {
        final String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            LoggerFactory.getLogger(Main.class).error("Couldn't get value for environment variable {}", key);
        }
        return value;
    }
}
