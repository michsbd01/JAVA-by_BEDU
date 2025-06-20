import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProcesadorPedido {
    public static void main(String[] args) {
        List<Pedido> pedidos = Arrays.asList(
            new Pedido("Carlos", "domicilio", "555-1234"),
            new Pedido("Ana", "local", "555-0000"),
            new Pedido("Luis", "domicilio", null),
            new Pedido("María", "domicilio", "555-5678"),
            new Pedido("Pedro", "local", null)
        );

        pedidos.stream()
            .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio"))
            .map(Pedido::getTelefono)
            .flatMap(Optional::stream)
            .map(tel -> "📞 Confirmación enviada al número: " + tel)
            .forEach(System.out::println);
    }
}
