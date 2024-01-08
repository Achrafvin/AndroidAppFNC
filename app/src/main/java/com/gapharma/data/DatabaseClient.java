package com.gapharma.data;

import android.content.Context;
import androidx.room.Room;

public class DatabaseClient {
    private static DatabaseClient instance;
    private AppDatabase appDatabase;

    private DatabaseClient(Context context) {

            appDatabase = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                    .build();
          }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
