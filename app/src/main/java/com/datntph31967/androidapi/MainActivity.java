package com.datntph31967.androidapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchUIUtil;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {
    TextView tvKQ;
    Context context = this;
    String strKQ = "";
    ToDo todo = null;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvKQ = findViewById(R.id.tvKQ);
        database = FirebaseFirestore.getInstance();
        //insert();
        //update();
        delete();
        select();
    }

    void insert() {
        String id = UUID.randomUUID().toString();
        todo = new ToDo(id, "title 01", "conten 01");//tạo đối tượng để insert
        database.collection("TODO")//truy cập dữ liệu
                .document(id)
                .set(todo.convertToHashMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Thêm Thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Thêm Thất bại", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    void update(){
        String id ="178f800e-4c17-49a9-a4ab-a2b6a9ede83f";
        todo = new ToDo(id,"title 01 update","conten 01 update");
        database.collection("TODO")
                .document(id)
                .update(todo.convertToHashMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "update thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "update thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    void delete(){
        String id = "178f800e-4c17-49a9-a4ab-a2b6a9ede83f";
        database.collection("TODO")
                .document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    ArrayList<ToDo> select(){
        ArrayList<ToDo> list = new ArrayList<>();
        database.collection("TODO")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isComplete()){
                            strKQ="";
                            for (QueryDocumentSnapshot doc:task.getResult()){
                                ToDo t = doc.toObject(ToDo.class);//chuyển dữ liệu đọc được sang Todo
                                list.add(t);
                                strKQ += "id: "+ t.getId() + "\n";
                                strKQ += "title: "+ t.getTitle() + "\n";
                                strKQ += "conten: "+ t.getConten() + "\n";
                            }
                            tvKQ.setText(strKQ);
                        }else {
                            Toast.makeText(context, "Select thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return  list;
    }
}