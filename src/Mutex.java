/**
 * Mutex
 */
public class Mutex {

    boolean[] isAvailable;
    
    public Mutex(int[] isAvailable){
        this.isAvailable = isAvailable;
    }

    public void acquire(){

    }

    public void wait(int index){
        while(this.isAvailable[index] == 1){
            
        }
    }
    
}