import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ServicioVerificacion {

    public CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            esperar(2, 3);
            boolean disponible = probabilidad(80);
            System.out.println("🛣️ Pista disponible: " + disponible);
            return disponible;
        });
    }

    public CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            esperar(2, 3);
            boolean favorable = probabilidad(85);
            System.out.println("🌦️ Clima favorable: " + favorable);
            return favorable;
        });
    }

    public CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            esperar(2, 3);
            boolean despejado = probabilidad(90);
            System.out.println("🚦 Tráfico aéreo despejado: " + despejado);
            return despejado;
        });
    }

    public CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            esperar(2, 3);
            boolean disponible = probabilidad(95);
            System.out.println("👷‍♂️ Personal disponible: " + disponible);
            return disponible;
        });
    }

    private void esperar(int min, int max) {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(min, max + 1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean probabilidad(int porcentaje) {
        return ThreadLocalRandom.current().nextInt(100) < porcentaje;
    }
}
