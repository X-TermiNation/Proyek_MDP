package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourite")
public class Favourite implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String judul;
    private String email;

    public Favourite(String judul, String email) {
        this.judul = judul;
        this.email = email;
    }

    protected Favourite(Parcel in) {
        judul = in.readString();
        email = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static final Creator<Favourite> CREATOR = new Creator<Favourite>() {
        @Override
        public Favourite createFromParcel(Parcel in) {
            return new Favourite(in);
        }

        @Override
        public Favourite[] newArray(int size) {
            return new Favourite[size];
        }
    };

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(judul);
        dest.writeString(email);
    }
}
