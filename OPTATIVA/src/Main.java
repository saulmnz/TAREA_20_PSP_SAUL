public class Main {
    public static void main(String[] args) {
        Buzon buzon = new Buzon();

        // CREAMOS LOS HILOS ESCRITORES Y LECTORES.
        Thread hiloEscritor1 = new HiloEscritor(buzon, "SOY EL HILITO ESCRITOR 1 ;) ");
        Thread hiloLector1 = new HiloLector(buzon);
        Thread hiloEscritor2 = new HiloEscritor(buzon, "SOY EL HILASO ESCRITOR 2 ;( ");
        Thread hiloLector2 = new HiloLector(buzon);

        // PARA PROBAR LA SINCRONIZACIÓN UTILILZAMOS UN ORDEN DE LANZAMIENTO QUE CAUSE ESPERA.

        // EN UN INICIO EL BUZÓN ESTÁ VACÍO... ESTE HILO ESPERARÁ!!
        hiloLector1.start();

        // ESCRIBE EN EL BUZÓN Y DESPIERTA AL HILO LECTOR QUE ESTABA ESPERANDO.
        hiloEscritor1.start();

        // ESPERA A QUE OTRO ESCRITOR LLENE EL BUZÓN YA QUE EL ANTERIOR HILO LECTOR LO VACIÓ,
        hiloLector2.start();

        // ESCRIBE EL BUZÓN NOTIFICANDO Y DESPERTANDO AL LECTOR QUE ESPERA.
        hiloEscritor2.start();
    }
}