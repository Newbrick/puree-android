package com.cookpad.android.loghouse;

import com.google.gson.Gson;

public class LogHouseManager {
    private static final String TAG = LogHouseManager.class.getSimpleName();
    private static final Gson GSON = new Gson();
    private static LogHouseDbHelper logHouseStorage;

    public static void initialize(LogHouseConfiguration conf) {
        logHouseStorage = new LogHouseDbHelper(conf.getApplicationContext());
    }

    public static void ask(Log log) {
        String serializedLog = GSON.toJson(log);
        logHouseStorage.insert(serializedLog);
        flush();
    }

    public static void flush() {
        logHouseStorage.select();
        logHouseStorage.delete();
    }
}
