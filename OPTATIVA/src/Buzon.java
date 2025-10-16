/**
 * LA CLASE BUZÓN SERÁ EL RECURSO COMPARTIDO QUE CONSTARÁ DE LAS SIGUIENTE REGLAS ->

 *  1. SOLO PODRÁ CONTENER UN MENSAJE A LA VEZ.
 *  2. EL HILO ESCRITOR SOLO PODRÁ ESCRIBIR SI EL BUZÓN ESTÁ VACÍO.
 *  3. EL HILO LECTOR SOLO PODRÁ LEER SI EL BUZÓN ESTÁ LLENO.
 *  4. SI NO SE CUMPLEN LAS CONDICIONES EL HILO DEBERÁ ESPERAR.

 * PARA QUE ESTAS REGLAS SE LLEVEN A CABO, NO EXISTA UNA CONDICIOÓN DE CARRERA Y EL BUZÓN FUNCIONE CORRECTAMENTE, NECESITAREMOS HACER USO DE UNA SINCRONIZACIÓN CORRECTA.
 */
public class Buzon {

    /**
     * mensaje -> VARIABLE QUE HACE REFERENCIA AL TEXTO INTRODUCIDO.
     * lleno ->   BOOLEANO QUE SIRVE PARA SABER SI HAY MENSAJE O NO, SIENDO FALSE CUANDO EL BUZÓN ESTÉ VACÍO Y TRUE CUANDO ESTÉ LLENO.
     */
    private String mensaje;
    private boolean lleno = false;


    /**
     * MÉTODO PARA QUE UN HILO ESCRITOR DEJE UN MENSAJE, SOLO PODRÁ SUCEDER SI EL BUZÓN ESTÁ VACÍO, DE NO SER ASÍ ESTE HILO ESPERARÁ..
     */
    public synchronized void escribir(String msg) {
        // CONDICIÓN, SI EL BUZÓN ESTÁ LLENO, EL HILO DEBE ESPERAR.
        while (lleno) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // UNA VEZ CUMPLIDA LA CONDICIÓN ( lleno == false ), ESCRIBIMOS EL MENSAJE INDICADO EN EL BUZÓN
        this.mensaje = msg;
        this.lleno = true;
        System.out.println("UN HILO ESCRITOR DEJÓ UN MENSAJITO: " + msg);
        // AVISAMOS A TODOS LOS HILOS QUE ESTÁN ESPERANDO ( los hilos lectores )
        notifyAll();
    }

    /**
     * MÉTODO PARA QUE UN HILO LECTOR LEA UN MENSAJE, SOLO PODRÁ SUCEDER SI EL BUZÓN ESTÁ LLENO, DE NO SER ASÍ ESTE HILO ESPERARÁ..
     */
    public synchronized String leer() {
        // CONDICIÓN, SI EL BUZÓN ESTÁ VACÍO EL HILO DEBE ESPERAR
        while (!lleno) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // CON EL BUZÓN LLENO LEEMOS EL MENSAJE Y AL FINALIZAR LIBERAMOS EL BUZÓN
        String msg = this.mensaje;
        this.lleno = false;
        System.out.println("UN HILO LECTOR LEYÓ EL MENSAJE QUE HAY EN EL BUZÓN: " + msg +"\n");
        // AVISAMOS A LOS HILOS QUE ESPERAN ( hilos escritores )
        notifyAll();
        return msg;
    }
}