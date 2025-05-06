package tests;

//src/test/java/Negocio/SAMesaImpTest.java

import org.junit.jupiter.api.*;

import Negocio.SAMesaImp;
import Negocio.TransferMesa;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SAMesaImpTest {

 private SAMesaImp saMesa;
 private TransferMesa mesa;
 private static final File FILE = new File("mesa.json");

 @BeforeEach
 void setUp() {
     if (FILE.exists()) FILE.delete();
     saMesa = new SAMesaImp();
     mesa = new TransferMesa("mesaSA", 4);
 }

 @Test
 void crearMesa_Valida() {
     assertTrue(saMesa.crearMesa(mesa));
 }

 @Test
 void crearMesa_CapacidadInvalida() {
     mesa.setCapacidad(0);
     assertFalse(saMesa.crearMesa(mesa));
 }

 @Test
 void eliminarMesa_NoReservada() {
     saMesa.crearMesa(mesa);
     assertTrue(saMesa.eliminarMesa(mesa));
 }

 @Test
 void eliminarMesa_Reservada() {
     mesa.setReservada(true);
     saMesa.crearMesa(mesa);
     assertFalse(saMesa.eliminarMesa(mesa));
 }

 @Test
 void modificarMesa_Valida() {
     saMesa.crearMesa(mesa);
     mesa.setCapacidad(6);
     assertTrue(saMesa.modificarMesa(mesa));
 }

 @Test
 void modificarMesa_Invalida() {
     mesa.setCapacidad(0);
     assertFalse(saMesa.modificarMesa(mesa));
 }

 @Test
 void buscarYListarMesas() {
     saMesa.crearMesa(mesa);
     TransferMesa buscada = saMesa.buscarMesa("mesaSA");
     assertNotNull(buscada);
     assertEquals("mesaSA", buscada.getId());

     List<TransferMesa> lista = saMesa.obtenerMesas();
     assertTrue(lista.size() >= 1);
 }

 @AfterEach
 void tearDown() {
     if (FILE.exists()) FILE.delete();
 }
}

