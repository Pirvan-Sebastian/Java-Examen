package DataBase;

import DataBase.ObiectDefaultDB;
import Test.ObiectDefault;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitireAfisareDB
{
    public static void main(String[] args) throws IOException,SQLException
    {
        //CITIRE SQL
        List<ObiectDefaultDB> listaDate = new ArrayList<>();
        String url="jdbc:sqlite:date/contacte.db";

        try(Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            ResultSet rezultat = statement.executeQuery("select * from Contacte"))
        {
            while(rezultat.next())
            {
                //Cod-int 1 Nume-String 2 Telefon-Int 3
                var cod=rezultat.getInt(1);
                var nume=rezultat.getString(2);
                var telefon=rezultat.getLong(3);

                listaDate.add(new ObiectDefaultDB(cod,nume,telefon));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    //AFISARE SQL
        try(Connection conn=DriverManager.getConnection(url);
            PreparedStatement statement= conn.prepareStatement(
                    "INSERT INTO Contacte(Cod,Nume,Telefon) VALUES (?,?,?)"))
        {
           //nu va merge deoarece datele in DB sunt UNIQUE si eu le adaug pe aceleasi aici dar logica e buna
            for(ObiectDefaultDB obj:listaDate)
            {
                statement.setInt(1,obj.getCod());
                statement.setString(2,obj.getNume());
                statement.setInt(3,(int)obj.getTelefon()); //int ca nu am mai schimbat in clasa eu de sila

                statement.executeUpdate();
            }

        }

    }
}
