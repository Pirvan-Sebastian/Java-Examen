package TXT_CSV;

public class ObiectDefault implements Comparable<ObiectDefault>
{
    public int cod;
    public String nume;
    public double pret;

    public ObiectDefault(int cod, String nume, double pret)
    {
        this.cod = cod;
        this.nume = nume;
        this.pret = pret;
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

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return
                "cod=" + cod +
                ", nume='" + nume +
                ", pret=" + pret;
    }

    @Override
    public int compareTo(ObiectDefault obiectDefault) {
        return this.getCod() - obiectDefault.getCod();
    }
}
