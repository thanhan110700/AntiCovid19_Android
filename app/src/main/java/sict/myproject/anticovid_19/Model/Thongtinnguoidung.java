package sict.myproject.anticovid_19.Model;

public class Thongtinnguoidung {
    String hovaten,CMT,BHXH,ngaysinh,gioitinh,quoctich,diachi,sodienthoai,email;

    public Thongtinnguoidung(String hovaten, String CMT, String BHXH, String ngaysinh, String gioitinh, String quoctich, String diachi, String sodienthoai, String email) {
        this.hovaten = hovaten;
        this.CMT = CMT;
        this.BHXH = BHXH;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.quoctich = quoctich;
        this.diachi = diachi;
        this.sodienthoai = sodienthoai;
        this.email = email;
    }

    public String getHovaten() {
        return hovaten;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }

    public String getCMT() {
        return CMT;
    }

    public void setCMT(String CMT) {
        this.CMT = CMT;
    }

    public String getBHXH() {
        return BHXH;
    }

    public void setBHXH(String BHXH) {
        this.BHXH = BHXH;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getQuoctich() {
        return quoctich;
    }

    public void setQuoctich(String quoctich) {
        this.quoctich = quoctich;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
