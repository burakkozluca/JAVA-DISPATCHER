public class FCFS {
    private List<Process> queue1;

    public FCFS(List<Process> queue1) {
        this.queue1 = queue1;
    }

    public void run() {
        // İşlemlerin listesi döngüsü
        for (Process process : queue1) {
            // İşlemin çalıştırılması
            process.run();
        }
    }
}
