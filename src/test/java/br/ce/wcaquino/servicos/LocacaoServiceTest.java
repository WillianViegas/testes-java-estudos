package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hamcrest.CoreMatchers;
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
	public void DeveAlugarFilme() throws Exception {

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
	public void naoDeveAlugarFilmeSemEstoque() throws Exception {
		
		// Cenario
		Usuario usuario = new Usuario("Maicon");
		List<Filme> filmes = Arrays.asList(new Filme("De volta para o futuro", 0, 8.50));
		
		// Acao
		ls.alugarFilme(usuario, filmes);
		
	}
	
	//robusta (mais ideal)
	@Test
	public void naoDeveAlugarFilmeSemUsuario() {
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
	public void naoDeveAlugarFilmeSemFilme() throws LocadoraException {
		//cenario
		Usuario usuario = new Usuario("Maicon");
		
		exception.expect(LocadoraException.class);
		
		//acao
			ls.alugarFilme(usuario, null);
	}
	
	@Test
	public void devePagar75PctNoFilme3() throws LocadoraException {
		//cenario
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.00), 
				new Filme("Filme 2", 2, 4.00), new Filme("Filme 3", 2, 4.00));
		
		//acao
		Locacao resultado = ls.alugarFilme(usuario, filmes);
	
		//verificacao
		//4 + 4 + 3 = 11
		Assert.assertThat(resultado.getValor(), CoreMatchers.is(11.0));
	}
	
	@Test
	public void devePagar50PctNoFilme4() throws LocadoraException {
		//cenario
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.00), 
				new Filme("Filme 2", 2, 4.00), new Filme("Filme 3", 2, 4.00), 
				new Filme("Filme 4", 2, 4.00));
		
		//acao
		Locacao resultado = ls.alugarFilme(usuario, filmes);
		
		//verificacao
		// 4 + 4 + 3 + 2 = 13
		Assert.assertThat(resultado.getValor(), CoreMatchers.is(13.00));
	}
	
	@Test
	public void devePagar25PctNoFilme5() throws LocadoraException {
		//cenario
		Usuario usuario = new Usuario("usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0),
				new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0), 
				new Filme("Filme 4", 2, 4.0), new Filme("Filme 5", 2, 4.0));
		
		//acao
		Locacao resultado = ls.alugarFilme(usuario, filmes);
		
		//verificacao
		//4 + 4 + 3 + 2 + 1 = 14
		Assert.assertThat(resultado.getValor(), CoreMatchers.is(14.00));
	}
	
	@Test
	public void devePagar0PctNoFilme6() throws LocadoraException {
		//cenario
		Usuario usuario = new Usuario("usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0),
				new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0), 
				new Filme("Filme 4", 2, 4.0), new Filme("Filme 5", 2, 4.0), 
				new Filme("Filme 5", 2, 4.0));
		
		//acao
		Locacao resultado = ls.alugarFilme(usuario, filmes);
		
		//verificacao
		//4 + 4 + 3 + 2 + 1 + 0 = 14
		Assert.assertThat(resultado.getValor(), CoreMatchers.is(14.00));
	}
}
