import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        //citire txt
        List<Produs> produse = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("produse.txt")))
        {
            String linie;
            while((linie=br.readLine())!=null)
            {
                String[] lineSplit = linie.split(",");

                int id = Integer.parseInt(lineSplit[0]);
                String denumire = lineSplit[1];
                double pret = Double.parseDouble(lineSplit[2]);

                produse.add(new Produs(id, denumire, pret));

            }

        }
        //1) Să se afișeze la consolă numărul de produse 2p
        System.out.println("Cerinta 1: Numar Produse= " + produse.size());

        //2) Să se afișeze la consolă lista de produse ordonate alfabetic.
       Collections.sort(produse); // nu merge produseSortate=Collections.sort(produse); fac direct
        // ca Collections.sort() intoarce un void nu merge assignat, pot face separat mai bine cu asignare dupa, sau clone
        System.out.println("Cerinta 2: Lista Produse Sortate");
        for(Produs p : produse)
        {
            System.out.println(p);
        }
        //afisare TXT
        try(PrintWriter writer = new PrintWriter("./date/subiect1/lista.txt"))
        {
            writer.println("DenumireProdus,Pret");
            Collections.reverse(produse);
            for(Produs p : produse)
            {
                writer.printf("%s,%f",p.denumire,p.pret);
                writer.println();
            }
            System.out.println("Fisier General cu Succes!");


        }

        //citire JSON
        List<Tranzactie> listaTranzactii = new ArrayList<>();

        try(FileReader reader = new FileReader("tranzactii.json"))
        {
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray array = new JSONArray(tokener);
            for(int i = 0; i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);

                listaTranzactii.add(new Tranzactie(
                        obj.getInt("codProdus"),
                        obj.getInt("cantitate"),
                        obj.getString("tip")
                ));
            }
        }
        //cerinta 4
        //4) Să se afișeze la consolă valoarea totală a stocurilor.
        double suma=0;
        for(Tranzactie t : listaTranzactii)
        {
           for(Produs p : produse)
           {
               if(p.getCod()==t.getCodProdus())
               {
                   suma+=p.getPret()*t.cantitate;
               }
           }
        }
        System.out.println("CERINTA 4 \n Totalul Stocurilor este: "+suma);
        //cerinta 3
        //3) Să se scrie în fișierul text date\subiect1\listaExamen.txt un raport de forma:
        //Denumire Produs, Numar tranzactii
        Map<String,Integer> Combo=new HashMap<>();

        for(Tranzactie t : listaTranzactii)
        {

            for(Produs p : produse)
            {
                if(p.getCod()==t.getCodProdus())
                {
                  if(Combo.get(p.denumire)==null)
                  {
                      Combo.put(p.denumire,t.cantitate);
                  }
                  else
                  {
                    Combo.put(p.denumire,Combo.get(p.denumire)+t.cantitate);
                  }
                }
            }
        }

        try(PrintWriter writer = new PrintWriter("date/subiect1/listaExamen.txt"))
        {
            writer.println("DenumireProdus,Numar Tranzactii");
            List<Map.Entry<String,Integer>> cheiSortate= new ArrayList<>(Combo.entrySet());
            cheiSortate.sort((a, b) -> b.getValue().compareTo(a.getValue()));
            //(parametrii functie arrow)->{cod functie arrow} --ca la JS

            for(Map.Entry<String,Integer> intrare : cheiSortate)
            {
                writer.printf("%s,%d\n",intrare.getKey(),intrare.getValue());
            }
        }
    }
}
