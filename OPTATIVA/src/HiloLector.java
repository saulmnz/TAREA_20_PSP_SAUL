public class HiloLector extends Thread {
    private Buzon buzon;

    public HiloLector(Buzon buzon) {
        this.buzon = buzon;
    }

    @Override
    public void run() {
        buzon.leer();
    }
}