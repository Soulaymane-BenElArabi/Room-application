package ma.ensa.room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.util.Date;
import java.util.List;

import ma.ensa.room.dao.IDao;
import ma.ensa.room.database.StudentDataBase;
import ma.ensa.room.entity.Student;

public class MainActivity<FetchAsyncTask> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StudentDataBase studentDataBase = StudentDataBase.getInstance(getApplicationContext());

        IDao dao = studentDataBase.studentIDao();


        new InsertAsyncTask(dao).execute(new Student("","", new Date()));
        new MainActivity.FetchAsyncTask(dao).execute();
        new MainActivity.FetchAsycTaskById(dao).execute(4);


    }

    private class FetchAsyncTask extends AsyncTask <Object, Object, List<Student>>{
        IDao studentIDao;
        public FetchAsyncTask(IDao studentIDao) {
            this.studentIDao = studentIDao;
        }
        @Override
        protected List<Student> doInBackground(Object... objects) {
            return studentIDao.findAll();
        }
        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(List<Student> students) {
            for(Student s :students){
                Log.d("fetch ", s.toString());
            }
        }
    }
    private class InsertAsyncTask extends AsyncTask<Student, Void, Void>{
        IDao studentIDao;
        public InsertAsyncTask(IDao studentIDao) {
            this.studentIDao = studentIDao;
        }
        @Override
        protected Void doInBackground(Student... students) {
            studentIDao.create(students[0]);
            return null;
        }
    }

    private class FetchAsycTaskById extends AsyncTask<Object, Object, Student>{
        IDao studentIDao;

        public FetchAsycTaskById(IDao studentIDao) {
            this.studentIDao = studentIDao;
        }

        @Override
        protected Student doInBackground(Object... integers) {
            int id = (int) integers[0];
            return studentIDao.findById(id);
        }

        @Override
        protected void onPostExecute(Student student) {
            Log.d("Id", student.toString());
        }
    }
}