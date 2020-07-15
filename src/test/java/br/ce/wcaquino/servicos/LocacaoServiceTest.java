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

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {

	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Test
	public void teste() {
	
		//Cenario
		Usuario usuario = new Usuario("Maicon");
		Filme filme = new Filme("De volta para o futuro", 6, 8.50);
		LocacaoService ls = new LocacaoService();
		
		//Acao
		Locacao obj = ls.alugarFilme(usuario, filme);
		
		//Verificacao
		error.checkThat(obj.getValor(), is(equalTo(8.50)));
		
		error.checkThat(isMesmaData(obj.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(obj.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
	}
}
