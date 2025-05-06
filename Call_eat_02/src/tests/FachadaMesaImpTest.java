package tests;

//src/test/java/Negocio/FachadaMesaImpTest.java

import org.junit.jupiter.api.*;

import Negocio.FachadaMesaImp;
import Negocio.TransferMesa;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FachadaMesaImpTest {

 private FachadaMesaImp fachada;
 private TransferMesa mesa;
 private static final File FILE = new File("mesa.json");

 @BeforeEach
 void setUp() {
     if (FILE.exists()) FILE.delete();
     fachada = new FachadaMesaImp();
     mesa = new TransferMesa("mesaF1", 4);
 }

 @Test
 void crearYBuscarMesa() {
     assertTrue(fachada.crearMesa(mesa));
     TransferMesa buscada = fachada.buscarMesa("mesaF1");
     assertNotNull(buscada);
     assertEquals("mesaF1", buscada.getId());
 }

 @Test
 void eliminarMesa() {
     fachada.crearMesa(mesa);
     assertTrue(fachada.eliminarMesa(mesa));
     assertNull(fachada.buscarMesa("mesaF1"));
 }

 @Test
 void modificarMesa() {
     fachada.crearMesa(mesa);
     mesa.setCapacidad(10);
     assertTrue(fachada.modificarMesa(mesa));
     assertEquals(10, fachada.buscarMesa("mesaF1").getCapacidad());
 }

 @Test
 void listaMesas() {
     fachada.crearMesa(mesa);
     List<TransferMesa> mesas = fachada.listaMesas();
     assertFalse(mesas.isEmpty());
 }

 @AfterEach
 void tearDown() {
     if (FILE.exists()) FILE.delete();
 }
}

