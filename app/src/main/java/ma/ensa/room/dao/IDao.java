package ma.ensa.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ma.ensa.room.entity.Student;

@Dao
public interface IDao{
    @Insert
    void create(Student o);

    @Query("select * from students")
    List<Student> findAll();

    @Query("select * from students where id=:id")
    Student findById(int id);

}
