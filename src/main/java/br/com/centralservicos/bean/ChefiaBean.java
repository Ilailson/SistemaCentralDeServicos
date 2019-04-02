package br.com.centralservicos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.centralservicos.dao.ChefiaDAO;
import br.com.centralservicos.dao.PessoaDAO;
import br.com.centralservicos.dao.SecaoDAO;
import br.com.centralservicos.domain.Chefia;
import br.com.centralservicos.domain.Pessoa;
import br.com.centralservicos.domain.Secao;

@SessionScoped
@ManagedBean
public class ChefiaBean implements Serializable {

	private static final long serialVersionUID = -7105970884413645749L;
	
	private Chefia chefia;
	private List<Chefia> listarChefias;
	private List<Chefia> chefiaFiltrada;
	private List<Secao> listarSecoes;
	private List<Pessoa> listarPessoas;
	
	
	public List<Chefia> getChefiaFiltrada() {
		return chefiaFiltrada;
	}
	public void setChefiaFiltrada(List<Chefia> chefiaFiltrada) {
		this.chefiaFiltrada = chefiaFiltrada;
	}
	public Chefia getChefia() {
		return chefia;
	}
	public void setChefia(Chefia chefia) {
		this.chefia = chefia;
	}
	public List<Chefia> getListarChefias() {
		return listarChefias;
	}
	public void setListarChefias(List<Chefia> listarChefias) {
		this.listarChefias = listarChefias;
	}
	
	
	
	
public List<Secao> getListarSecoes() {
		return listarSecoes;
	}
	public void setListarSecoes(List<Secao> listarSecoes) {
		this.listarSecoes = listarSecoes;
	}
	public List<Pessoa> getListarPessoas() {
		return listarPessoas;
	}
	public void setListarPessoas(List<Pessoa> listarPessoas) {
		this.listarPessoas = listarPessoas;
	}
/*+++++++++++++++++++++++++++++++++++OPERACIONAL++++++++++++++++++++++++++++++++++++++++++++*/
@PostConstruct
public void listar() {
	try {
		ChefiaDAO  chefiaDAO = new ChefiaDAO();
		listarChefias = chefiaDAO.listar();
		
	} catch (RuntimeException e) {
		e.printStackTrace();
		Messages.addGlobalError("Ocorreu um erro ao listar as chefias");
	}
}

public void novo() {
	try {
		chefia = new Chefia();
		
		SecaoDAO secaoDAO = new SecaoDAO();
		listarSecoes=secaoDAO.listar();//listar seções recebendo todas  as seçoes.
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		listarPessoas=pessoaDAO.listar();
	} catch (RuntimeException e) {
		e.printStackTrace();
		Messages.addGlobalError("Ocorreu um erro ao tentar gerar chefias");
	}
}

public void editar(ActionEvent evento) {
	try {
		chefia = (Chefia) evento.getComponent().getAttributes().get("chefiaSelecionado");
		
		SecaoDAO secaoDAO = new SecaoDAO();
		listarSecoes = secaoDAO.listar();
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		listarPessoas = pessoaDAO.listar();
		
	} catch (RuntimeException e) {
		e.printStackTrace();
		Messages.addGlobalError("Ocorreu  um erro ao  tentar  selecionar uma chefia");
	}
}

public  void  salvar() {
	try {
		ChefiaDAO dao = new ChefiaDAO();
		dao.merge(chefia);
		
		chefia = new Chefia();
		
		SecaoDAO secaoDAO = new SecaoDAO();
		listarSecoes = secaoDAO.listar();
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		listarPessoas=pessoaDAO.listar();//lista as pessoas
		
		listarChefias = dao.listar(); //atualiza  apos salvar
		
		Messages.addGlobalInfo("Salvo com sucesso");
	} catch (RuntimeException e) {
		e.printStackTrace();
		Messages.addGlobalError("Erro ao tentar salvar");
	}
}
	
	
	public void excluir(ActionEvent evento) {
		try {
			chefia = (Chefia) evento.getComponent().getAttributes().get("chefiaSelecionado");
			
			ChefiaDAO dao = new ChefiaDAO();
			dao.excluir(chefia);
			
			listarChefias = dao.listar();
			
			Messages.addGlobalInfo("Removido com sucesso");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	







}

