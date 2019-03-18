package com.hkdemircan.pideuygulama.model;

import javax.persistence.*;

@Entity
@Table(name = "pidecesit")
public class Pide {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String cesit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCesit() {
        return cesit;
    }

    public void setCesit(String cesit) {
        this.cesit = cesit;
    }

    @Override
    public String toString() {
        return "Pide{" +
                "id=" + id +
                ", cesit='" + cesit + '\'' +
                '}';
    }
}
