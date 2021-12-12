package com.example.myapplication;

import java.util.HashMap;

public class Songs {
    String email;
    music inst;
    HashMap<String, String> songMap;

    public Songs(String email, music inst, HashMap<String, String> songMap) {
        this.email = email;
        this.inst = inst;
        this.songMap = songMap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public music getInst() {
        return inst;
    }

    public void setInst(music inst) {
        this.inst = inst;
    }

    public HashMap<String, String> getSongMap() {
        return songMap;
    }

    public void setSongMap(HashMap<String, String> songMap) {
        this.songMap = songMap;
    }
}
