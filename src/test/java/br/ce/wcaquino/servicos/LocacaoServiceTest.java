package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
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

	private LocacaoService ls;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
		ls = new LocacaoService();
	}
	
	@Test
	public void teste() throws Exception {

		// Cenario
		Usuario usuario = new Usuario("Maicon");
		
		List<Filme> filmes = Arrays.asList(new Filme("De volta para o futuro", 2, 8.50));
		
		// Acao
		Locacao obj = ls.alugarFilme(usuario, filmes);

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
		List<Filme> filmes = Arrays.asList(new Filme("De volta para o futuro", 0, 8.50));
		
		// Acao
		ls.alugarFilme(usuario, filmes);
		
	}
	
	//robusta (mais ideal)
	@Test
	public void testLocacao_usuarioVazio() {
		//cenario
		List<Filme> filmes = Arrays.asList(new Filme("De volta para o futuro", 2, 8.50));

		//acao
		try {
			ls.alugarFilme(null, filmes);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
			
		}
	}
	
	//forma nova
	@Test
	public void tesLocacao_filmeVazio() throws LocadoraException {
		//cenario
		Usuario usuario = new Usuario("Maicon");
		
		exception.expect(LocadoraException.class);
		
		//acao
			ls.alugarFilme(usuario, null);
	}
}
