import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;

class WeatherCard {
    private WeatherModel model;

    WeatherCard(WeatherModel model) {
        this.model = model;
    }

    JPanel get() throws IOException {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Date:");
        JLabel date = new JLabel(model.getDate().substring(0, 10));

        String format = "##.##";
        String imageURL = "http://openweathermap.org/img/w/" + model.getIcon() + ".png";
        JLabel weatherIcon = new JLabel();
        weatherIcon.setIcon(new ImageIcon(ImageIO.read(new URL(imageURL).openStream())));

        double tk = Double.parseDouble(model.getTemp());
        double tc = tk - 273.15;
        double tf = tc * 9 / 5 + 32.0;
        JLabel temp = new JLabel("<html>" + new DecimalFormat(format).format(tc) + " °C<br>"
                + new DecimalFormat(format).format(tf) + " °F" + "</html>");

        temp.setPreferredSize(new Dimension(50, 30));
        JLabel minTempLabel = new JLabel("Min. Temp:");
        tk = Double.parseDouble(model.getMinTemperature());
        tc = tk - 273.15;
        tf = tc * 9 / 5 + 32.0;
        JLabel minTemp = new JLabel(new DecimalFormat(format).format(tc) + " °C/"
                + new DecimalFormat(format).format(tf) + " °F");
        JLabel maxTempLabel = new JLabel("Max. Temp:");
        tk = Double.parseDouble(model.getMaxTemperature());
        tc = tk - 273.15;
        tf = tc * 9 / 5 + 32.0;

        JLabel maxTemp = new JLabel(new DecimalFormat(format).format(tc) + " °C/"
                + new DecimalFormat(format).format(tf) + " °F");

        JLabel humidityLabel = new JLabel("Humidity:");
        JLabel humidity = new JLabel(model.getHumidity() + "%");

        JLabel windSpeedLabel = new JLabel("Wind:");
        JLabel windSpeed = new JLabel(new DecimalFormat(format).format(Double.parseDouble(model.getWindSpeed()) * 3.6) + " Kmph");


        JLabel desc = new JLabel(model.getDescription());

        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(8, 8, 8, 8);
        constraints.gridx = constraints.gridy = 0;
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()));
        panel.add(label, constraints);
        constraints.gridx++;
        panel.add(date, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        panel.add(weatherIcon, constraints);
        constraints.gridx = 1;
        panel.add(temp, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        panel.add(desc, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        panel.add(minTempLabel, constraints);
        constraints.gridx = 1;
        panel.add(minTemp, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        panel.add(maxTempLabel, constraints);
        constraints.gridx = 1;
        panel.add(maxTemp, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        panel.add(humidityLabel, constraints);
        constraints.gridx = 1;
        panel.add(humidity, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        panel.add(windSpeedLabel, constraints);
        constraints.gridx = 1;
        panel.add(windSpeed, constraints);
        return panel;
    }
}
