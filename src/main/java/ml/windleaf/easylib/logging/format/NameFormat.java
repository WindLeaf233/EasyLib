package ml.windleaf.easylib.logging.format;

import ml.windleaf.easylib.utils.ChatColorUtils;
import org.bukkit.ChatColor;

public enum NameFormat {
    EMPTY("%s"),
    SQUARE_BRACKETS("[%s]"),
    ;

    private final String content;
    private final Boolean bold;

    NameFormat(String content) {
        this.content = content;
        this.bold = false;
    }

    NameFormat(String content, Boolean bold) {
        this.content = content;
        this.bold = bold;
    }

    public String getContent() {
        return this.bold
                ? ChatColorUtils.getTextColored(this.content, ChatColor.BOLD)
                : this.content;
    }
}
