/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author Selina A.S.
 */
public class HttpUtils {
    
/*HttpUtils-kode taget fra undervisningen. Denne HttpUtils er en hjælpeklasse. 
    FetchData er en standardiseret måde at udføre HTTP GET-anmodninger og modtage data fra en given URL. 
Ved at abstrahere detaljerne vedrørende håndteringen af HTTP-anmodninger og 
dataindhentning i en separat hjælpeklasse som HttpUtils kan man opnå kodegenbrug,
bedre vedligeholdelse og mere organiseret struktur i ens backend-kode.
    */
     public static String fetchData(String _url) throws MalformedURLException, IOException {
        URL url = new URL(_url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        //con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", "server");

        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        return jsonStr;
    }
    
    
    
}
