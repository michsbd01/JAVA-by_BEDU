public class MovilidadApp {

    private final RutaService rutaService;
    private final TarifaService tarifaService;

    public MovilidadApp() {
        this.rutaService = new RutaService();
        this.tarifaService = new TarifaService();
    }

    public void procesarSolicitud() {
        rutaService.calcularRuta()
            .thenCombine(tarifaService.estimarTarifa(), (ruta, tarifa) ->
                "✅ 🚗 Ruta calculada: " + ruta + " | Tarifa estimada: $" + tarifa
            )
            .exceptionally(ex ->
                "❌ Error en el procesamiento: " + ex.getMessage()
            )
            .thenAccept(System.out::println);
    }

    public static void main(String[] args) throws InterruptedException {
        MovilidadApp app = new MovilidadApp();
        app.procesarSolicitud();

        // Espera lo suficiente para que se completen las tareas asincrónicas
        Thread.sleep(5000);
    }
}
