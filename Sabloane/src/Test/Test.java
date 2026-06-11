package Test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Test
{
    public static void main(String[] args) throws IOException,SQLException
    {
        List<ObiectDefault> lista= new ArrayList<>();
        //citire txt
        try(BufferedReader bf=new BufferedReader(new FileReader("./date/intrare.txt")))
        {
            String linie;
            while((linie=bf.readLine())!=null)
            {
                String[] data= linie.split(",");

                ObiectDefault obiect= new ObiectDefault(
                        Integer.parseInt(data[0]),
                        data[1],
                        Double.parseDouble(data[2])
                );

                lista.add(obiect);
            }
        }

        //afisare txt
        try(PrintWriter writer=new PrintWriter("./date/testout.txt"))
        {
            writer.println("Cod,Nume,Pret");
            for(ObiectDefault obj:lista)
            {
                writer.printf("%d,%s,%.2f\n",obj.getCod(),obj.getNume(),obj.getPret());
            }
        }

        //citire JSON
        List<ObiectDefault> listaJSON = new ArrayList<>();
        try(FileReader fisier=new FileReader("./date/input.json"))
        {
            JSONTokener tokener= new JSONTokener(fisier);
            JSONArray array= new JSONArray(tokener);

            for(int i=0;i< array.length();i++)
            {
                JSONObject objJSON= array.getJSONObject(i);

                listaJSON.add(new ObiectDefault(
                        objJSON.getInt("codProdus"),
                        objJSON.getString("nume"),
                        objJSON.getDouble("pret")
                ));
            }
        }
        //afisare JSON
        try(PrintWriter writer=new PrintWriter("./date/testout.json"))
        {
            JSONArray arrayJSON= new JSONArray();
            for(ObiectDefault obj:listaJSON)
            {
                JSONObject obiectJSON = new JSONObject();

                obiectJSON.put("codProdus",obj.getCod());
                obiectJSON.put("nume",obj.getNume());
                obiectJSON.put("pret",obj.getPret());

                arrayJSON.put(obiectJSON);
            }

            writer.write(arrayJSON.toString());
        }

        //citire DB
        List<ObiectDefaultDB> listaDB = new ArrayList<>();
        String url="jdbc:sqlite:date/contacte.db";

        try(Connection conn= DriverManager.getConnection(url);
        Statement statement=conn.createStatement();
        ResultSet result= statement.executeQuery("SELECT *FROM Contacte"))
        {
            while(result.next())
            {
                var cod=result.getInt(1);
                var nume=result.getString(2);
                var telefon=result.getLong(3);

                listaDB.add(new ObiectDefaultDB(cod,nume,telefon));
            }
        }

        //afiasre DB
        try(Connection conn=DriverManager.getConnection(url);
        PreparedStatement statement=conn.prepareStatement(
                "INSERT INTO Contacte(Cod,NUme,Telefon) VALUES(?,?,?)"))
        {
            for(ObiectDefaultDB obj:listaDB)
            {
                statement.setInt(1,obj.getCod());
                statement.setString(2,obj.getNume());
                statement.setInt(3,(int)obj.getTelefon());

                statement.executeUpdate();
            }
        }


    }
}
