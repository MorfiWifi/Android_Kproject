package com.apps.morfiwifi.morfi_project_samane.ui;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

    /*
        This source code could be used for academic purposes only. Posting on other websites or blogs is only allowed with a dofollow link to the orignal content.
    */

public class temp_class
{
    // Declaring layout button, edit texts
    Button login;
    EditText username,password;
    ProgressBar progressBar;
    // End Declaring layout button, edit texts

    // Declaring connection variables
    Connection con;

    //End Declaring connection variables





        protected String doInBackground(String...params)
        {
            String username = params[0];
            String pass = params[1];
            String db = params[2];
            String ip = params[3];

            String z;
            if(username.trim().equals("")|| pass.trim().equals(""))
                z = "Please enter Username and Password";
            else
            {
                boolean isSuccess;
                try
                {
                    con = connectionclass(username, pass, db, ip);        // Connect to database
                    if (con == null)
                    {
                        z = "Check Your Internet Access!";
                    }
                    else
                    {
                        // Change below query according to your own database.
                        String query = "select * from Logs";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if(rs.next())
                        {
                            z = "Login successful";
                            isSuccess=true;
                            con.close();
                        }
                        else
                        {
                            z = "Invalid Credentials!";
                            isSuccess = false;
                        }
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = ex.getMessage();
                }
            }
            return z;
        }


    public String ConnectToDatabase(){
        String res;
        try {


            // SET CONNECTIONSTRING
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            String username = "kasrazhi_aa";
            String password = "bbBB11!!";
            Connection DbConn = DriverManager.getConnection("jdbc:jtds:sqlserver://185.4.30.96/kasrazhi_aa;user=" + username + ";password=" + password);

            Log.w("Connection","open");
            Statement stmt = DbConn.createStatement();
            ResultSet reset = stmt.executeQuery(" select * from Logs ");
            res  = reset.toString();
            if (reset == null)
                res = "Null";

            res = DbConn.toString();
//            EditText num = (EditText) findViewById(R.id.displaymessage);
//            num.setText(reset.getString(1));

            DbConn.close();

        } catch (Exception e)
        {
            Log.w("Error connection","" + e.getMessage());
            res =  e.getMessage();
            res = "ERROR";
        }
        return res;
    }

    @SuppressLint("NewApi")
    public Connection connectionclass(String user, String password, String database, String server)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "data source=185.4.30.96,2014;initial catalog=kasrazhi_aa;persist security info=True;user id=kasrazhi_aa;password=bbBB11!!;MultipleActiveResultSets=True;App=EntityFramework";
                    //"jdbc:jtds:sqlserver://" + server + database + ";user=" + user+ ";password=" + password + ";";





            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}