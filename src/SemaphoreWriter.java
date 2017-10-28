import java.util.concurrent.Semaphore;

/*
 * Reader / Writer implementation in which only the writers lock the whole database
 **/

public class SemaphoreWriter extends MyRunnable2 {

    public SemaphoreWriter(String[] lines, Semaphore readersInside, Semaphore writeMutex) {
        this.lines = lines;
        this.writeMutex = writeMutex;
    }

    public void run() {

        writeMutexDown(); // locks out all other writers and all readers or gets blocked

        try {

            for (int i = 0; i < 100; i++) { // simulates 100 accesses
                int index = (int) (Math.random() * 36242);
                this.lines[index] = "MODIFICADO";
            }

            sleep(1); // simulates the validation period as specified

        } finally {

            writeMutexUp(); // releases the database

        }
    }
}
