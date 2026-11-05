package SwingApproach;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EvenPaz ep = new EvenPaz(3);
            EvenPazView epView = new EvenPazView();
            Controller controller = new Controller(ep, epView);

            JFrame frame;
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame = new JFrame("Cake Cutting, Even and Paz");
            frame.add(epView);
            frame.setSize(screenSize);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(true);

            controller.start();
        });

    }

}
