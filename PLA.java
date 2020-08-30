public class PLA {

    double[][] x;
    double[] y;
    double[] weights;

    public PLA() {
        generateCoordinates();
        generateWeights();
    }

    public PLA(double[][] x, double[] y) {
        this.x = x;
        this.y = y;
        generateWeights();
    }

    public void updatePoint(int t) {

    }

    public boolean correctlyClassified(int index) {

    }

    public void generateCoordinates() {

    }

    public void generateWeights() {

    }

    public void adjustIteration() {
        b = true;
        for (int i = 0; i < y.length; i++) {
            if (!correctlyClassified(i)) {
                updatePoint(i);
                b = false;
            }
        }
    }

    public static void main(String[] args) {
        PLA pla = new PLA();



    }

}