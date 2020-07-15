package br.ce.wcaquino.servicos;


import java.util.Date;

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
		Assert.assertTrue(obj.getValor() == 8.50);
		Assert.assertTrue(DataUtils.isMesmaData(obj.getDataLocacao(), new Date()));
		Assert.assertTrue(DataUtils.isMesmaData(obj.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
	}
}
