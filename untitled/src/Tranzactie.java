//public enum Tip{Intrare,Iesire}

public class Tranzactie implements Comparable<Tranzactie>
{
    public int codProdus;
    public int cantitate;
//  public Tip tip;
    public String tip; //mai simplu decat enum

    public Tranzactie(int codProdus, int cantitate, String tip)
    {
        this.codProdus = codProdus;
        this.cantitate = cantitate;
        this.tip = tip;
    }

    public int getCodProdus() {
        return codProdus;
    }

    public void setCodProdus(int codProdus) {
        this.codProdus = codProdus;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "codProdus=" + codProdus + ", cantitate=" + cantitate + ", tip='" + tip;
    }

    @Override
    public int compareTo(Tranzactie tranzactie) {
        return this.cantitate - tranzactie.cantitate;
    }
}
