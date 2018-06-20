
// Functions to retrieve data from the database
public class WeatherModel {
    private final String city;
    private String date;
    private String temp;
    private String main, description, icon, minTemperature, maxTemperature, humidity, windspeed;

    WeatherModel(String cityname, String date, String main, String description, String icon, String temp, String minTemperature, String maxTemperature, String humidity, String windspeed) {
        this.date = date;
        city = cityname;
        this.main = main;
        this.description = description;
        this.icon = icon;
        this.temp = temp;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.humidity = humidity;
        this.windspeed = windspeed;
    }

    String getTemp() {
        return temp;
    }

    public String getMain() {
        return main;
    }

    String getDescription() {
        return description;
    }

    String getIcon() {
        return icon;
    }

    String getMinTemperature() {
        return minTemperature;
    }

    String getMaxTemperature() {
        return maxTemperature;
    }

    String getHumidity() {
        return humidity;
    }

    String getWindSpeed() {
        return windspeed;
    }

    String getDate() {
        return date;
    }

    String getCity() {
        return city;
    }
}
