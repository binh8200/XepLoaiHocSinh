package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.test.Adapter.SinhVienAdapter;
import com.example.test.DAO.SinhVienDAO;
import com.example.test.Model.SinhVien;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SinhVienDAO sinhvienDAO;
    SinhVienAdapter adapter;
    ArrayList<SinhVien> list_sv;
    EditText edtTen, edtDiem;
    Button btnSubmit, btnXoa;
    ListView lvSinhVien;

    RelativeLayout rellay1, rellay2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash

        list_sv = new ArrayList<>();
        sinhvienDAO = new SinhVienDAO(this);
        list_sv = sinhvienDAO.xemQLSV();
        adapter = new SinhVienAdapter(this,list_sv);
        lvSinhVien.setAdapter(adapter);

        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SinhVien sv = list_sv.get(position);
                edtTen.setText(sv.getTen());
            }
        });

        //-----xoa-----
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SinhVien sv = new SinhVien();
                sv.setTen(edtTen.getText().toString());
                if (sinhvienDAO.XoaSinhVien(sv) == 1){
                    list_sv = sinhvienDAO.xemQLSV();
                    adapter = new SinhVienAdapter(MainActivity.this,list_sv);
                    lvSinhVien.setAdapter(adapter);
                    Toast.makeText(MainActivity.this,"Xóa Thành Công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Xóa Không Thanh Công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SinhVien sv = new SinhVien();
                sinhvienDAO = new SinhVienDAO(MainActivity.this);
                sv.setTen(edtTen.getText().toString());
                sv.setDiem(edtDiem.getText().toString());

                if (sinhvienDAO.ThemSinhVien(sv) == -1 ) {
                    Toast.makeText(MainActivity.this, "Thêm Không Thành Công",Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(MainActivity.this,"Thêm Thành Công",Toast.LENGTH_SHORT).show();
                list_sv = sinhvienDAO.xemQLSV();
                adapter = new SinhVienAdapter(MainActivity.this,list_sv);
                lvSinhVien.setAdapter(adapter);
            }
        });
    }

    public void init() {
        edtTen = findViewById(R.id.edtTen);
        edtDiem = findViewById(R.id.edtDiem);
        btnSubmit = findViewById(R.id.btnSubmit);
        lvSinhVien = findViewById(R.id.lvSinhVien);
        btnXoa = findViewById(R.id.btnXoa);
    }
}
