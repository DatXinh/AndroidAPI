package com.datntph31967.androidapi;

import java.util.HashMap;

public class ToDo {
    private String id , title , conten;

    public ToDo(String id, String title, String conten) {
        this.id = id;
        this.title = title;
        this.conten = conten;
    }

    public ToDo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConten() {
        return conten;
    }

    public void setConten(String conten) {
        this.conten = conten;
    }
    // hàm convert dữ liệu sang dạng hashmap để lưu vào firebase
    public HashMap<String,Object>convertToHashMap (){

        HashMap<String,Object> work=new HashMap<>();
        work.put("id",id);
        work.put("title",title);
        work.put("content",conten);
        return work;
    }
}
