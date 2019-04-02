package br.com.centralservicos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.centralservicos.dao.PessoaDAO;
import br.com.centralservicos.domain.Pessoa;

@ViewScoped
@ManagedBean
public class PessoaBean implements Serializable{

	private static final long serialVersionUID = 7624201396635082871L;
	private Pessoa pessoa;
	private List<Pessoa> listarPessoas;
	private List<Pessoa> pessoaFiltrada;
	
	
	public List<Pessoa> getPessoaFiltrada() {
		return pessoaFiltrada;
	}

	public void setPessoaFiltrada(List<Pessoa> pessoaFiltrada) {
		this.pessoaFiltrada = pessoaFiltrada;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getListarPessoas() {
		return listarPessoas;
	}

	public void setListarPessoas(List<Pessoa> listarPessoas) {
		this.listarPessoas = listarPessoas;
	}


/*++++++++++++++++++++++++++++++OPERACIONAL+++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
	@PostConstruct
	public void listar() {
		
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			listarPessoas = pessoaDAO.listar();
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro  ao tentar listar as pessoas");
		}
		
	}
	
	
public void novo() {
	try {
		pessoa = new Pessoa();
	} catch (RuntimeException e) {
		e.printStackTrace();
		Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova pessoa");
	}
	
}

public void editar(ActionEvent evento) {
	try {
		pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
		
		
	} catch (RuntimeException e) {
		e.printStackTrace();
		Messages.addGlobalError("Ocorreu um erro ao  tentar selecionar uma  pessoa");
	}
}

public void salvar() {
	try {
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.merge(pessoa);
		
		listarPessoas=pessoaDAO.listarOrdenacao("nome");
		
		pessoa = new Pessoa();
	} catch (RuntimeException e) {
		e.printStackTrace();
		Messages.addGlobalError("Ocorreu um erro ao tentar salvar a pessoa");
	}
}


public void excluir(ActionEvent evento) {
	try {
		pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
		
		PessoaDAO  pessoaDAO = new PessoaDAO();
		pessoaDAO.excluir(pessoa);
		
		listarPessoas = pessoaDAO.listar();//depois que excluir  atualiza a lista
		
		Messages.addGlobalInfo("Pessoa removida com sucesso");
	} catch (RuntimeException e) {
		e.printStackTrace();
		Messages.addGlobalError("Erro ao  tentar remover a pessoa:  "+e.getMessage());
	}
}


}
