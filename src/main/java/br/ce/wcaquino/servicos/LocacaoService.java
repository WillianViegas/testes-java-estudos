package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;
import java.util.List;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, List<Filme> filme) throws LocadoraException {
		
		if(usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}
		
		if(filme == null || filme.isEmpty()) {
			throw new LocadoraException("Filme vazio");
		}
		
		for(Filme x : filme) {
			if(x.getEstoque() == 0) {
				throw new FilmeSemEstoqueException();
			}
		}
	
		double valorLocacao = 0.0;
		
		Locacao locacao = new Locacao();
		locacao.setFilmes(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		
		for(Filme x : filme) {
			valorLocacao += x.getPrecoLocacao();
		}
		
		locacao.setValor(valorLocacao);

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}
}