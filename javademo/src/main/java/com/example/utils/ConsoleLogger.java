package com.example.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

@SuppressWarnings("WeakerAccess")
public class ConsoleLogger {
  public static final String TRANSPARENT = "";
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";
  public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
  public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
  public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
  public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
  public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
  public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
  public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
  public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
  public static final String FILTER_ANY = ".*";
  public static final String FILTER_NONE = "";
  private String[] foregroundColors = new String[]{TRANSPARENT, ANSI_BLACK, ANSI_RED, ANSI_GREEN, ANSI_YELLOW, ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN, ANSI_WHITE};
  private String[] backgroundColors = new String[]{TRANSPARENT, ANSI_BLACK_BACKGROUND, ANSI_RED_BACKGROUND, ANSI_GREEN_BACKGROUND, ANSI_YELLOW_BACKGROUND, ANSI_BLUE_BACKGROUND, ANSI_PURPLE_BACKGROUND, ANSI_CYAN_BACKGROUND, ANSI_WHITE_BACKGROUND};
  private Pattern include;
  private Pattern exclude;
  private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault());

  public ConsoleLogger() {
    this(FILTER_ANY, FILTER_NONE);
  }

  public ConsoleLogger(String include, String exclude) {
    this.include = Pattern.compile(include);
    this.exclude = Pattern.compile(exclude);
  }

  public void write(Colors fgColor, Colors bgColor, String tag, String msg) {
    write(fgColor, bgColor, "", tag, msg);
  }

  public void write(Colors fgColor, String tag, String msg) {
    write(fgColor, "", tag, msg);
  }

  public void write(Colors fgColor, String prefix, String tag, String msg) {
    write(fgColor, Colors.Transparent, prefix, tag, msg);
  }

  public void write(Colors fgColor, Colors bgColor, String prefix, String tag, String msg) {
    write(
        backgroundColors[bgColor.getValue()] + foregroundColors[fgColor.getValue()] + getTime(),
        formatThread(),
        prefix,
        " [" + tag + "] ",
        msg + ANSI_RESET
    );
  }

  public void d(String tag, String msg) {
    if (isIncluded(tag) && !isExcluded(tag)) {
      write(Colors.Blue, " D", tag, msg);
    }
  }

  public void d(String tag, String msg, Exception e) {
    if (isIncluded(tag) && !isExcluded(tag)) {
      write(Colors.Blue, " D", tag, msg + ", Exception: " + e);
    }
  }

  public void e(String tag, String msg, Exception e) {
    if (isIncluded(tag) && !isExcluded(tag)) {
      write(Colors.Red, " E", tag, msg + ", Exception: " + e);
    }
  }

  protected synchronized void write(String... fields) {
    StringBuilder sb = new StringBuilder();
    for (String f : fields) sb.append(f);
    System.out.println(sb);
  }

  private String formatThread() {
    return " [" + Thread.currentThread().getId() + ":" + Thread.currentThread().getName() + "] ";
  }

  private String getTime() {
    Date date = new Date();
    return formatter.format(date);
  }

  private boolean isExcluded(String tag) {
    return exclude != null && exclude.matcher(tag).matches();
  }

  private boolean isIncluded(String tag) {
    return include == null || include.matcher(tag).matches();
  }

  @SuppressWarnings("unused")
  public enum Colors {
    Transparent(0),
    Black(1),
    Red(2),
    Green(3),
    Yellow(4),
    Blue(5),
    Purpule(6),
    Cyan(7),
    White(8);

    private final int value;

    Colors(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }
}
