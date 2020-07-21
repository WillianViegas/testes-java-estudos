package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.builders.FilmeBuilder.umFilme;
import static br.ce.wcaquino.builders.UsuarioBuilder.umUsuario;
import static br.ce.wcaquino.matchers.MatchersProprios.caiNumaSegunda;
import static br.ce.wcaquino.matchers.MatchersProprios.ehHoje;
import static br.ce.wcaquino.matchers.MatchersProprios.ehHojeComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Assume;
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
import buildermaster.BuilderMaster;

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
		
		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

		// Cenario
		Usuario usuario = umUsuario().agora();
		
		List<Filme> filmes = Arrays.asList(umFilme().comValor(5.0).agora());
		
		// Acao
		Locacao obj = ls.alugarFilme(usuario, filmes);

		// Verificacao
		error.checkThat(obj.getValor(), is(equalTo(5.0)));
		error.checkThat(obj.getDataLocacao(), ehHoje());
		error.checkThat(obj.getDataRetorno(), ehHojeComDiferencaDias(1));
	
	}
	
	// elegante
	@Test(expected=FilmeSemEstoqueException.class)
	public void naoDeveAlugarFilmeSemEstoque() throws Exception {
		
		// Cenario
		Usuario usuario = umUsuario().agora();
		List<Filme> filmes = Arrays.asList(umFilme().semEstoque().agora());
		
		// Acao
		ls.alugarFilme(usuario, filmes);
		
	}
	
	//robusta (mais ideal)
	@Test
	public void naoDeveAlugarFilmeSemUsuario() {
		//cenario
		List<Filme> filmes = Arrays.asList(umFilme().agora());

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
		Usuario usuario = umUsuario().agora();
		
		exception.expect(LocadoraException.class);
		
		//acao
			ls.alugarFilme(usuario, null);
	}
	
	@Test
	//@Ignore pula o teste na cadeia de testes
	public void deveDevolverNaSegundaAoAlugarNoSabado() throws LocadoraException {
		
		//Cria uma condição para o teste ser verificado, se n atender ele é pulado
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		//cenario
		Usuario usuario = umUsuario().agora();
		List<Filme> filmes = Arrays.asList(umFilme().agora());
		
		//acao
		Locacao retorno = ls.alugarFilme(usuario, filmes);
		
		//verificacao
		//assertThat(retorno.getDataRetorno(), new DiaSemanaMatcher(Calendar.MONDAY));
	    //assertThat(retorno.getDataRetorno(), caiEm(Calendar.MONDAY));
		assertThat(retorno.getDataRetorno(), caiNumaSegunda());
	}
	
	public static void main(String[] args) {
		new BuilderMaster().gerarCodigoClasse(Locacao.class);
	}
}
