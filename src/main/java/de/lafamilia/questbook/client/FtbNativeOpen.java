package de.lafamilia.questbook.client;

public final class FtbNativeOpen {
    private static boolean allowOnce;
    private FtbNativeOpen() {}
    public static void requestNative() { allowOnce = true; }
    public static boolean consumeNativeRequest() {
        boolean value = allowOnce; allowOnce = false; return value;
    }
}
