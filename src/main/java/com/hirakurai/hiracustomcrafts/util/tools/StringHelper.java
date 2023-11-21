package com.hirakurai.hiracustomcrafts.util.tools;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {
    private static final Pattern HEX_PATTERN = Pattern.compile("&(#[a-f0-9]{6})", 2);

    public static String color(String input) {
        Matcher m = HEX_PATTERN.matcher(input);
        while (m.find()) {
            input = input.replace(m.group(), ChatColor.of(m.group(1)).toString());
        }
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
