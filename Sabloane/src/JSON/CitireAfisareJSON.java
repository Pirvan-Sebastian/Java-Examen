package JSON;

import JSON.ObiectDefault;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.XMLTokener;
import org.xml.sax.XMLReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CitireAfisareJSON
{
    public static void main(String[] args) throws IOException
    {
        List<ObiectDefault> listaObiecte = new ArrayList<>();
        //---------CITIRE JSON----------------
        try(FileReader fisier=new FileReader("./date/input.JSON"))
        {
            JSONTokener tokener= new JSONTokener(fisier);
            JSONArray array = new JSONArray(tokener);
            for(int i=0;i< array.length();i++)
            {
                JSONObject obj= array.getJSONObject(i);
//             "codProdus": 2,
//                 "nume": "Seminte",
//                 "pret": 21.18
                listaObiecte.add(new ObiectDefault(
                        obj.getInt("codProdus"),
                        obj.getString("nume"),
                        obj.getDouble("pret")
                ));
            }
        }

        //---------AFISARE JSON----------------

        try(PrintWriter writer =new PrintWriter("./date/iesire.json"))
        {
            JSONArray array = new JSONArray();
            for(ObiectDefault obiect: listaObiecte)
            {
                JSONObject obiectJson = new JSONObject();

                obiectJson.put("codProdus", obiect.getCod());
                obiectJson.put("nume",obiect.getNume());
                obiectJson.put("pret",obiect.getPret());

                array.put(obiectJson);
            }

            writer.write(array.toString());
        }

    }
}
