import java.util.Random;

public class PLA {

    Vector[] x;
    Vector y;
    Vector weights;

    int features;
    int size;

    public PLA(int size, int features) {
        this.size = size;
        this.features = features;
        this.x = new Vector[size];
        for (int i = 0; i < size; i++) {
            x[0] = new Vector(features + 1);
        }
        this.y = new Vector(size);
        this.weights = new Vector(features + 1);
        generateCoordinates();
        generateWeights();
    }

    public PLA() {
        this(20, 2);
    }

    public PLA(double[][] x, double[] y) {
        this.size = y.length;
        this.features = x[0].length;
        this.x = new Vector[size];
        for (int i = 0; i < size; i++) {
            this.x[0] = new Vector(x[0]);
        }
        this.y = new Vector(y);
        this.weights = new Vector(features);
        generateWeights();
    }

    private static class Vector {

        private double[] vector;
        private int size;

        private Vector(double[] vector) {
            this.vector = vector;
            this.size = vector.length;
        }

        private Vector(int size) {
            this.vector = new double[size];
            this.size = size;
        }

        public static double dot(Vector v1, Vector v2) {
            double sum = 0;
            for (int i = 0; i < v1.size; i++) {
                sum += v1.vector[i] * v2.vector[i];
            }
            return sum;
        }

        public static Vector elementTimes(Vector v1, Vector v2) {
            Vector v = new Vector(v1.size);
            for (int i = 0; i < v.size; i++) {
                v.vector[i] = v1.vector[i] * v2.vector[i];
            }
            return v;
        }

        public static Vector elementTimes(double d, Vector v) {
            Vector ret = new Vector(v.size);
            for (int i = 0; i < v.size; i++) {
                ret.vector[i] = v.vector[i] * d;
            }
            return ret;
        }

        public static Vector add(Vector v1, Vector v2) {
            Vector v = new Vector(v1.size);
            for (int i = 0; i < v.size; i++) {
                v.vector[i] = v1.vector[i] + v2.vector[i];
            }
            return v;
        }

        @Override
        public String toString() {
            String s = "";
            for (double d : vector) {
                s += d + " ";
            }
            s += "\n";
            return s;
        }

    }

    public void updatePoint(int t) {
        weights = Vector.add(weights, Vector.elementTimes(y.vector[t], x[t]));
    }

    public boolean correctlyClassified(int index) {
        return Math.signum(Vector.dot(weights, x[index])) == y.vector[index];
    }

    //generate linearly seperable data?? TODO
    public void generateCoordinates() {
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            x[0].vector[0] = 0;
            for (int j = 1; j < features; j++) {
                x[i].vector[j] = r.nextInt(100);
            }
            y.vector[i] = r.nextInt(2)*2 - 1;
        }

    }

    //theoretically generates data can test on, TODO
//    public void createTestData() {
//        int size = this.x.length / 5;
//        int[] x = new int[size];
//        int[] y = new int[size];
//
//        Random r = new Random();
//        x[0] = 1;
//        y[0] = 1;
//        for (int i = 1; i < x.length; i++) {
//            x[i] = r.nextInt(100);
//            y[i] = r.nextInt(2)*2 - 1;
//        }
//    }

//    public void test() {
//        createTestData();
//    }

    public void generateWeights() {
        for (int i = 0; i < features; i++) {
            weights.vector[i] = 0;
        }
    }

    public void adjustIteration() {
        boolean b = false;
        while (!b) {
            b = true;
            for (int i = 0; i < size; i++) {
                if (!correctlyClassified(i)) {
                    updatePoint(i);
                    b = false;
                }
            }
        }
    }


    public static void display(PLA pla) {
//        printArray(pla.x);
        System.out.println(pla.y);
        System.out.println(pla.weights);
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