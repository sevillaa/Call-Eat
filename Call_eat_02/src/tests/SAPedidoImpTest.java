package tests;


import org.junit.jupiter.api.*;

import Negocio.SAPedidoImp;
import Negocio.TransferPedido;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SAPedidoImpTest {

    private SAPedidoImp sa;
    private TransferPedido pedido;
    private static final File FILE = new File("pedidos.json");

    @BeforeEach
    void setUp() {
        limpiarArchivoPedidos();
        sa = new SAPedidoImp();
        pedido = new TransferPedido(
                "sa123",
                new Date(),
                new ArrayList<>(),
                true,
                true,
                "dirSA",
                "notasSA",
                false,
                "mesaSA"
        );
    }

    @Test
    void crearYBuscarPedido_Integracion() {
        assertTrue(sa.crearPedido(pedido));
        TransferPedido encontrado = sa.buscarPedido("sa123");
        assertNotNull(encontrado);
        assertEquals("sa123", encontrado.getId());
    }

    @Test
    void eliminarPedido_Integracion() {
        sa.crearPedido(pedido);
        assertTrue(sa.eliminarPedido(pedido));
        assertNull(sa.buscarPedido("sa123"));
    }

    @Test
    void listaPedidos_Integracion() throws InterruptedException {
        Date inicio = new Date();
        Thread.sleep(10); // asegurar timestamp diferente
        sa.crearPedido(pedido);
        List<TransferPedido> list = sa.listaPedidos(inicio, new Date());
        assertFalse(list.isEmpty());
    }

    @Test
    void modificarPedido_NullAndIdNull() {
        assertFalse(sa.modificarPedido(null));
        TransferPedido sinId = new TransferPedido();
        sinId.setId(null);
        assertFalse(sa.modificarPedido(sinId));
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
