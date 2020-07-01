package sict.myproject.anticovid_19.Model;

public class Nguoithan {
    private int mID;
    private String mName;

    public Nguoithan(int mID, String mName) {
        this.mID = mID;
        this.mName = mName;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
