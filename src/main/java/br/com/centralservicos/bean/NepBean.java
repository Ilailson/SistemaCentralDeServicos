package br.com.centralservicos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.centralservicos.dao.NepDAO;
import br.com.centralservicos.dao.PessoaDAO;
import br.com.centralservicos.dao.ProgramaDAO;
import br.com.centralservicos.domain.Nep;
import br.com.centralservicos.domain.Pessoa;
import br.com.centralservicos.domain.Programa;

@ManagedBean
@ViewScoped
public class NepBean implements Serializable{

	private static final long serialVersionUID = -1382407175578273906L;
	
	private Nep nep;
	private List<Nep> listarNep;
	private List<Nep> nepFiltrado;
	private List<Pessoa> listarPessoas;
	private List<Programa> listarPrograma;
	public Nep getNep() {
		
		return nep;
	}
	public void setNep(Nep nep) {
		this.nep = nep;
	}
	public List<Nep> getListarNep() {
		return listarNep;
	}
	public void setListarNep(List<Nep> listarNep) {
		this.listarNep = listarNep;
	}
	public List<Pessoa> getListarPessoas() {
		return listarPessoas;
	}
	public void setListarPessoas(List<Pessoa> listarPessoas) {
		this.listarPessoas = listarPessoas;
	}
	public List<Programa> getListarPrograma() {
		return listarPrograma;
	}
	public void setListarPrograma(List<Programa> listarPrograma) {
		this.listarPrograma = listarPrograma;
	}
	
	
	public List<Nep> getNepFiltrado() {
		return nepFiltrado;
	}
	public void setNepFiltrado(List<Nep> nepFiltrado) {
		this.nepFiltrado = nepFiltrado;
	}
	
/*++++++++++++++++++++++++++++++++++OPERACIONAL++++++++++++++++++++++++++++++++++++++++++++++++++++*/	

	
	@PostConstruct
	public void listar() {
		NepDAO dao = new NepDAO();
		listarNep = dao.listar();
	}
	
	public void novo() {
		nep = new Nep();
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		listarPessoas = pessoaDAO.listar();
		
		ProgramaDAO programaDAO = new ProgramaDAO();
		listarPrograma = programaDAO.listar();
	}
	
	public void salvar() {
		try {
			NepDAO dao = new NepDAO();
			dao.merge(nep);
			
			nep = new Nep();
			
			
			PessoaDAO  pessoaDAO = new PessoaDAO();
			listarPessoas = pessoaDAO.listar();
			
			ProgramaDAO programaDAO = new ProgramaDAO();
			listarPrograma = programaDAO.listar();
			
			listarNep = dao.listar();
			
			Messages.addGlobalInfo("Salvo com sucesso");
			
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao tentar salvar");
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			nep = (Nep) evento.getComponent().getAttributes().get("nepSelecionado");
			
			PessoaDAO  pessoaDAO = new PessoaDAO();
			listarPessoas = pessoaDAO.listar();
			
			ProgramaDAO programaDAO = new ProgramaDAO();
			listarPrograma = programaDAO.listar();
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao tentar salvar");
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			nep = (Nep) evento.getComponent().getAttributes().get("nepSelecionado");
			
			NepDAO dao = new NepDAO();
			dao.excluir(nep);
			
			listarNep = dao.listar();
			
			Messages.addGlobalInfo("Excluido com sucesso");
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao tentar Excluir");
		}
	}
	

}
