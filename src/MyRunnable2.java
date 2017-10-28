import java.util.concurrent.Semaphore;

public abstract class MyRunnable2 extends MyRunnable {

    Semaphore writeMutex;

    void writeMutexDown () {
        try {

            writeMutex.acquire(); // tries lock all writers (if there is a writer this thread gets blocked)

        } catch (InterruptedException e) {
            System.out.println("Thread interrupted. This shouldn't happen");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    void writeMutexUp () {
        writeMutex.release();
    }

    void sleep (int milliseconds) {
        try {

            Thread.sleep(milliseconds);

        } catch (InterruptedException e) {
            System.out.println("Thread interrupted. This shouldn't happen");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
