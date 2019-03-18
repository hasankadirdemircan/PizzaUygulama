package com.hkdemircan.pideuygulama.model;

import javax.persistence.*;

@Entity
@Table(name = "siparis")
public class PideKaydet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    private String pideCesit;
    private String adet;
    private String tutar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPideCesit() {
        return pideCesit;
    }

    public void setPideCesit(String pideCesit) {
        this.pideCesit = pideCesit;
    }

    public String getAdet() {
        return adet;
    }

    public void setAdet(String adet) {
        this.adet = adet;
    }

    public String getTutar() {
        return tutar;
    }

    public void setTutar(String tutar) {
        this.tutar = tutar;
    }

    @Override
    public String toString() {
        return "PideKaydet{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", pideCesit='" + pideCesit + '\'' +
                ", adet='" + adet + '\'' +
                ", tutar='" + tutar + '\'' +
                '}';
    }
}
