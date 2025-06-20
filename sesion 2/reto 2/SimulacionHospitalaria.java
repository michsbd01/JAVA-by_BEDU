import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulacionHospitalaria {
    public static void main(String[] args) {
        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        // Lista de profesionales
        String[] nombres = {
            "Dra. Sánchez", "Dr. Gómez", "Enfermera López", "Dr. Rivera", "Enfermero Torres"
        };

        // Executor con 4 hilos
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Crear y ejecutar tareas
        for (String nombre : nombres) {
            ProfesionalMedico profesional = new ProfesionalMedico(nombre, salaCirugia);
            executor.submit(profesional);
        }

        executor.shutdown(); // Cierra el pool
    }
}
