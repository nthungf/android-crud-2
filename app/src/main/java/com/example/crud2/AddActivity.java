package com.example.crud2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.crud2.dal.SQLiteHelper;
import com.example.crud2.model.Book;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText eTen;
    private Spinner spinner;
    private RadioGroup radioGroup;
    private CheckBox cbCNTT, cbVT, cbDT;
    private RatingBar ratingBar;
    private Button btUpdate, btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initViews();
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
        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);

        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.tacgia)));
    }

    @Override
    public void onClick(View v) {
        if (v == btCancel) {
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
                Book b = new Book(ten, tacGia, phamVi, doiTuong, rating);
                SQLiteHelper db = new SQLiteHelper(this);
                db.addBook(b);
                finish();
            }
        }
    }
}