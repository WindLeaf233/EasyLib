package ml.windleaf.api.logging;

import ml.windleaf.api.logging.format.NameFormat;
import ml.windleaf.api.logging.format.Separator;
import org.bukkit.Bukkit;

import java.util.Arrays;

public class PluginLogger {
    private final String name;
    private final NameFormat nameFormat;
    private final Separator separator;

    public PluginLogger(String name) {
        this.name = name;
        this.nameFormat = NameFormat.SQUARE_BRACKETS;
        this.separator = Separator.EMPTY;
    }

    public PluginLogger(String name, NameFormat nameFormat, Separator separator) {
        this.name = name;
        this.nameFormat = nameFormat;
        this.separator = separator;
    }

    public void logConsole(Object... any) {
        StringBuilder sb = new StringBuilder("");
        Arrays.stream(any).map(Object::toString).map(str -> sb.append(" ").append(str).append(" "));
        Bukkit.getConsoleSender().sendMessage(sb.toString());
    }
}
