package com.ambapharm.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.ambapharm.data.dao.AttachementDao;
import com.ambapharm.data.dao.IssueDao;
import com.ambapharm.data.dao.UserDao;
import com.ambapharm.data.entities.Attachement;
import com.ambapharm.data.entities.Issue;
import com.ambapharm.data.entities.User;
import com.ambapharm.helpers.Converters;

@Database(entities = {User.class, Issue.class, Attachement.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract IssueDao issueDao();
    public abstract AttachementDao attachementDao();
}
