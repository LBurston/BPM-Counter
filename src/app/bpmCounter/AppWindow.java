package app.bpmCounter;
import javax.swing.*;
import java.awt.*;

public class AppWindow {

    private Container window;
    private int bpm;
    private Counter counter;

    public AppWindow() {
        bpm = 0;
        createGUI();
        counter = new Counter(this);
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    private void createGUI() {
        JFrame app = new JFrame();
        window = app.getContentPane();

        JMenuBar menu = new JMenuBar();
        menu.add(new JMenu("Settings"));
        JMenuItem newWindow = new JMenuItem("Open Another Counter");
        JMenuItem howTo = new JMenuItem("How to Use");
        JMenuItem exit = new JMenuItem("Exit");
        menu.add(newWindow);
        menu.add(howTo);
        menu.add(new JSeparator());
        menu.add(exit);

        window.setSize(new Dimension(400, 300));
        window.setLayout(new BoxLayout(window, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("BPM:");
        JLabel bpmCount = new JLabel("" + bpm);
        JButton beatInput = new JButton("Click here");
        label.setFont(new Font("Serif", Font.BOLD, 20));
        label.setAlignmentX(JLabel.CENTER);
        bpmCount.setFont(new Font("Serif", Font.PLAIN, 26));
        bpmCount.setAlignmentX(JLabel.CENTER);
        beatInput.setSize(new Dimension(360,50));
        beatInput.setAlignmentX(JButton.CENTER);
        beatInput.addActionListener((ev) -> counter.buttonPress());
        beatInput.addActionListener((ev) -> setBpm(counter.getCurrentBPM()));
        beatInput.addActionListener((ev) -> bpmCount.setText("" + bpm));

        window.add(label);
        window.add(bpmCount);
        window.add(beatInput);

        app.setVisible(true);
        app.pack();

    }

}
