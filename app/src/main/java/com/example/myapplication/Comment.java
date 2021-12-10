package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "comment")
public class Comment implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String commentUser;

    protected Comment(Parcel in) {
        id = in.readInt();
        name = in.readString();
        commentUser = in.readString();
    }

    public void setId(int id) {
        this.id = id;
    }

    public Comment(String name, String commentUser) {
        this.name = name;
        this.commentUser = commentUser;
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser = commentUser;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(commentUser);
    }

    @NonNull
    @Override
    public String toString() {
        return commentUser;
    }
}
