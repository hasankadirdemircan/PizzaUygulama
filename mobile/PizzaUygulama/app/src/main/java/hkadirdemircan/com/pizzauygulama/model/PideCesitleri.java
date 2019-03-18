package hkadirdemircan.com.pizzauygulama.model;

public class PideCesitleri {

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
        return "PideCesitleri{" +
                "id=" + id +
                ", cesit='" + cesit + '\'' +
                '}';
    }
}
