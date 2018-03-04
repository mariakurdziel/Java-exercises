package kolokwium;


import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.UnaryOperator;

public class Operator {
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);
    static double[] array;
    static void initArray(int size) {
        array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = Math.random() * size / (i + 1);
        }
    }

    /**
     * Oblicza średnią wartości elementów tablicy array uruchamiając równolegle działające wątki.
     * Wypisuje czasy operacji
     * @param cnt - liczba wątków
     */
    static void applyop(int cnt,UnaryOperator<Integer> unop) throws InterruptedException {
        // utwórz tablicę wątków
        OperatorCalc threads[] = new OperatorCalc[cnt];
        UnaryOperator<Integer> up=unop;

        int offset = array.length/cnt;

        for(int i=0; i<cnt; i++){
            threads[i] = new OperatorCalc(i*offset,(i+1)*offset);
        }
        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        // załóż, że array.length dzieli się przez cnt)
        double t1 = System.nanoTime()/1e6;
        //uruchom wątki
        for(int i=0; i<threads.length; i++) {
            threads[i].start();
        }
        double t2 = System.nanoTime()/1e6;
        // czekaj na ich zakończenie używając metody ''join''
        double max = array[0];
        for(int i=0; i<cnt; i++){
            double tmp = results.take();
            up.apply((int)array[i]);
        }

        // oblicz średnią ze średnich
        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f max=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                max);
    }

    public static void main(String[] args) {
        initArray(100000000);
        UnaryOperator<Integer> unop=i->i*i;

        try {
            applyop(4,unop);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class OperatorCalc extends Thread {
        private final int start;
        private final int end;
        double max;

        OperatorCalc(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run(UnaryOperator<Integer> up) {
            for (int i = start + 1; i < end; i++) {
                up.apply((int)array[i]);
            }
            System.out.printf(Locale.US, "%d-%d max=%f\n", start, end, max);
            try {
                results.put(this.max);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
