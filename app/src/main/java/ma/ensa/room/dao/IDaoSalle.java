package ma.ensa.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ma.ensa.room.entity.Salle;
import ma.ensa.room.entity.Student;

@Dao
public interface IDaoSalle {
    @Insert
    void create(Salle o);

    @Query("select * from salles")
    List<Salle> findAll();

    @Query("select * from salles where id=:id")
    Salle findById(int id);
}
