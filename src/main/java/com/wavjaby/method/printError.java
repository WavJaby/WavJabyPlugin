package com.wavjaby.method;

import com.wavjaby.main;
import org.bukkit.Bukkit;

import java.util.logging.Level;

public class printError {

    public static void printError(String errorMessage) {
        Bukkit.getLogger().log(Level.SEVERE , errorMessage);
    }
}
