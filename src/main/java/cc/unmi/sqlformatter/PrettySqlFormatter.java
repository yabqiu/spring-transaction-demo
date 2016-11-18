package cc.unmi.sqlformatter;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class PrettySqlFormatter implements MessageFormattingStrategy {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";

    private String outputFormat = ANSI_BLUE + "\nconnectionId: %s | take %s milliseconds|category: %s\n";

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
        StringBuilder sb = new StringBuilder(String.format(outputFormat, connectionId, elapsed, category));

        if (!prepared.trim().isEmpty()) {
            sb.append("statement:")
                    .append(ANSI_PURPLE)
                    .append(format(prepared));
        }

        if (!sql.trim().isEmpty()) {
            sb.append(ANSI_BLUE)
                    .append("\neffective SQL:")
                    .append(ANSI_RED)
                    .append(format(sql));
        }

        return sb.append(ANSI_RESET)
                .append("\n").toString();
    }

    private String format(String sql) {
        return (sql.trim().toLowerCase().matches("(create|alter|drop|comment|truncate|rename).*")) ?
                FormatStyle.DDL.getFormatter().format(sql) :
                FormatStyle.BASIC.getFormatter().format(sql);
    }
}

