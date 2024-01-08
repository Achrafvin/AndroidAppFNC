package com.gapharma.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.gapharma.data.dao.AttachementDao;
import com.gapharma.data.dao.IssueDao;
import com.gapharma.data.dao.UserDao;
import com.gapharma.data.entities.Attachement;
import com.gapharma.data.entities.Issue;
import com.gapharma.data.entities.User;
import com.gapharma.utils.Converters;

@Database(entities = {User.class, Issue.class, Attachement.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract IssueDao issueDao();
    public abstract AttachementDao attachementDao();
}
