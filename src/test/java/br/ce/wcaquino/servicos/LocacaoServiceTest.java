package br.ce.wcaquino.servicos;


import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {

	@Test
	public void teste() {
	
		//Cenario
		Usuario usuario = new Usuario("Maicon");
		Filme filme = new Filme("De volta para o futuro", 6, 8.50);
		LocacaoService ls = new LocacaoService();
		
		//Acao
		Locacao obj = ls.alugarFilme(usuario, filme);
		
		//Verificacao
		assertThat(obj.getValor(), CoreMatchers.is(CoreMatchers.equalTo(8.50)));
		assertThat(obj.getValor(), is(not(8.10)));
		
		Assert.assertThat(DataUtils.isMesmaData(obj.getDataLocacao(), new Date()), is(true));
		assertThat(isMesmaData(obj.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
	}
}
