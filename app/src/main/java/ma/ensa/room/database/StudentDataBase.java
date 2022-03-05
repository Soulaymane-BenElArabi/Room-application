package ma.ensa.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import ma.ensa.room.dao.IDao;
import ma.ensa.room.entity.Student;

@Database(entities = {Student.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class StudentDataBase extends RoomDatabase {
    private static volatile StudentDataBase instance;

    public abstract IDao studentIDao();

    public static StudentDataBase getInstance(final Context context) {
        if (instance == null) {
            synchronized (StudentDataBase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), StudentDataBase.class, "school").build();
                }
            }
        }
        return instance;
    }
}
