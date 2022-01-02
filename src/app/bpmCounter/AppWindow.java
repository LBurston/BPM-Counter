package app.bpmCounter;
import javax.swing.*;
import java.awt.*;

public class AppWindow {

    private Container window;
    private int bpm;
    private final Counter counter;

    public AppWindow() {
        bpm = 0;
        counter = new Counter(this);
        createGUI();
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    private void createGUI() {
        JFrame app = new JFrame("BPM Counter");
        app.setPreferredSize(new Dimension(260, 260));
        window = app.getContentPane();

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menu.setPreferredSize(new Dimension(60, 20));
        menuBar.add(menu);
        JMenuItem newWindow = new JMenuItem("Open Another Counter");
        JMenuItem howTo = new JMenuItem("How to Use");
        JMenuItem exit = new JMenuItem("Exit");
        menu.add(newWindow);
        menu.add(howTo);
        menu.add(new JSeparator());
        menu.add(exit);

        window.setLayout(new BoxLayout(window, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("BPM:");
        JLabel bpmCount = new JLabel("" + bpm);
        JButton beatInput = new JButton("Tap here");
        label.setFont(new Font("Serif", Font.BOLD, 40));
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        bpmCount.setFont(new Font("Serif", Font.PLAIN, 46));
        bpmCount.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        beatInput.setPreferredSize(new Dimension(200,200));
        beatInput.setAlignmentX(JButton.CENTER_ALIGNMENT);
        beatInput.addActionListener((ev) -> bpmCount.setText("" + bpm));
        beatInput.addActionListener((ev) -> setBpm(counter.getCurrentBPM()));
        beatInput.addActionListener((ev) -> counter.buttonPress());



        app.setJMenuBar(menuBar);
        window.add(label);
        window.add(bpmCount);
        window.add(Box.createVerticalGlue());
        window.add(beatInput);
        window.add(new Box.Filler(new Dimension(0, 20), null, null));
        app.setResizable(false);

//        JLabel variableStoredTimes = new JLabel(String.valueOf(counter.getStoredTimes()));
//        JLabel variableHasLooped = new JLabel(String.valueOf(counter.isHasLooped()));
//        JLabel variableCurrentCount=  new JLabel(String.valueOf(counter.getCurrentCount()));
//
//        beatInput.addActionListener((ev) -> variableStoredTimes.setText(String.valueOf(counter.getStoredTimes())));
//        beatInput.addActionListener((ev) -> variableHasLooped.setText(String.valueOf(counter.isHasLooped())));
//        beatInput.addActionListener((ev) -> variableCurrentCount.setText(String.valueOf(counter.getCurrentCount())));
//
//        window.add(variableStoredTimes);
//        window.add(variableHasLooped);
//        window.add(variableCurrentCount);

        app.setVisible(true);
        app.pack();

    }


}
