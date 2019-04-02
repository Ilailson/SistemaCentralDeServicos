package br.com.centralservicos.test;

import java.util.List;

import org.junit.Test;

import br.com.centralservicos.dao.PessoaDAO;
import br.com.centralservicos.domain.Pessoa;

public class PessoaDAOTest {
	
	public void salvar() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Ilailson Rocha");
		
		PessoaDAO dao = new PessoaDAO();
		
		dao.salvar(pessoa);
	}
	
	
	public void listar() {
		PessoaDAO dao = new PessoaDAO();
		List<Pessoa> resultado = dao.listar();
		
		System.out.println("Total de registros encontrados: "+resultado.size());
		for (Pessoa pessoa : resultado) {
			System.out.println(pessoa.getCodigo() + "- "+ pessoa.getNome());
		}
	}
	

	public void buscarPorCodigo() {
		PessoaDAO dao = new PessoaDAO();
		Pessoa pessoa = dao.buscarPorCodigo(1L);//pessoa  recebendo o código. 
		
		if (pessoa ==null) {//não tem dados
			System.out.println("Nenhum registro encontrado");
		} else {//tem dados
			System.out.println("Registro encontrado: ");
			System.out.println(pessoa.getCodigo()+" - " + pessoa.getNome());
		}
	}
	
	
	public void excluir() {
		Long codigo = 1L;
		PessoaDAO dao = new PessoaDAO();
		Pessoa pessoa = dao.buscarPorCodigo(codigo);
		
		if (pessoa == null) {
			System.out.println("Nenhum registro  encontrado");
		} else {
			dao.excluir(pessoa);
			System.out.println("Registro removido");
			System.out.println(pessoa.getCodigo()+" - " +pessoa.getNome() );
		}
	}
	
	@Test
	public void editar() {
		Long codigo = 3L;
		PessoaDAO dao = new PessoaDAO();
		Pessoa pessoa = dao.buscarPorCodigo(codigo);
		
		if (pessoa ==null) {//não tem dados.
			System.out.println("Nenhum registro encontrado");
		} else {//tem dados
			System.out.println("Registro editado - Antes: ");
			System.out.println(pessoa.getCodigo()+" - "+ pessoa.getNome());
			
			pessoa.setNome("Ilailson Castelhano Rocha");
			
			dao.editar(pessoa);
			
			System.out.println("Registro editado - Depois: ");
			System.out.println(pessoa.getCodigo()+" - "+ pessoa.getNome());
		}
	}
}
