package com.gapharma;

import android.content.Context;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.gapharma.data.AppDatabase;
import com.gapharma.data.dao.UserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
@Config(manifest = Config.NONE)
public class UserDaoTest {
    private UserDao userDao;
    private AppDatabase db;
    private ExecutorService executorService;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        userDao = db.userDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(500, TimeUnit.MILLISECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }
        }
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
//        User user = new User("Test User","user@example.com","pharm@example.com","password","123456789",1L);
//
//        executorService.execute(() -> userDao.save(user));
//
//        // Adding some delay to ensure the database operation completes
//        Thread.sleep(500);
//
//        User[] retrievedUsers = new User[1];
//        executorService.execute(() -> retrievedUsers[0] = userDao.findByName("Test User"));
//
//        // Adding some delay to ensure the database operation completes
//        Thread.sleep(500);
//
//        assertNotNull(retrievedUsers[0]);
//        assertEquals(user.getName(), retrievedUsers[0].getName());
    }
}
