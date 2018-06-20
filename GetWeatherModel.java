import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

class GetWeatherModel {

    private final String query;
    private URL url;


    GetWeatherModel(String city) {
        String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";
        String APIKEY = "&appid=5caf295bc4c09af3c6719fcb103d2ef9";
        query = BASE_URL + city + APIKEY;
    }

    ArrayList<WeatherModel> get() throws JSONException {

        try {
            url = new URL(query);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String JSONstr = null;
        try {
            HttpURLConnection client = (HttpURLConnection) url.openConnection();

            client.setRequestMethod("GET");

            client.connect();

            InputStream inputStream = client.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            JSONstr = builder.toString();
            System.out.println(JSONstr);

        } catch (IOException e) {
            e.printStackTrace();
        }


        JSONObject jsonData = new JSONObject(JSONstr);
        JSONArray array = jsonData.getJSONArray("list");

        ArrayList<WeatherModel> models = new ArrayList<>();
        String currentDate = null;
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);

            if (i != 0 && currentDate.substring(0, 10).equals(object.getString("dt_txt").substring(0, 10))) {
                continue;
            }
            currentDate = object.getString("dt_txt");
            JSONObject weather = object.getJSONArray("weather").getJSONObject(0);
            JSONObject MAIN = object.getJSONObject("main");

            models.add(new WeatherModel(
                    jsonData.getJSONObject("city").getString("name"),
                    currentDate,
                    weather.getString("main"),
                    weather.getString("description"),
                    weather.getString("icon"),
                    String.valueOf(MAIN.getDouble("temp")),
                    String.valueOf(MAIN.getDouble("temp_min")),
                    String.valueOf(MAIN.getDouble("temp_max")),
                    String.valueOf(MAIN.getInt("humidity")),
                    String.valueOf(object.getJSONObject("wind").getDouble("speed")))
            );

        }
        return models;

    }

}
