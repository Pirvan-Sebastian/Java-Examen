package Test;

public class ObiectDefaultDB implements Comparable<ObiectDefaultDB>
{
    public int cod;
    public String nume;
    public long telefon;

    public ObiectDefaultDB(int cod, String nume, long telefon)
    {
        this.cod = cod;
        this.nume = nume;
        this.telefon = telefon;
    }

    public int getCod() {
        return cod;
    }
    public void setCod(int cod) {
        this.cod = cod;
    }
    public String getNume() {
        return nume;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public long getTelefon() {
        return telefon;
    }
    public void setTelefon(long telefon) {
        this.telefon = telefon;
    }
    @Override
    public String toString() {
        return
                "cod=" + cod +
                ", nume='" + nume +
                ", telefon=" + telefon;
    }
    @Override
    public int compareTo(ObiectDefaultDB obiectDefaultDB) {
        return this.getCod() - obiectDefaultDB.getCod();
    }
}
