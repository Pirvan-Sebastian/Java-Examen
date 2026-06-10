package Test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Test
{
    public static void main(String[] args) throws IOException
    {
       List<ObiectDefault> list = new ArrayList<>();

       try(FileReader fisier = new FileReader("./date/input.json"))
       {
           JSONTokener tokener = new JSONTokener(fisier);
           JSONArray array = new JSONArray(tokener);

           for(int i=0;i< array.length();i++)
           {
               JSONObject obj= array.getJSONObject(i);
               list.add(new ObiectDefault(
                       obj.getInt("camp"),
                       obj.getString("camp"),
                       obj.getDouble("camp")
               ));
           }
       }

       try(PrintWriter writer = new PrintWriter("./date/testout.json"))
       {
           JSONArray array = new JSONArray();

           for(ObiectDefault obj: list)
           {
               JSONObject obiectJSON = new JSONObject();

               obiectJSON.put("camp", obj.getCod());
               obiectJSON.put("camp",obj.getNume());
               obiectJSON.put("camp",obj.getPret());

               array.put(obiectJSON);

           }
           writer.write(array.toString());
       }
    }
}
