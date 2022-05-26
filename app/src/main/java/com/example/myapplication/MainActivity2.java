package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity2 extends AppCompatActivity {
    DBHelper myDb;
    EditText edID, edNama,edKelas,edStudi;
    Button btnAddData;
    Button btnViewAll;
    Button btnUpdate;
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myDb = new DBHelper(this);
        edID = (EditText) findViewById(R.id.editNIM);
        edNama = (EditText) findViewById(R.id.editNama);
        edKelas = (EditText) findViewById(R.id.editKelas);
        edStudi = (EditText) findViewById(R.id.editStudi);
        btnAddData = (Button) findViewById(R.id.btnSimpan);
        btnViewAll = (Button) findViewById(R.id.btnTampil);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        addData();
        viewAll();
        updateData();
        deleteData();
    }
    //Hapus
    public void deleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(edID.getText().toString());
                if (deletedRows > 0)
                    Toast.makeText(MainActivity2.this, "Data Dihapus",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity2.this, "Data Gagal Dihapus",
                            Toast.LENGTH_LONG).show();
            }
        });
    }
    //Update
    public void updateData(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(
                        edID.getText().toString(),
                        edNama.getText().toString(),
                        edKelas.getText().toString(),
                        edStudi.getText().toString()
                );
                if (isUpdate == true)
                    Toast.makeText(MainActivity2.this, "Data Berhasil Dirubah",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity2.this, "Data gagal Dirubah",
                            Toast.LENGTH_LONG).show();
            }
        });
    }
    //Simpan
    public void addData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(edID.getText().toString(),
                        edNama.getText().toString(),
                        edKelas.getText().toString(),
                        edStudi.getText().toString() );
                if (isInserted == true)
                    Toast.makeText(MainActivity2.this, "Data Berhasil Disimpan",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity2.this, "Data Gagal Disimpan",
                            Toast.LENGTH_LONG).show();
            }
        });
    }
    //Tampil
    public void viewAll(){
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0){
                    showMessage("Error", "Nothing Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID : " + res.getString(0)+"\n");
                    buffer.append("NAMA : "+ res.getString(1)+"\n");
                    buffer.append("Kelas : " + res.getString(2)+"\n");
                    buffer.append("Program Studi : " + res.getString(3)+"\n");
                }
                showMessage("Data", buffer.toString());
            }
        });
    }
    //Alert Dialog
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
