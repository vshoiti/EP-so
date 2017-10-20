public class MyThread extends Thread{
    Runnable obj;

    public MyThread(Runnable obj, String name){
        super(name);
        this.obj = obj;
    }

    public void run(){
        obj.run();
    }
}
