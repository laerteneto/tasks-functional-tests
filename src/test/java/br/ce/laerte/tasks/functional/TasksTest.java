package br.ce.laerte.tasks.functional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.laerte.tasks.page.BasePage;

public class TasksTest extends BaseTest {

	BasePage page = new BasePage();

	@Test
	public void deveSalvarTarefaComSucesso() {
		page.clicar(By.id("addTodo"));
		page.escrever(By.id("task"), "Teste via Selenium");
		page.escrever(By.id("dueDate"), "10/10/2030");

		page.clicar(By.id("saveButton"));

		Assert.assertEquals("Success!", page.obterTexto(By.id("message")));
	}

	@Test
	public void naoDeveSalvarTarefaDataPassada() {
		page.clicar(By.id("addTodo"));
		page.escrever(By.id("task"), "Teste via Selenium");
		page.escrever(By.id("dueDate"), "10/10/2010");

		page.clicar(By.id("saveButton"));

		Assert.assertEquals("Due date must not be in past", page.obterTexto(By.id("message")));

	}

	@Test
	public void naoDeveSalvarTarefaSemData() {
		page.clicar(By.id("addTodo"));
		page.escrever(By.id("task"), "Teste via Selenium");

		page.clicar(By.id("saveButton"));

		Assert.assertEquals("Fill the due date", page.obterTexto(By.id("message")));

	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		page.clicar(By.id("addTodo"));
		page.escrever(By.id("dueDate"), "10/10/2010");

		page.clicar(By.id("saveButton"));

		Assert.assertEquals("Fill the task description", page.obterTexto(By.id("message")));

	}
}
