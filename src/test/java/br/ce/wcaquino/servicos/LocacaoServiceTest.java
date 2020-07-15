package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void teste() throws Exception {

		// Cenario
		Usuario usuario = new Usuario("Maicon");
		Filme filme = new Filme("De volta para o futuro", 2, 8.50);
		LocacaoService ls = new LocacaoService();

		// Acao
		Locacao obj = ls.alugarFilme(usuario, filme);

		// Verificacao
		error.checkThat(obj.getValor(), is(equalTo(8.50)));

		error.checkThat(isMesmaData(obj.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(obj.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
	}
	
	// elegante
	@Test(expected=Exception.class)
	public void testLocacao_filmeSemEstoque() throws Exception {
		// Cenario
		Usuario usuario = new Usuario("Maicon");
		Filme filme = new Filme("De volta para o futuro", 0, 8.50);
		LocacaoService ls = new LocacaoService();

		// Acao
		ls.alugarFilme(usuario, filme);
		
	}
	
	//robusta
	@Test
	public void testLocacao_filmeSemEstoque_2(){
		// Cenario
		Usuario usuario = new Usuario("Maicon");
		Filme filme = new Filme("De volta para o futuro", 0, 8.50);
		LocacaoService ls = new LocacaoService();

		// Acao
		try {
			ls.alugarFilme(usuario, filme);
			Assert.fail("Nao lancou excecao");
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Filme sem estoque"));
		}
	}
	
	// nova forma
	@Test
	public void testLocacao_filmeSemEstoque_3() throws Exception {
		// Cenario
		Usuario usuario = new Usuario("Maicon");
		Filme filme = new Filme("De volta para o futuro", 0, 8.50);
		LocacaoService ls = new LocacaoService();

		exception.expect(Exception.class);
		exception.expectMessage("Filme sem estoque");

		// Acao
		ls.alugarFilme(usuario, filme);
		
	}
}
