public class Produs implements Comparable
{
    public int cod;
    public String denumire;
    public double pret;
    public Produs(int cod, String denumire, double pret)
    {
        this.cod = cod;
        this.denumire = denumire;
        this.pret = pret;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    @Override
    public String toString()
    {
        return "cod=" + cod + ", denumire='" + denumire + ", pret=" + pret +"RON";
    }

    @Override
    public int compareTo(Object o) {
        return this.getDenumire().compareTo(((Produs)o).getDenumire());
    }
}
