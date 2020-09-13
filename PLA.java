import java.util.Random;

public class PLA {

    double[] x;
    double[] y;
    double[] weights;

    public PLA(int size) {
        this.x = new double[size + 1];
        this.y = new double[size + 1];
        this.weights = new double[size + 1];
        generateCoordinates();
        generateWeights();
    }

    public PLA() {
        this(20);
    }

    public PLA(double[] x, double[] y) {
        this.x = x;
        this.y = y;
        this.weights = new double[x.length];
        generateWeights();
    }

    public void updatePoint(int t) {
        weights[t] = weights[t] + y[t] * x[t];
    }

    public boolean correctlyClassified(int index) {
        return Math.signum(weights[index] * x[index]) == Math.signum(y[index]);
    }

    public void generateCoordinates() {
        Random r = new Random();
        x[0] = 1;
        y[0] = 1;
        for (int i = 1; i < x.length; i++) {
            x[i] = r.nextInt(100);
            y[i] = r.nextInt(2)*2 - 1;
        }

    }

    public void createTestData() {
        int size = this.x.length / 5;
        int[] x = new int[size];
        int[] y = new int[size];

        Random r = new Random();
        x[0] = 1;
        y[0] = 1;
        for (int i = 1; i < x.length; i++) {
            x[i] = r.nextInt(100);
            y[i] = r.nextInt(2)*2 - 1;
        }
    }

    public void test() {
        createTestData();
    }

    public void generateWeights() {
        for (int i = 0; i < x.length; i++) {
            weights[i] = 0;
        }
    }

    public void adjustIteration() {
        boolean b = false;
        while (!b) {
            b = true;
            for (int i = 0; i < y.length; i++) {
                if (!correctlyClassified(i)) {
                    updatePoint(i);
                    b = false;
                }
            }
        }
    }

    public static void printArray(double[] array) {
        for (double d : array) {
            System.out.print(d + " ");
        }
        System.out.println();
    }

    public static void display(PLA pla) {
        printArray(pla.x);
        printArray(pla.y);
        printArray(pla.weights);
        int sum = 0;
        for (int i = 0; i < pla.x.length; i++) {
            System.out.println(pla.correctlyClassified(i));
            if (pla.correctlyClassified(i)) {
                sum++;
            }
        }

        System.out.println(sum);
    }

    public static void main(String[] args) {
        PLA pla = new PLA();
        display(pla);
        pla.adjustIteration();
        display(pla);

    }

}