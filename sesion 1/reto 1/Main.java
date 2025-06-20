import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<OrdenMasa> ordenesMasa = Arrays.asList(
            new OrdenMasa("A123", 500),
            new OrdenMasa("A124", 750)
        );

        List<OrdenPersonalizada> ordenesPersonalizadas = Arrays.asList(
            new OrdenPersonalizada("P456", 100, "ClienteX"),
            new OrdenPersonalizada("P789", 150, "ClienteY")
        );

        List<OrdenPrototipo> ordenesPrototipo = Arrays.asList(
            new OrdenPrototipo("T789", 10, "Diseño"),
            new OrdenPrototipo("T790", 5, "Pruebas")
        );

        // Mostrar todas
        GestorOrdenes.mostrarOrdenes(ordenesMasa);
        GestorOrdenes.mostrarOrdenes(ordenesPersonalizadas);
        GestorOrdenes.mostrarOrdenes(ordenesPrototipo);

        // Procesar personalizadas
        GestorOrdenes.procesarPersonalizadas(new ArrayList<>(ordenesPersonalizadas), 200);

        // Unificar todas para conteo
        List<OrdenProduccion> todas = new ArrayList<>();
        todas.addAll(ordenesMasa);
        todas.addAll(ordenesPersonalizadas);
        todas.addAll(ordenesPrototipo);

        GestorOrdenes.contarOrdenes(todas);
    }
}
