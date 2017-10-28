import java.util.concurrent.Semaphore;

/*
 * Reader / Writer implementation in which only the writers lock the whole database
 **/

public class SemaphoreReader extends MyRunnable2{

    private final Semaphore readersInside;

    /**
     * No PDF: "Se o objeto for um leitor, ele deve somente ler a palavra na posição desejada,
     * armazenando-a em alguma variável local..."
     * A variável local pode ser dentro do Runnable??? (String read)
     */
    private String read;

    public SemaphoreReader(String[] lines, Semaphore readersInside, Semaphore writeMutex){
        this.lines = lines;
        this.readersInside = readersInside;
        this.writeMutex = writeMutex;
    }

    public void run(){

    	readersSemaphoreUp();

    	try {
    		
    		for (int i = 0; i < 100; i++) { // simulates 100 accesses
        		int index = (int) (Math.random() * 36242);
        		this.read = this.lines[index];             
            }
    		
            sleep(1); // simulates the validation period as specified

    	} finally {

			readersSemaphoreDown();

		}
    }

    private void readersSemaphoreUp () {
        synchronized (readersInside) {

            if (readersInside.availablePermits() == 0) // if this is the first reader entering the database
                writeMutexDown(); // tries to lock out all writers or blocks all readers if there is a writer

            readersInside.release(); // increases the reader counter
        }
    }

    private void readersSemaphoreDown () {
        try {
            synchronized (readersInside) {

                readersInside.acquire(); // decreases the reader counter
                if (readersInside.availablePermits() == 0) // if this is the last reader leaving the database
                    writeMutexUp(); // allows writers to enter

            }

        } catch (InterruptedException e) { // interrupted during acquire()
            System.out.println("Thread interrupted. This shouldn't happen");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
