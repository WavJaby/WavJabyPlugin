package com.wavjaby;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public class Color {
    // ANSI escape code
    static final String RESET = "\u001B[0m";

    static final String BLACK = "\u001B[30m";
    static final String DARK_BLUE = "\u001B[34m";
    static final String DARK_GREEN = "\u001B[32m";
    static final String DARK_AQUA = "\u001B[36m";
    static final String DARK_RED = "\u001B[31m";
    static final String PURPLE = "\u001B[35m";
    static final String GOLD = "\u001B[33m";
    static final String GRAY = "\u001B[37m";
    static final String DARK_GRAY = "\u001B[90m";
    static final String BLUE = "\u001B[94m";
    static final String GREEN = "\u001B[92m";
    static final String AQUA = "\u001B[96m";
    static final String RED = "\u001B[91m";
    static final String PINK = "\u001B[95m";
    static final String YELLOW = "\u001B[93m";
    static final String WHITE = "\u001B[97m";

    final static String[] color = {
            BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, PURPLE, GOLD, GRAY, DARK_GRAY, BLUE,
            GREEN, AQUA, RED, PINK, YELLOW, WHITE};

    public static void printError(String errorMessage) {
        errorMessage.replace("&r", RESET);
        errorMessage.replace("§r", RESET);
        for (int i = 0; i < 16; i++) {
            String mcColorCode = Integer.toHexString(i);
            errorMessage.replace("&" + mcColorCode, color[i]);
            errorMessage.replace("§" + mcColorCode, color[i]);
        }
        Bukkit.getLogger().log(Level.SEVERE, errorMessage);
    }
}