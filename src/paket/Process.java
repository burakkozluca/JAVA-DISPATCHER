package paket;

public class Process {
    public int arrival_time;
    public int priority;
    public int process_time;
    public int id;
    public boolean bitti;
    public int baslangic;

    public Process(int arrival_time, int priority, int process_time, int id) {
        this.arrival_time = arrival_time;
        this.priority = priority;
        this.process_time= process_time;
        this.id = id;
        this.baslangic = process_time;
        this.bitti = false;
    }
    public int getId() {
        return id;
    }

    public boolean isDone()
    {
        return bitti;
    }
    public int getPriority() {
        return priority;
    }
    public int getTime() {
        return process_time;
    }



    public void run(int sistem) {
        if(getTime() == baslangic)
        {
            System.out.println("\033[32m" + sistem+   " sn proses başladı" + "       (id:" + id + "  öncelik:" + priority + "  kalan süre:" + process_time + "sn)" + "\033[0m");
            process_time--;
        }

        // İşlem süresi kadar bekle

        else if(getTime() != 0)
        {
            System.out.println("\033[33m"+ sistem+ " sn proses yürütülüyor" + "   (id:" + id + "  öncelik:" + priority + "  kalan süre:" + process_time + "sn)" + "\033[0m");
            process_time --;
        }

        else
        {
            System.out.println("\033[31m"+sistem +  " sn proses sonlandı" + "      (id:" + id + "  öncelik:" + priority + "  kalan süre:" + process_time + "sn)"+ "\033[0m");
            bitti = true;
        }
    }
}
