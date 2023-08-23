import org.json.simple.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class Main{

    public static void main(String[] args) {
        Connection gourav = new Connection();
        gourav.getURL();
    }
}

class Connection{
    void getURL() {
        Scanner sc1 = new Scanner(System.in);
        String city = sc1.next();
        String state = sc1.next();
        try {
            URL url = new URL("http://api.openweathermap.org/geo/1.0/direct?q="+city+","+state+",IN&limit=1&appid=510f3c5236baa07c585a598fd591f5cf");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            System.out.println("Connection Established");

            con.getResponseCode();
            System.out.println(con.getResponseCode());


            Scanner sc = new Scanner(url.openStream());
            String input = " ";
            while(sc.hasNext())
            {
                input+=sc.nextLine();
            }
            sc.close();
            //System.out.println(input);
            JSONParser par = new JSONParser();
            JSONArray arr = (JSONArray) par.parse(input);
            System.out.println(arr);
            JSONObject obj1 = (JSONObject) arr.get(0);
            Double lat = (Double)obj1.get("lat");
            Double lon = (Double)obj1.get("lon");
            System.out.println(lat);
            System.out.println(lon);
            URL url1 = new URL("https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=510f3c5236baa07c585a598fd591f5cf");
            HttpURLConnection con1 = (HttpURLConnection) url1.openConnection();
            con1.setRequestMethod("GET");
            con1.connect();

            System.out.println("Connection Established");

            con1.getResponseCode();
            System.out.println(con1.getResponseCode());

            Scanner sc2 = new Scanner(url.openStream());
            String input2 = " ";
            while(sc2.hasNext())
            {
                input2+=sc2.nextLine();
            }
            sc2.close();
            System.out.println(input2);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}