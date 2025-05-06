package tests;

import org.junit.jupiter.api.*;

import Negocio.FachadaPedidoImp;
import Negocio.TransferPedido;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FachadaPedidoImpTest {

    private FachadaPedidoImp fachada;
    private TransferPedido pedido;
    private static final File FILE = new File("pedidos.json");

    @BeforeEach
    void setUp() {
        limpiarArchivoPedidos();
        fachada = new FachadaPedidoImp();
        pedido = new TransferPedido(
                "fp123",
                new Date(),
                new ArrayList<>(),
                false,
                true,
                "dirF",
                "notasF",
                true,
                "mesaF"
        );
    }

    @Test
    void crearYBuscarPedido_ViaFachada() {
        assertTrue(fachada.crearPedido(pedido));
        TransferPedido buscado = fachada.buscarPedido("fp123");
        assertNotNull(buscado);
        assertEquals("fp123", buscado.getId());
    }

    @Test
    void eliminarPedido_ViaFachada() {
        fachada.crearPedido(pedido);
        assertTrue(fachada.eliminarPedido(pedido));
        assertNull(fachada.buscarPedido("fp123"));
    }

    @Test
    void listaPedidos_ViaFachada() throws InterruptedException {
        Date inicio = new Date();
        Thread.sleep(10); // Asegurar timestamps distintos
        fachada.crearPedido(pedido);
        List<TransferPedido> lista = fachada.listaPedidos(inicio, new Date());
        assertFalse(lista.isEmpty());
    }

    @Test
    void modificarPedido_ViaFachada() {
        fachada.crearPedido(pedido);
        pedido.setDireccion("nuevaDir");
        assertTrue(fachada.modificarPedido(pedido));
        TransferPedido mod = fachada.buscarPedido("fp123");
        assertEquals("nuevaDir", mod.getDireccion());
    }

    @AfterEach
    void tearDown() {
        limpiarArchivoPedidos();
    }

    private void limpiarArchivoPedidos() {
        try (FileWriter writer = new FileWriter(FILE)) {
            writer.write("[]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

