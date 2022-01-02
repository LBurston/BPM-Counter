package app.bpmCounter;

public class BpmCounter {

    private int instances;

    public static void main(String[] args) {
        BpmCounter currentApp = new BpmCounter();
        currentApp.run();
    }

    public BpmCounter() {
        instances = 0;
    }

    private void run() {
       instances++;
       new AppWindow();
    }



    public int getInstances() {
        return instances;
    }



}
