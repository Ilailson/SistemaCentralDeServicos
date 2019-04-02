package br.com.centralservicos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.centralservicos.dao.SecaoDAO;
import br.com.centralservicos.domain.Secao;
@ViewScoped
@ManagedBean
public class SecaoBean implements Serializable{

	private static final long serialVersionUID = 5605280292038279496L;
	private Secao secao;
	private List<Secao>listarSecoes;
	private List<Secao>secaoFiltrada;
	
	
	
	public Secao getSecao() {
		return secao;
	}
	public void setSecao(Secao secao) {
		this.secao = secao;
	}
	public List<Secao> getListarSecoes() {
		return listarSecoes;
	}
	public void setListarSecoes(List<Secao> listarSecoes) {
		this.listarSecoes = listarSecoes;
	}
	
	
	
public List<Secao> getSecaoFiltrada() {
		return secaoFiltrada;
	}
	public void setSecaoFiltrada(List<Secao> secaoFiltrada) {
		this.secaoFiltrada = secaoFiltrada;
	}
	/*+++++++++++++++++++++++++++OPERACIONAL++++++++++++++++++++++++++++++++++*/	
	@PostConstruct
	public void listar() {
		
		try {
			SecaoDAO secaoDAO = new SecaoDAO();
			listarSecoes = secaoDAO.listar();
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro  ao tentar listar as Seções");
		}
		
	}
	
	
public void novo() {
	try {
		secao = new Secao();
	} catch (RuntimeException e) {
		e.printStackTrace();
		Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova seção");
	}
	
}

public void editar(ActionEvent evento) {
	try {
		secao = (Secao) evento.getComponent().getAttributes().get("sessaoSelecionada");
		
		
	} catch (RuntimeException e) {
		e.printStackTrace();
		Messages.addGlobalError("Ocorreu um erro ao  tentar selecionar uma  seção");
	}
}

public void salvar() {
	try {
		SecaoDAO secaoDAO = new SecaoDAO();
		secaoDAO.merge(secao);
		
		listarSecoes = secaoDAO.listarOrdenacao("nome");
		
		secao = new Secao();
		
	} catch (RuntimeException e) {
		e.printStackTrace();
		Messages.addGlobalError("Ocorreu um erro ao tentar salvar a seção");
	}
}


public void excluir(ActionEvent evento) {
	try {
		secao = (Secao) evento.getComponent().getAttributes().get("sessaoSelecionada");
		
		SecaoDAO secaoDAO = new SecaoDAO();
		secaoDAO.excluir(secao);
		
		listarSecoes = secaoDAO.listar();//depois de  excluir atualiza a  lista.  
		
		Messages.addGlobalInfo("Pessoa removida com sucesso");
		
	} catch (RuntimeException e) {
		e.printStackTrace();
		Messages.addGlobalError("Erro ao  tentar remover a seção:  "+e.getMessage());
	}
}

}
