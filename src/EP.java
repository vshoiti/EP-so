import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * EP 2 de Sistemas Operacionais
 */
public class EP {

    public static void main(String[] args) {

        // Bloco try/catch para guardar as linhas em um array        
        BufferedReader br;
        FileReader fr;
        String[] lines = new String[36242];
        int[] mutex = new int[36242];

        try{
            fr = new FileReader("../bd.txt");
            br = new BufferedReader(fr);
            
            String currentLine;
            int index = 0;

            while( (currentLine = br.readLine()) != null){
                lines[index] = currentLine;
                index++;
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        /*
        * Cria o array de threads aleatórias, cade índice tem 50% de chance de 
        * ser Reader ou Writer
        */
        Thread[] threadsArray = new Thread[100];
        for (int i = 0; i < 100; i++) {
            double decide = Math.random();
            if(decide>0.5) threadsArray[i] = new MyThread(new Reader(lines, mutex), "reader");
            else threadsArray[i] = new MyThread(new Writer(lines, mutex), "writer");
        }

        //RODANDO AS THREADS
        for (int i = 0; i < 100; i++) {
            threadsArray[i].start();

            try{
                threadsArray[i].sleep(1);
            }catch(InterruptedException e){}
        }

        //IMPRIME TODAS AS LINHAS.
        for (int i = 0; i < lines.length; i++) {
            System.out.println(lines[i]);
        }

    }
}

class Reader implements Runnable{

    String[] lines;
    int[] mutex;
    /**
     * No PDF: "Se o objeto for um leitor, ele deve somente ler a palavra na posição desejada,
     * armazenando-a em alguma variável local..."
     * A variável local pode ser dentro do Runnable??? (String read)
     */
    String read;

    Reader(String[] lines, int[] mutex){
        this.lines = lines;
        this.mutex = mutex;
    }

    public void run(){
        for (int i = 0; i < 100; i++) {
            int index = (int) (Math.random() * 36242);

            while(mutex[index] == 1){
                try {
                    System.out.println("==========================================================================================");
                    Thread.currentThread().sleep(1);    
                } catch (InterruptedException e) {
                    //TODO: handle exception
                }
            }
            mutex[index] = 1;
            this.read = this.lines[index];
            mutex[index] = 0;
        }
    }
}

class Writer implements Runnable{

    int[] mutex;
    String[] lines;

    Writer(String[] lines, int[] mutex){
        this.lines = lines;
        this.mutex = mutex;
    }

    public void run(){
        for (int i = 0; i < 100; i++) {
            int index = (int) (Math.random()*36242);

            while(mutex[index] == 1){
                try {
                    System.out.println("==========================================================================================");
                    Thread.currentThread().sleep(1);    
                } catch (InterruptedException e) {
                    //TODO: handle exception
                }
            }
            mutex[index] = 1;
            this.lines[index] = "MODIFICADO";
            mutex[index] = 0;
        }
    }
}
