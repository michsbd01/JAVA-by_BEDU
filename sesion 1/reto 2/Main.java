import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<MaterialCurso> materiales = new ArrayList<>();
        materiales.add(new Video("Introducción a Java", "Mario", 15));
        materiales.add(new Video("POO en Java", "Carlos", 20));
        materiales.add(new Articulo("Historia de Java", "Ana", 1200));
        materiales.add(new Articulo("Tipos de datos", "Luis", 800));
        materiales.add(new Ejercicio("Variables y tipos", "Luis"));
        materiales.add(new Ejercicio("Condicionales", "Mario"));

        PlataformaEducativa.mostrarMateriales(materiales);

        // Filtrar videos para contar duración
        List<Video> videos = new ArrayList<>();
       for (MaterialCurso mat : materiales) {
    if (mat instanceof Ejercicio ejercicio) {
        ejercicio.marcarRevisado();
    }
}

        PlataformaEducativa.contarDuracionVideos(videos);

        // Marcar ejercicios como revisados
        PlataformaEducativa.marcarEjerciciosRevisados(materiales);

        // Filtro por autor usando Predicate
        PlataformaEducativa.filtrarPorAutor(materiales, m -> m.autor.equals("Mario"));
    }
}
