package sict.myproject.anticovid_19.Model;

public class Countries {
    public int mImgCountry;
    public String mNamecountry;

    public Countries(int mImgCountry, String mNamecountry) {
        this.mImgCountry = mImgCountry;
        this.mNamecountry = mNamecountry;
    }

    public int getmImgCountry() {
        return mImgCountry;
    }

    public void setmImgCountry(int mImgCountry) {
        this.mImgCountry = mImgCountry;
    }

    public String getmNamecountry() {
        return mNamecountry;
    }

    public void setmNamecountry(String mNamecountry) {
        this.mNamecountry = mNamecountry;
    }
}
