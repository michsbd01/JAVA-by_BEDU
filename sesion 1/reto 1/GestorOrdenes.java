import java.util.List;

public class GestorOrdenes {

    // Muestra cualquier tipo de orden (OrdenMasa, OrdenPersonalizada, OrdenPrototipo)
    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        System.out.println("\n📋 Órdenes registradas:");
        for (OrdenProduccion orden : lista) {
            orden.mostrarResumen();
        }
    }

    // Procesa solo las órdenes personalizadas (super permite operar con listas más generales)
    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        System.out.println("\n💰 Procesando órdenes personalizadas...");
        for (Object obj : lista) {
            if (obj instanceof OrdenPersonalizada personalizada) {
                personalizada.aplicarCostoAdicional(costoAdicional);
            }
        }
    }

    // Cuenta cuántas órdenes hay de cada tipo
    public static void contarOrdenes(List<OrdenProduccion> lista) {
        int masa = 0, personalizadas = 0, prototipos = 0;

        for (OrdenProduccion orden : lista) {
            if (orden instanceof OrdenMasa) {
                masa++;
            } else if (orden instanceof OrdenPersonalizada) {
                personalizadas++;
            } else if (orden instanceof OrdenPrototipo) {
                prototipos++;
            }
        }

        System.out.println("\n📊 Resumen total de órdenes:");
        System.out.println("🔧 Producción en masa: " + masa);
        System.out.println("🛠️ Personalizadas: " + personalizadas);
        System.out.println("🧪 Prototipos: " + prototipos);
    }
}
