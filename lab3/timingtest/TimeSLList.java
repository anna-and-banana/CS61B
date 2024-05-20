package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    // The size of the SLList for every test
    private static final int[] TEST_NUMBERS = new int[] {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
    // The Number of operations for every test
    private static final int OPERATION_NUMBERS = 10000;
    // Variable 'ITEM' for inserting
    private static final int ITEM = 1;

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        // Create AList 'Ns' by 'testNumbers'
        AList<Integer> Ns = new AList<>();
        for (int n : TEST_NUMBERS) {
            Ns.addLast(n);
        }

        // Prepare AList 'times' to store runtimes
        AList<Double> times = new AList<>();
        // Calculate runtime for every 'n' in 'Ns', then store in SLList 'times'
        for (int i = 0; i < Ns.size(); i++) {
            // Initialize for each turn
            SLList timingTest = new SLList<>();
            int n = Ns.get(i);
            while (n > 0) {
                timingTest.addFirst(ITEM);
                n--;
            }

            // Start calculating runtime
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < OPERATION_NUMBERS; j++) {
                timingTest.getLast();
            }
            // Check timer, then store at AList 'times'
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
        }

        // Prepare three AList, which are 'Ns', 'times', 'opCounts', then Call 'printTimingTable'
        // Create AList 'opCounts' by 'OPERATION_NUMBERS'
        AList<Integer> opCounts = new AList<>();
        for (int i = 0; i < Ns.size(); i++) {
            opCounts.addLast(OPERATION_NUMBERS);
        }
        printTimingTable(Ns, times, opCounts);
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
