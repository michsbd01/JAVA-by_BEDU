import java.util.*;
import java.util.stream.*;

public class ClinicaCalidad {

    public static void main(String[] args) {
        // Crear encuestas
        List<Encuesta> encuestasCentro = List.of(
            new Encuesta("Juan", "El tiempo de espera fue largo", 2),
            new Encuesta("Lucía", null, 4),
            new Encuesta("Pedro", "El personal fue amable", 5)
        );

        List<Encuesta> encuestasNorte = List.of(
            new Encuesta("Ana", "La atención fue buena, pero la limpieza puede mejorar", 3),
            new Encuesta("Carlos", null, 2),
            new Encuesta("Elena", "Me atendieron tarde", 3)
        );

        // Crear sucursales
        List<Sucursal> sucursales = List.of(
            new Sucursal("Centro", encuestasCentro),
            new Sucursal("Norte", encuestasNorte)
        );

        // Procesamiento con Stream API + flatMap + Optional
        List<String> mensajes = sucursales.stream()
            .flatMap(sucursal -> 
                sucursal.getEncuestas().stream()
                    .filter(e -> e.getCalificacion() <= 3)
                    .flatMap(encuesta -> 
                        encuesta.getComentario()
                            .map(com -> Stream.of("Sucursal " + sucursal.getNombre() 
                                + ": Seguimiento a paciente con comentario: \"" + com + "\""))
                            .orElseGet(Stream::empty)
                    )
            )
            .collect(Collectors.toList());

        // Mostrar resultados
        mensajes.forEach(System.out::println);
    }
}
