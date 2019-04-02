package br.com.centralservicos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.centralservicos.dao.ProcessoDAO;
import br.com.centralservicos.domain.Processo;

@ManagedBean
@ViewScoped
public class ProcessoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Processo processo;
	private List<Processo> listarProcessos;
	private List<Processo> filtrarProcessos;
	
	public Processo getProcesso() {
		return processo;
	}
	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
	public List<Processo> getListarProcessos() {
		return listarProcessos;
	}
	public void setListarProcessos(List<Processo> listarProcessos) {
		this.listarProcessos = listarProcessos;
	}
	public List<Processo> getFiltrarProcessos() {
		return filtrarProcessos;
	}
	public void setFiltrarProcessos(List<Processo> filtrarProcessos) {
		this.filtrarProcessos = filtrarProcessos;
	}
	
/*+++++++++++++++++++++++++++++++++++++OPERACIONAL+++++++++++++++++++++++++++++++++++++++++++*/
	@PostConstruct
	public void listar() {
		ProcessoDAO dao = new ProcessoDAO();
		listarProcessos=dao.listar();
	}
	
	public void novo() {
		processo = new Processo();
	}
	
	public void salvar() {
		try {
			ProcessoDAO dao = new ProcessoDAO();
			dao.merge(processo);
			
			processo = new Processo();
			listarProcessos=dao.listarOrdenacao("processo");
			
			
			
			Messages.addGlobalInfo("Salvo com sucesso");
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao salvar");
		}
		
	}
	
	public void editar(ActionEvent evento) {
		processo = (Processo) evento.getComponent().getAttributes().get("processoSelecionado");
	}
	
	public void excluir(ActionEvent evento) {
		processo = (Processo) evento.getComponent().getAttributes().get("processoSelecionado");
		
		ProcessoDAO dao = new ProcessoDAO();
		dao.excluir(processo);
		
		listarProcessos = dao.listar();
	}
	
	
	
	
}
