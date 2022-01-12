package com.example.basic;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.basic.QuizContract.*;


import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION +  " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable () {
        Question q1 = new Question("Do you consume meat often?", "Yes","No","C","D",2 );
        addQuestion(q1);
        Question q2 = new Question("How Long do you shower for?", "0-10 min","10 min+","C","D",1 );
        addQuestion(q2);
        Question q3 = new Question("Do you compost?", "Yes","No","C","D",1 );
        addQuestion(q3);
        Question q4 = new Question("Do you often travel by bus?", "Yes","No","C","D",1 );
        addQuestion(q4);
        Question q5 = new Question("Do you grow your own food?", "Yes","No","C","D",1 );
        addQuestion(q5);
        Question q6 = new Question("Do you eat out a lot?", "Yes","No","C","D",2 );
        addQuestion(q6);
        Question q7 = new Question("Do you purchase locally grown food?", "Yes","No","C","D",1 );
        addQuestion(q7);
        Question q8 = new Question("Do you leave the lights on when not in a room?", "Yes","No","C","D",2 );
        addQuestion(q8);
        Question q9 = new Question("Do you often travel by car?", "Yes","No","C","D",2 );
        addQuestion(q9);
        Question q10 = new Question("Do you often travel by walking/running?", "Yes","No","C","D",1 );
        addQuestion(q10);
        Question q11 = new Question("Do you often travel by biking?", "Yes","No","C","D",1 );
        addQuestion(q11);
        Question q12 = new Question("How often do you purchase a new phone?", "Once a Year or less","After at least a Year","C","D",2 );
        addQuestion(q12);
        Question q13 = new Question("Do you recycle often?", "Yes","No","C","D",1 );
        addQuestion(q13);
        Question q14 = new Question("Do you consume animal products often?", "Yes","No","C","D",2 );
        addQuestion(q14);
        Question q15 = new Question("Do you carpool often?", "Yes","No","C","D",1 );
        addQuestion(q15);
        Question q16 = new Question("Do you purchase coffee in disposable cups often?", "Yes","No","C","D",2 );
        addQuestion(q16);

    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }
    @SuppressLint("Range")
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME,null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;

    }
}
