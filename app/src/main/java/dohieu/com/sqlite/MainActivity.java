package dohieu.com.sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database database;

    ListView listView;
    ArrayList<CongViec> congViecArrayList;
    CongViecAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.listviewCV);
        congViecArrayList = new ArrayList<>();
        adapter = new CongViecAdapter(this, R.layout.dong_cong_viec, congViecArrayList);
        listView.setAdapter(adapter);
//  Tạo database Ghi Chú
        database = new Database(this, "ghichu.sqlite", null, 1);


//  Tạo bảng công việc
        database.QueryData("CREATE TABLE IF NOT EXISTS CongViec(ID INTEGER PRIMARY KEY AUTOINCREMENT, TenCV VARCHAR(200))");

//  INSERT DATA

//        database.QueryData("INSERT INTO CongViec VALUES(null,'Làm bài tập android')");
//        database.QueryData("INSERT INTO CongViec VALUES(null,'Viết ứng dụng ghi chú')");

//        SELEST DATA
        GetDataCongViec();

    }

    public void GetDataCongViec() {
        Cursor dataCongViec = database.GetData("SELECT * FROM CongViec");
        congViecArrayList.clear();
        while (dataCongViec.moveToNext()) {
            String ten = dataCongViec.getString(1);
            int id = dataCongViec.getInt(0);
            congViecArrayList.add(new CongViec(id, ten));
            Toast.makeText(this, ten, Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_congviec, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menuAdd) {
            Dialogthem();
        }
        return super.onOptionsItemSelected(item);
    }
    public void Dialogsuaw() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_suacv);
        EditText editText = findViewById(R.id.edtTenCVEdit);
        Button btnXacnhan = findViewById(R.id.btnSua);
        Button btnHuy = findViewById(R.id.btnHuyCVEdit);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    private void Dialogthem() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_themcongviec);

        final EditText edtTenCV = dialog.findViewById(R.id.edtTenCV);
        Button btnThem = dialog.findViewById(R.id.btnThemCV);
        Button btnHuy = dialog.findViewById(R.id.btnHuyCV);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenCV = edtTenCV.getText().toString();
                if (tenCV.equals("")) {
                    Toast.makeText(MainActivity.this, "Vui long them cong viec", Toast.LENGTH_SHORT).show();
                } else {
                    database.QueryData("INSERT INTO CongViec VALUES(null,'" + tenCV + "')");
                    Toast.makeText(MainActivity.this, "them cong viec thanh cong", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDataCongViec();
                }
            }
        });
        dialog.show();
    }


}
