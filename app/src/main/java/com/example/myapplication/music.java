package com.example.myapplication;
import android.os.Parcel;
import android.os.Parcelable;

public class music implements Parcelable {

    private String judul;
    private int sound1,sound2,sound3,sound4,sound5,sound6,sound7,sound8,sound9,sound10,sound11,sound12;

    public music(String judul, int sound1, int sound2, int sound3, int sound4, int sound5, int sound6, int sound7, int sound8, int sound9, int sound10, int sound11, int sound12) {
        this.judul = judul;
        this.sound1 = sound1;
        this.sound2 = sound2;
        this.sound3 = sound3;
        this.sound4 = sound4;
        this.sound5 = sound5;
        this.sound6 = sound6;
        this.sound7 = sound7;
        this.sound8 = sound8;
        this.sound9 = sound9;
        this.sound10 = sound10;
        this.sound11 = sound11;
        this.sound12 = sound12;
    }

    public int getSound1() {
        return sound1;
    }

    public void setSound1(int sound1) {
        this.sound1 = sound1;
    }

    public int getSound2() {
        return sound2;
    }

    public void setSound2(int sound2) {
        this.sound2 = sound2;
    }

    public int getSound3() {
        return sound3;
    }

    public void setSound3(int sound3) {
        this.sound3 = sound3;
    }

    public int getSound4() {
        return sound4;
    }

    public void setSound4(int sound4) {
        this.sound4 = sound4;
    }

    public int getSound5() {
        return sound5;
    }

    public void setSound5(int sound5) {
        this.sound5 = sound5;
    }

    public int getSound6() {
        return sound6;
    }

    public void setSound6(int sound6) {
        this.sound6 = sound6;
    }

    public int getSound7() {
        return sound7;
    }

    public void setSound7(int sound7) {
        this.sound7 = sound7;
    }

    public int getSound8() {
        return sound8;
    }

    public void setSound8(int sound8) {
        this.sound8 = sound8;
    }

    public int getSound9() {
        return sound9;
    }

    public void setSound9(int sound9) {
        this.sound9 = sound9;
    }

    public int getSound10() {
        return sound10;
    }

    public void setSound10(int sound10) {
        this.sound10 = sound10;
    }

    public int getSound11() {
        return sound11;
    }

    public void setSound11(int sound11) {
        this.sound11 = sound11;
    }

    public int getSound12() {
        return sound12;
    }

    public void setSound12(int sound12) {
        this.sound12 = sound12;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    protected music(Parcel in) {
        judul = in.readString();
    }

    public static final Creator<music> CREATOR = new Creator<music>() {
        @Override
        public music createFromParcel(Parcel in) {
            return new music(in);
        }

        @Override
        public music[] newArray(int size) {
            return new music[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(judul);
    }
}
