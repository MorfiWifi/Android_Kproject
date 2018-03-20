package com.apps.morfiwifi.morfi_project_samane.utility;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by WifiMorfi on 12/20/2017.
 */
public class Init {
    public static  void Pront_mark(){
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MNssssssyMMMMMhssssssmMMMMMMMdssssshMMMMMMMNssssyyyyyydmNMMM");
        System.out.println("Mm       sMMMm       sMMMMMMm`     `dMMMMMMd            -sNM");
        System.out.println("Mm       `NMM/       sMMMMMN-   `   .NMMMMMd     +o+/`    :N");
        System.out.println("Mm    +   oMd   +    sMMMMM/   `h`   :MMMMMd     dMMMm`    o");
        System.out.println("Mm    h:  `m:  `m    sMMMMs    sMs    oMMMMd     dMMMM/    :");
        System.out.println("Mm    hd   :   oM    sMMMd`   .hdh.    yMMMd     dMMMM:    :");
        System.out.println("Mm    hM/     `NM    sMMm.             `mMMd     dNNms     y");
        System.out.println("Mm    hMm     sMM`   sMM:    :+++++:    -NMd     ...`    `sM");
        System.out.println("MN````hMM+```-NMM.```yMs````-NMMMMMN-````+Md````````..-/smMM");
        System.out.println("MMNNNNMMMMNNNNMMMNNNNMMNNNNNNMMMMMMMNNNNNNMMNNNNNNNNNNNMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
    }
    public  static  String Token = "";
    public static boolean CheckInternet (){
        try {
            try {
                URL url = new URL("http://www.google.com");
                //System.out.println(url.getHost());
                HttpURLConnection con = (HttpURLConnection) url
                        .openConnection();
                con.connect();
                if (con.getResponseCode() == 200){
                    return true;
                    //System.out.println("Connection established!!");
                }
            } catch (Exception exception) {
                return false;
                //System.out.println("No Connection");
            }
        } catch (Exception e) {
            return false;
            //e.printStackTrace();
        }
        return false;
    }

}
