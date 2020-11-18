package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTestWebDriver {

	@Test
	public void test() {
		UnitTestDriver obj1 = new UnitTestDriver();
		String salida = obj1.Testeo();
		
		assertEquals(" Salimos de la busqueda del Perú ",salida);
	}

}
