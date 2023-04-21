package com.example.crud2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crud2.dal.SQLiteHelper;
import com.example.crud2.model.Book;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText eTen;
    private Spinner spinner;
    private RadioGroup radioGroup;
    private CheckBox cbCNTT, cbVT, cbDT;
    private RatingBar ratingBar;
    private Button btUpdate, btCancel, btDelete;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initViews();

        Intent intent = getIntent();
        book = (Book) intent.getSerializableExtra("book");
        eTen.setText(book.getTen());
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(book.getTacGia())) {
                spinner.setSelection(i);
                break;
            }
        }
        if (book.getPhamVi().equalsIgnoreCase("hoc")) {
            radioGroup.check(R.id.rbHoc);
        } else {
            radioGroup.check(R.id.rbTraCuu);
        }
        if (book.getDoiTuong().contains("CNTT")) cbCNTT.setChecked(true);
        if (book.getDoiTuong().contains("VT")) cbVT.setChecked(true);
        if (book.getDoiTuong().contains("DT")) cbDT.setChecked(true);
        ratingBar.setRating(book.getDanhGia());
    }

    private void initViews() {
        eTen = findViewById(R.id.edTen);
        spinner = findViewById(R.id.spinner);
        radioGroup = findViewById(R.id.radioGroup);
        cbCNTT = findViewById(R.id.cbCNTT);
        cbVT = findViewById(R.id.cbVT);
        cbDT = findViewById(R.id.cbDT);
        ratingBar = findViewById(R.id.rate);
        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);
        btDelete = findViewById(R.id.btDelete);
        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        btDelete.setOnClickListener(this);

        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.tacgia)));
    }

    @Override
    public void onClick(View v) {
        if (v == btCancel) {
            finish();
        } else if (v == btDelete) {
            SQLiteHelper db = new SQLiteHelper(this);
            db.delete(book.getId());
            finish();
        } else if (v == btUpdate) {
            String ten = eTen.getText().toString();
            String tacGia = spinner.getSelectedItem().toString();
            String phamVi = radioGroup.getCheckedRadioButtonId() == R.id.rbHoc ? "Hoc" : "Tra cuu";
            String doiTuong = "";
            if (cbCNTT.isChecked()) doiTuong += "CNTT,";
            if (cbVT.isChecked()) doiTuong += "VT,";
            if (cbDT.isChecked()) doiTuong += "DT,";
            int rating = (int) ratingBar.getRating();
            if (!ten.isEmpty()) {
                int id = book.getId();
                Book b = new Book(id, ten, tacGia, phamVi, doiTuong, rating);
                SQLiteHelper db = new SQLiteHelper(this);
                db.update(b);
                finish();
            }
        }
    }
}