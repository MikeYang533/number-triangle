package start;
package gui;
import javax.swing.*;

public class gui {
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {

        JPanel creditcardInfoPanel = new Jpanel();
        creditcardinfoPanel.add(new JLabel("Credit card info here for prize"));
        creditcardinfoPanel.add(new JTextField(10));


        JFrame frame = new JFrame("My GUI");
        frame.setMinimumSize(new java.awt.Dimension(400, 400));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        });
    }
}