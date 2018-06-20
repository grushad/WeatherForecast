import org.json.JSONException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ShowForecast extends JFrame {
    private String city;

    ShowForecast(String city) throws IOException, JSONException {
        this.city = city;
        initComponents();
    }

    private void initComponents() throws JSONException, IOException {


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        ArrayList<WeatherModel> models = new GetWeatherModel(city).get();
        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = constraints.gridy = 0;
        for (WeatherModel model : models) {
            finalPanel.add(new WeatherCard(model).get(), constraints);
            constraints.gridx++;
        }

        setTitle(models.get(0).getCity());

        add(finalPanel);

        pack();
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowForecast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });

    }

}
