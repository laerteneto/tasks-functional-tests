package br.ce.laerte.tasks.functional;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import br.ce.laerte.tasks.core.DriverFactory;

public class BaseTest {

	@Rule
	public TestName testName = new TestName(); // obter parametros do test para utilizar no screenshot depois

	@Before
	public void setup() {
		DriverFactory.getDriver().navigate().to("http://192.168.0.178:8001/tasks/");
		// driver.manage().window().maximize();
	}

	@After
	public void tearDown() {
		DriverFactory.killDriver();
	}

}
