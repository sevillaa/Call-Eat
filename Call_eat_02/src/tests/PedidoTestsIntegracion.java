package tests;

//src/test/java/Integracion/DAOPedidoImpTest.java

import Negocio.TransferPedido;
import org.junit.jupiter.api.*;

import Integracion.DAOPedidoImp;

import java.io.File;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTestsIntegracion {

 private DAOPedidoImp dao;
 private TransferPedido pedido;
 private static final File FILE = new File("pedidos.json");

 @BeforeEach
 void setUp() {
     // Eliminar archivo previo para partir de cero
     if (FILE.exists()) {
         FILE.delete();
     }
     dao = new DAOPedidoImp();
     pedido = new TransferPedido(
             "test123",
             new Date(),
             List.of(),
             true,
             true,
             "direccion",
             "notas",
             false,
             "mesa1"
     );
 }

 @Test
 void crearYBuscarPedido() {
     assertTrue(dao.crearPedido(pedido), "crearPedido debería devolver true");
     TransferPedido bus = dao.buscarPedido("test123");
     assertNotNull(bus, "buscarPedido no debería devolver null tras crear");
     assertEquals("test123", bus.getId(), "El ID debe coincidir");
 }

 @Test
 void eliminarPedido() {
     dao.crearPedido(pedido);
     assertTrue(dao.eliminarPedido(pedido), "eliminarPedido debería devolver true");
     assertNull(dao.buscarPedido("test123"), "buscarPedido debería ser null tras eliminar");
 }


 @Test
 void modificarPedido() {
     dao.crearPedido(pedido);
     pedido.setNotas("nuevas notas");
     assertTrue(dao.modificarPedido(pedido), "modificarPedido debería devolver true");
     TransferPedido mod = dao.buscarPedido("test123");
     assertEquals("nuevas notas", mod.getNotas(), "Las notas debieron actualizarse");
 }

 @AfterEach
 void tearDown() {
     // Limpiar al final
     if (FILE.exists()) {
         FILE.delete();
     }
 }
}

