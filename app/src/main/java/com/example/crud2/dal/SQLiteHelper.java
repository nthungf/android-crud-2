package com.example.crud2.dal;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.crud2.model.Book;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Sach.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE books("
                + "id integer primary key autoincrement,"
                + "ten text,"
                + "tacgia text,"
                + "phamvi text,"
                + "doituong text,"
                + "danhgia int"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Book> getAll() {
        System.out.println("get all");
        List<Book> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
//        String order = "date DESC";
        Cursor rs = st.query("books", null, null, null, null, null, null);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String tacGia = rs.getString(2);
            String phamVi = rs.getString(3);
            String doiTuong = rs.getString(4);
            int danhGia = rs.getInt(5);
            list.add(new Book(id, ten, tacGia, phamVi, doiTuong, danhGia));
        }
        System.out.println("get all completed");
        return list;
    }

    //add item
    public void addBook(Book i) {
        String sql = "insert into books (ten, tacgia, phamvi, doituong, danhgia)" + "values (?,?,?,?,?)";
        String[] args = {i.getTen(), i.getTacGia(), i.getPhamVi(), i.getDoiTuong(), Integer.toString(i.getDanhGia())};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql, args);
    }

    // update item
    public int update(Book i) {
        ContentValues values = new ContentValues();
        values.put("ten", i.getTen());
        values.put("tacgia", i.getTacGia());
        values.put("phamvi", i.getPhamVi());
        values.put("doituong", i.getDoiTuong());
        values.put("danhgia", i.getDanhGia());

        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(i.getId())};

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update("books", values, whereClause, whereArgs);
    }

    // delete item
    public int delete(int id) {
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("books", whereClause, whereArgs);
    }

    public List<Book> searchByKey(String key) {
        System.out.println("Search");
        List<Book> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "danhgia DESC";
        String selection = "ten like ? or tacgia like ?";
        String tmp = "%" + key + "%";
        String[] selectionArgs = {tmp, tmp};
        Cursor rs = st.query("books", null, selection, selectionArgs, null, null, order);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String tacGia = rs.getString(2);
            String phamVi = rs.getString(3);
            String doiTuong = rs.getString(4);
            int danhGia = rs.getInt(5);
            list.add(new Book(id, ten, tacGia, phamVi, doiTuong, danhGia));
        }
        System.out.println("Search completed");
        return list;
    }

    public List<Book> getStat() {
        System.out.println("get stat");
        List<Book> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String sql = "select id, ten, tacgia, phamvi, doituong, max(danhgia) from books group by tacgia";
        Cursor rs = st.rawQuery(sql, null);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String tacGia = rs.getString(2);
            String phamVi = rs.getString(3);
            String doiTuong = rs.getString(4);
            int danhGia = rs.getInt(5);
            list.add(new Book(id, ten, tacGia, phamVi, doiTuong, danhGia));
        }
        System.out.println("get stat completed");
        return list;
    }
}
