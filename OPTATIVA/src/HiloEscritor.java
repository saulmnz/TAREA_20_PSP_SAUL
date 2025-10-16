public class HiloEscritor extends Thread {
    private Buzon buzon;
    private String mensaje;

    public HiloEscritor(Buzon buzon, String mensaje) {
        this.buzon = buzon;
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        buzon.escribir(mensaje);
    }
}