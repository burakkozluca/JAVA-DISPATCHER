//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package paket;

public class Process {
    public int arrival_time;
    public int priority;
    public int process_time;
    public int id;
    public int baslangic;
    public int suspend_time;

    public Process(int var1, int var2, int var3, int var4) {
        this.arrival_time = var1;
        this.priority = var2;
        this.process_time = var3;
        this.id = var4;
        this.baslangic = var3;
        this.suspend_time = var3;
    }

    public int getId() {
        return this.id;
    }

    public int getPriority() {
        return this.priority;
    }

    public int getTime() {
        return this.process_time;
    }

    public void run(int var1) {
        if (this.getTime() == this.baslangic) {
            System.out.println("\u001b[32m" + var1 + "sn proses başladı       (id:" + this.id + "  öncelik:" + this.priority + "  kalan süre:" + this.process_time + "sn)\u001b[0m");
        } else if (this.getTime() != 0) {
            System.out.println("\u001b[33m" + var1 + "sn proses yürütülüyor   (id:" + this.id + "  öncelik:" + this.priority + "  kalan süre:" + this.process_time + "sn)\u001b[0m");
        }

    }

    public void remove(int var1) {
        System.out.println("\u001b[31m" + var1 + "sn proses sonlandı      (id:" + this.id + "  öncelik:" + this.priority + "  kalan süre:" + this.process_time + "sn)\u001b[0m");
    }

    public void suspend(int var1, int var2) {
        this.id = var2;
        System.out.println("\u001b[34m" + var1 + "sn proses askıda        (id:" + this.id + "  öncelik:" + this.priority + "  kalan süre:" + this.process_time + "sn)\u001b[0m");
        this.suspend_time = var1;
    }

    public void zaman_asimi(int var1, int var2) {
        System.out.println("\u001b[36m" + var1 + "sn proses ZamanAşımı    (id:" + this.id + "  öncelik:" + this.priority + "  kalan süre:" + this.process_time + "sn)\u001b[0m");
    }
}
