package education.multithreading.golovach.courses.lesson1;

/**
 * Created by yaroslav on 30.08.15.
 */
public class InstructionLevelParallelism {
    public static void main(String[] args) {
        onlySingleUnitExecutionPossible();
        parallelExecutionPossible();
    }

    public static void onlySingleUnitExecutionPossible() {
        System.out.println("Single processor unit execution");
        long t0 = System.currentTimeMillis();
        double d0 = 0;
        for (int k = 0; k < 100_000_000; k++) {
            /**
             * every d0 = d0 * d0 line will increase execution time, since every statement depends on the
             * result of previous statement. So processor cannot use multiple execution units simultaneously.
             */
            d0 = d0 * d0;
            d0 = d0 * d0;
        }
        long t1 = System.currentTimeMillis();
        System.out.println(t1 - t0);
        System.out.println(d0);
    }

    public static void parallelExecutionPossible() {
        System.out.println("Parallel execution");
        long t0 = System.currentTimeMillis();
        double d0 = 0;
        double d1 = 0;
        double d2 = 0;
        double d3 = 0;
        double d4 = 0;
        double d5 = 0;
        double d6 = 0;
        double d7 = 0;
        double d8 = 0;
        double d9 = 0;
        for (int k = 0; k < 100_000_000; k++) {
            /**
             * We could count how many float calculus units does our processor have.
             * If processor have 5 float calculus units => 5 independent statements will be processed simultaneously
             */
            d0 = d0 * d0;
            d1 = d1 * d1;
            d2 = d2 * d2;
            d3 = d3 * d3;
            d4 = d4 * d4;
            d5 = d5 * d5;
            d6 = d6 * d6;
            //d7 = d7 * d7;
            //d8 = d8 * d8;
            //d9 = d9 * d9;
        }
        long t1 = System.currentTimeMillis();
        System.out.println(t1 - t0);
        System.out.println(d0 + d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9);
    }
}
