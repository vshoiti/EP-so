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

        try{
            fr = new FileReader("../bd.txt");
            br = new BufferedReader(fr);
            
            String currentLine;
            String[] lines = new String[36242];
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
            if(decide>0.5) threadsArray[i] = new Reader();
            else threadsArray[i] = new Writer();
        }

    }
}