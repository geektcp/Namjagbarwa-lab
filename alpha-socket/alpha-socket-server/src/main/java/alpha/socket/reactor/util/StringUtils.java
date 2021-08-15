package alpha.socket.reactor.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class StringUtils {

    private static final DecimalFormat decimalFormat;

    static {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.ENGLISH);
        decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.applyPattern("#.##");
    }

    public static String stringifyException(Throwable e) {
        StringWriter stm = new StringWriter();
        PrintWriter wrt = new PrintWriter(stm);
        e.printStackTrace(wrt);
        wrt.close();

        return stm.toString();
    }

    public static String simpleHostname(String fullHostname) {
        int offset = fullHostname.indexOf('.');
        if (offset != -1) {
            return fullHostname.substring(0, offset);
        }

        return fullHostname;
    }

    private static DecimalFormat oneDecimal = new DecimalFormat("0.0");

    public static String humanReadableInt(long number) {
        long absNumber = Math.abs(number);
        double result = number;
        String suffix = "";

        if (absNumber < 1024) {

        } else if (absNumber < 1024 * 1024) {
            result = number / 1024.0;
            suffix = "k";
        } else if (absNumber < 1024 * 1024 * 1024) {
            result = number / (1024.0 * 1024);
            suffix = "m";
        } else {
            result = number / (1024.0 * 1024 * 1024);
            suffix = "g";
        }

        return oneDecimal.format(result) + suffix;
    }

    public static String formatPercent(double done, int digits) {
        DecimalFormat percentFormat = new DecimalFormat("0.00%");
        double scale = Math.pow(10.0, digits + 2);
        double rounded = Math.floor(done * scale);

        percentFormat.setDecimalSeparatorAlwaysShown(false);
        percentFormat.setMinimumFractionDigits(digits);
        percentFormat.setMaximumFractionDigits(digits);

        return percentFormat.format(rounded / scale);
    }

    public static String arrayToString(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        StringBuffer sbuf = new StringBuffer();
        sbuf.append(strs[0]);
        for (int idx = 1; idx < strs.length; idx++) {
            sbuf.append(",");
            sbuf.append(strs[idx]);
        }

        return sbuf.toString();
    }

    public static String byteToHexString(byte[] bytes, int start, int end) {
        if (bytes == null) {
            throw new IllegalArgumentException("bytes == null");
        }

        StringBuilder s = new StringBuilder();
        for (int i = start; i < end; i++) {
            s.append(String.format("%02x", bytes[i]));
        }

        return s.toString();
    }

    public static String byteToHexString(byte bytes[]) {
        return byteToHexString(bytes, 0, bytes.length);
    }

    public static byte[] hexStringToByte(String hex) {
        byte[] bts = new byte[hex.length() / 2];
        for (int i = 0; i < bts.length; i++) {
            bts[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }

        return bts;
    }

    public static String uriToString(URI[] uris) {
        if (uris == null) {
            return null;
        }

        StringBuffer ret = new StringBuffer(uris[0].toString());
        for (int i = 1; i < uris.length; i++) {
            ret.append(",");
            ret.append(uris[i].toString());
        }

        return ret.toString();
    }

    public static URI[] stringToURI(String[] str) {
        if (str == null) {
            return null;
        }

        URI[] uris = new URI[str.length];
        for (int i = 0; i < str.length; i++) {
            try {
                uris[i] = new URI(str[i]);
            } catch (URISyntaxException ur) {
                uris[i] = null;
            }
        }

        return uris;
    }

    public static String formatTimeDiff(long finishTime, long startTime) {
        long timeDiff = finishTime - startTime;

        return formatTime(timeDiff);
    }

    public static String formatTime(long timeDiff) {
        StringBuffer buf = new StringBuffer();
        long hours = timeDiff / (60 * 60 * 1000);
        long rem = (timeDiff % (60 * 60 * 1000));
        long minutes = rem / (60 * 1000);
        rem = rem % (60 * 1000);
        long seconds = rem / 1000;

        if (hours != 0) {
            buf.append(hours);
            buf.append("hrs, ");
        }

        if (minutes != 0) {
            buf.append(minutes);
            buf.append("mins, ");
        }

        buf.append(seconds);
        buf.append("sec");

        return buf.toString();
    }

    public static String getFormattedTimeWithDiff(DateFormat dateFormat,
                                                  long finishTime, long startTime) {
        StringBuffer buf = new StringBuffer();
        if (0 != finishTime) {
            buf.append(dateFormat.format(new Date(finishTime)));
            if (0 != startTime) {
                buf.append(" (" + formatTimeDiff(finishTime, startTime) + ")");
            }
        }

        return buf.toString();
    }

    public static String[] getStrings(String str) {
        Collection<String> values = getStringCollection(str);
        if (values.size() == 0) {
            return null;
        }

        return values.toArray(new String[values.size()]);
    }

    public static Collection<String> getStringCollection(String str) {
        List<String> values = new ArrayList<String>();
        if (str == null) {
            return values;
        }

        StringTokenizer tokenizer = new StringTokenizer(str, ",");
        values = new ArrayList<String>();
        while (tokenizer.hasMoreTokens()) {
            values.add(tokenizer.nextToken());
        }

        return values;
    }

    final public static char COMMA = ',';
    final public static String COMMA_STR = ",";
    final public static char ESCAPE_CHAR = '\\';

    public static String[] split(String str) {
        return split(str, ESCAPE_CHAR, COMMA);
    }

    public static String[] split(String str, char separator) {
        if ("".equals(str)) {
            return new String[]{""};
        }

        ArrayList<String> strList = new ArrayList<String>();
        int startIndex = 0;
        int nextIndex = 0;
        while ((nextIndex = str.indexOf((int) separator, startIndex)) != -1) {
            strList.add(str.substring(startIndex, nextIndex));
            startIndex = nextIndex + 1;
        }
        strList.add(str.substring(startIndex));
        int last = strList.size(); // last split
        while (--last >= 0 && "".equals(strList.get(last))) {
            strList.remove(last);
        }

        return strList.toArray(new String[strList.size()]);
    }

    public static String[] split(String str, char escapeChar, char separator) {
        if (str == null) {
            return null;
        }

        ArrayList<String> strList = new ArrayList<String>();
        StringBuilder split = new StringBuilder();
        int index = 0;
        while ((index = findNext(str, separator, escapeChar, index, split)) >= 0) {
            ++index;
            strList.add(split.toString());
            split.setLength(0);
        }
        strList.add(split.toString());
        int last = strList.size();
        while (--last >= 0 && "".equals(strList.get(last))) {
            strList.remove(last);
        }

        return strList.toArray(new String[strList.size()]);
    }

    public static int findNext(String str, char separator, char escapeChar,
                               int start, StringBuilder split) {
        int numPreEscapes = 0;
        for (int i = start; i < str.length(); i++) {
            char curChar = str.charAt(i);
            if (numPreEscapes == 0 && curChar == separator) {
                return i;
            } else {
                split.append(curChar);
                numPreEscapes = (curChar == escapeChar)
                        ? (++numPreEscapes) % 2
                        : 0;
            }
        }

        return -1;
    }

    public static String escapeString(String str) {
        return escapeString(str, ESCAPE_CHAR, COMMA);
    }

    public static String escapeString(String str, char escapeChar,
                                      char charToEscape) {
        return escapeString(str, escapeChar, new char[]{charToEscape});
    }

    private static boolean hasChar(char[] chars, char character) {
        for (char target : chars) {
            if (character == target) {
                return true;
            }
        }

        return false;
    }

    public static String escapeString(String str, char escapeChar,
                                      char[] charsToEscape) {
        if (str == null) {
            return null;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char curChar = str.charAt(i);
            if (curChar == escapeChar || hasChar(charsToEscape, curChar)) {
                result.append(escapeChar);
            }
            result.append(curChar);
        }

        return result.toString();
    }

    public static String unEscapeString(String str) {
        return unEscapeString(str, ESCAPE_CHAR, COMMA);
    }

    public static String unEscapeString(String str, char escapeChar,
                                        char charToEscape) {
        return unEscapeString(str, escapeChar, new char[]{charToEscape});
    }

    public static String unEscapeString(String str, char escapeChar,
                                        char[] charsToEscape) {
        if (str == null) {
            return null;
        }

        StringBuilder result = new StringBuilder(str.length());
        boolean hasPreEscape = false;
        for (int i = 0; i < str.length(); i++) {
            char curChar = str.charAt(i);
            if (hasPreEscape) {
                if (curChar != escapeChar && !hasChar(charsToEscape, curChar)) {
                    throw new IllegalArgumentException("Illegal escaped string " + str +
                            " unescaped " + escapeChar + " at " + (i - 1));
                }
                result.append(curChar);
                hasPreEscape = false;
            } else {
                if (hasChar(charsToEscape, curChar)) {
                    throw new IllegalArgumentException("Illegal escaped string " + str +
                            " unescaped " + curChar + " at " + i);
                } else if (curChar == escapeChar) {
                    hasPreEscape = true;
                } else {
                    result.append(curChar);
                }
            }
        }

        if (hasPreEscape) {
            throw new IllegalArgumentException("Illegal escaped string " + str +
                    ", not expecting " + escapeChar + " in the end.");
        }

        return result.toString();
    }

    public static String getHostname() {
        try {
            return "" + InetAddress.getLocalHost();
        } catch (UnknownHostException uhe) {
            return "" + uhe;
        }
    }

    public static enum TraditionalBinaryPrefix {
        KILO(1024),
        MEGA(KILO.value << 10),
        GIGA(MEGA.value << 10),
        TERA(GIGA.value << 10),
        PETA(TERA.value << 10),
        EXA(PETA.value << 10);

        public final long value;
        public final char symbol;

        TraditionalBinaryPrefix(long value) {
            this.value = value;
            this.symbol = toString().charAt(0);
        }

        public static TraditionalBinaryPrefix valueOf(char symbol) {
            symbol = Character.toUpperCase(symbol);
            for (TraditionalBinaryPrefix prefix : TraditionalBinaryPrefix.values()) {
                if (symbol == prefix.symbol) {
                    return prefix;
                }
            }

            throw new IllegalArgumentException("Unknown symbol '" + symbol + "'");
        }

        public static long string2long(String s) {
            s = s.trim();
            final int lastpos = s.length() - 1;
            final char lastchar = s.charAt(lastpos);
            if (Character.isDigit(lastchar)) {
                return Long.parseLong(s);
            } else {
                long prefix = TraditionalBinaryPrefix.valueOf(lastchar).value;
                long num = Long.parseLong(s.substring(0, lastpos));
                if (num > (Long.MAX_VALUE / prefix) || num < (Long.MIN_VALUE / prefix)) {
                    throw new IllegalArgumentException(s + " does not fit in a Long");
                }

                return num * prefix;
            }
        }
    }

    public static String escapeHTML(String string) {
        if (string == null) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        boolean lastCharacterWasSpace = false;
        char[] chars = string.toCharArray();
        for (char c : chars) {
            if (c == ' ') {
                if (lastCharacterWasSpace) {
                    lastCharacterWasSpace = false;
                    sb.append("&nbsp;");
                } else {
                    lastCharacterWasSpace = true;
                    sb.append(" ");
                }
            } else {
                lastCharacterWasSpace = false;
                switch (c) {
                    case '<':
                        sb.append("&lt;");
                        break;
                    case '>':
                        sb.append("&gt;");
                        break;
                    case '&':
                        sb.append("&amp;");
                        break;
                    case '"':
                        sb.append("&quot;");
                        break;
                    default:
                        sb.append(c);
                        break;
                }
            }
        }

        return sb.toString();
    }

    public static String byteDesc(long len) {
        double val = 0.0;
        String ending = "";
        if (len < 1024 * 1024) {
            val = (1.0 * len) / 1024;
            ending = " KB";
        } else if (len < 1024 * 1024 * 1024) {
            val = (1.0 * len) / (1024 * 1024);
            ending = " MB";
        } else if (len < 1024L * 1024 * 1024 * 1024) {
            val = (1.0 * len) / (1024 * 1024 * 1024);
            ending = " GB";
        } else if (len < 1024L * 1024 * 1024 * 1024 * 1024) {
            val = (1.0 * len) / (1024L * 1024 * 1024 * 1024);
            ending = " TB";
        } else {
            val = (1.0 * len) / (1024L * 1024 * 1024 * 1024 * 1024);
            ending = " PB";
        }

        return limitDecimalTo2(val) + ending;
    }

    public static synchronized String limitDecimalTo2(double d) {
        return decimalFormat.format(d);
    }

    public static String stackTraceOfThread(Thread t) {
        StackTraceElement[] stackTraceElements = Thread.getAllStackTraces().get(t);
        String stackTrace = "";
        if (stackTraceElements != null) {
            for (StackTraceElement stackTraceElement : stackTraceElements) {
                stackTrace += stackTraceElement + "\n";
            }
        }

        return stackTrace;
    }
}
