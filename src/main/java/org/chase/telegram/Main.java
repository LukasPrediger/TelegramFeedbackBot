package org.chase.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) throws TelegramApiException {
        try {
            String botToken = getEnv("BOT_TOKEN");
            String botUsername = getEnv("BOT_USERNAME");
            String creatorChat = getEnv("CREATOR_CHAT");

            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(new ResponseBot(creatorChat, botToken, botUsername));
        } catch (MissingValueException e) {
            LOGGER.error("Error getting Environments", e);
        }
    }

    private static String getEnv(String key) throws MissingValueException {
        final String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            throw new MissingValueException(key);
        }
        return value;
    }
}
