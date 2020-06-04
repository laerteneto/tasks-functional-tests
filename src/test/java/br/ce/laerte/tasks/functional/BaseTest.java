package br.ce.laerte.tasks.functional;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.TestName;

import br.ce.laerte.tasks.core.DriverFactory;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class BaseTest {

	@Rule
	public TestName testName = new TestName(); // obter parametros do test para utilizar no screenshot depois

	@AfterClass
	public static void finalizarClasse() {
		DriverFactory.killDriver();
	}

	@After
	public void tearDown() {
		gerarScreenShot();
		DriverFactory.killDriver();
	}

	public void gerarScreenShot() {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(DriverFactory.getDriver());
		try {
			ImageIO.write(screenshot.getImage(), "jpg", new File(
					System.getProperty("user.dir") + "/target/screnshoots/" + testName.getMethodName() + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
