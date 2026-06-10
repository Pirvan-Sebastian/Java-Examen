package TXT_CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CitireAfisareTXT
{
    public static void main(String[] args) throws IOException
    {
        List<ObiectDefault> listaObiecte = new ArrayList<ObiectDefault>();
        //-----------CITIRE TXT-----------------------
        try(BufferedReader br = new BufferedReader(new FileReader("./date/intrare.txt")))
        {
            String line;
            while((line = br.readLine()) != null)
            {
                String[] data = line.split(",");

                listaObiecte.add(new ObiectDefault(
                        Integer.parseInt(data[0]),
                        data[1],
                        Double.parseDouble(data[2])
                ));
            }
        }

        //-----------AFISARE TXT-----------------------
        try(PrintWriter writer = new PrintWriter("./date/output.txt"))
        {
            writer.println("Cod,Denumire,Pret\n");
            for(ObiectDefault obiectDefault : listaObiecte)
            {
                writer.printf("%d,%s,%f",obiectDefault.cod,obiectDefault.nume,obiectDefault.pret);
                writer.println();
            }
        }
    }
}
