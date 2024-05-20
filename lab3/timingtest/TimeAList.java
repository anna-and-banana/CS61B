package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    // The size of the AList for every test
    private static int[] testNumbers = new int[] {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        // Create AList 'Ns' by 'testNumbers'
        AList<Integer> Ns = new AList<>();
        for (int n : testNumbers) {
            Ns.addLast(n);
        }
        // Calculate the running time for every N in Ns
        AList<Double> times = new AList<>();
        AList<Integer> timingTest = new AList<>();
        for (int i = 0; i < Ns.size(); i++) {
            int n = Ns.get(i);

            Stopwatch sw = new Stopwatch();
            while (n > 0) {
                timingTest.addLast(1);
                n--;
            }
            double timeInSeconds = sw.elapsedTime();

            // Add these 'time' to an AList 'times'
            times.addLast(timeInSeconds);

            // Prepare a new AList for next turn
            AList<Integer> lst = new AList<>();
            timingTest = lst;
        }

        // Prepare three AList, which are 'Ns', 'times', 'opCounts'(which is equal to 'Ns')
        // Call 'printTimingTable' use above parameters
        printTimingTable(Ns, times, Ns);
    }

    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }
}
