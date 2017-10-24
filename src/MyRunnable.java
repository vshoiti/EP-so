public abstract class MyRunnable implements Runnable{

    String[] lines;

    publoc void run();
}

class Reader extends MyRunnable{

    String[] lines;
    /**
     * No PDF: "Se o objeto for um leitor, ele deve somente ler a palavra na posição desejada,
     * armazenando-a em alguma variável local..."
     * A variável local pode ser dentro do Runnable??? (String read)
     */
    String read;

    Reader(String[] lines){
        this.lines = lines;
    }

    public void run(){
        synchronized(lines){
            for (int i = 0; i < 100; i++) {
                int index = (int) (Math.random() * 36242);
                this.read = this.lines[index];
            }
        }
    }
}
    
class Writer extends MyRunnable{

    String[] lines;

    Writer(String[] lines){
        this.lines = lines;
    }

    public void run(){

        synchronized(lines){
            for (int i = 0; i < 100; i++) {
                int index = (int) (Math.random()*36242);
                this.lines[index] = "MODIFICADO";
            }
        }
    }
}