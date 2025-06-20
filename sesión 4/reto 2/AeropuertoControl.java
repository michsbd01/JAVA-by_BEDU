import java.util.concurrent.CompletableFuture;

public class AeropuertoControl {

    private final ServicioVerificacion servicio = new ServicioVerificacion();

    public void autorizarAterrizaje() {
        System.out.println("🛫 Verificando condiciones para aterrizaje...\n");

        CompletableFuture<Boolean> pista = servicio.verificarPista();
        CompletableFuture<Boolean> clima = servicio.verificarClima();
        CompletableFuture<Boolean> trafico = servicio.verificarTraficoAereo();
        CompletableFuture<Boolean> personal = servicio.verificarPersonalTierra();

        CompletableFuture.allOf(pista, clima, trafico, personal)
            .thenApply(v -> pista.join() && clima.join() && trafico.join() && personal.join())
            .thenAccept(resultado -> {
                if (resultado) {
                    System.out.println("\n🛬 Aterrizaje autorizado: todas las condiciones óptimas.");
                } else {
                    System.out.println("\n🚫 Aterrizaje denegado: condiciones no óptimas.");
                }
            })
            .exceptionally(ex -> {
                System.out.println("\n❌ Error en el sistema de control: " + ex.getMessage());
                return null;
            });
    }

    public static void main(String[] args) throws InterruptedException {
        AeropuertoControl control = new AeropuertoControl();
        control.autorizarAterrizaje();

        // Esperar a que terminen las tareas asincrónicas
        Thread.sleep(5000);
    }
}
