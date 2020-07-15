package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
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
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
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
	@Test(expected=FilmeSemEstoqueException.class)
	public void testLocacao_filmeSemEstoque() throws Exception {
		// Cenario
		Usuario usuario = new Usuario("Maicon");
		Filme filme = new Filme("De volta para o futuro", 0, 8.50);
		LocacaoService ls = new LocacaoService();

		// Acao
		ls.alugarFilme(usuario, filme);
		
	}
	
	//robusta (mais ideal)
	@Test
	public void testLocacao_usuarioVazio() {
		//cenario
		Filme filme = new Filme("De volta para o futuro", 1, 8.50);
		LocacaoService ls = new LocacaoService();
		//acao
		try {
			ls.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
			
		}
	}
	
	//forma nova
	@Test
	public void tesLocacao_filmeVazio() throws LocadoraException {
		//cenario
		LocacaoService ls = new LocacaoService();
		Usuario usuario = new Usuario("Maicon");
		
		exception.expect(LocadoraException.class);
		
		//acao
			ls.alugarFilme(usuario, null);
	}
}
