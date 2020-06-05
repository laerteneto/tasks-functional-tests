package br.ce.laerte.tasks.functional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.TestName;

import br.ce.laerte.tasks.core.DriverFactory;

public class BaseTest {

	@Rule
	public TestName testName = new TestName(); // obter parametros do test para utilizar no screenshot depois

	@AfterClass
	public static void finalizarClasse() {
		DriverFactory.killDriver();
	}

	@After
	public void tearDown() {
		DriverFactory.killDriver();
	}

}
