package br.com.centralservicos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.centralservicos.dao.PessoaDAO;
import br.com.centralservicos.dao.ProgramaDAO;
import br.com.centralservicos.domain.Pessoa;
import br.com.centralservicos.domain.Programa;

@ViewScoped
@ManagedBean
public class ProgramaBean implements Serializable {

	private static final long serialVersionUID = -4291493257114833355L;

	private Programa programa;
	private List<Programa> listarProgramas;
	private List<Programa> programaFiltrado;
	private List<Pessoa> listarPessoas;

	
	public List<Programa> getProgramaFiltrado() {
		return programaFiltrado;
	}

	public void setProgramaFiltrado(List<Programa> programaFiltrado) {
		this.programaFiltrado = programaFiltrado;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public List<Programa> getListarProgramas() {
		return listarProgramas;
	}

	public void setListarProgramas(List<Programa> listarProgramas) {
		this.listarProgramas = listarProgramas;
	}

	public List<Pessoa> getListarPessoas() {
		return listarPessoas;
	}

	public void setListarPessoas(List<Pessoa> listarPessoas) {
		this.listarPessoas = listarPessoas;
	}

	/*++++++++++++++++++++++++++++OPERACIONAL++++++++++++++++++++++++++++++++++++++++++++*/

	@PostConstruct
	public void listar() {
		try {

			ProgramaDAO dao = new ProgramaDAO();
			listarProgramas=dao.listar();
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao listar");
		}

	}

	public void novo() {
		programa = new Programa();
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		listarPessoas=pessoaDAO.listar();//Listando todas as pessoas que tem no banco de dados
	}

	public void salvar() {
		try {
			ProgramaDAO dao = new ProgramaDAO();
			dao.merge(programa);

			programa = new Programa();

			PessoaDAO pessoaDAO = new PessoaDAO();
			listarPessoas = pessoaDAO.listar();

			listarProgramas = dao.listar();

			Messages.addGlobalInfo("Salvo com sucesso");

		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao tentar salvar");
		}

	}

	public void editar(ActionEvent evento) {
		try {

			programa = (Programa) evento.getComponent().getAttributes().get("programaSelecionado");

			PessoaDAO pessoaDAO = new PessoaDAO();
			listarPessoas = pessoaDAO.listar();

		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Erro ao  tentar Editar");
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			programa = (Programa) evento.getComponent().getAttributes().get("programaSelecionado");
			
			ProgramaDAO dao = new ProgramaDAO();
			dao.excluir(programa);
			
			listarProgramas = dao.listar();
			
			Messages.addFlashGlobalInfo("Removido com sucesso");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Erro ao  tentar Excluir");
		}
	}
}
