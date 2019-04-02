package br.com.centralservicos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;

import br.com.centralservicos.dao.PessoaDAO;
import br.com.centralservicos.dao.UsuarioDAO;
import br.com.centralservicos.domain.Pessoa;
import br.com.centralservicos.domain.Usuario;

@ViewScoped
@ManagedBean
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 2734816701398616456L;

	private Usuario usuario;
	private List<Usuario> listarUsuarios;
	private List<Usuario> usuariosFiltrados;
	private List<Pessoa> listarPessoas;
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getListarUsuarios() {
		return listarUsuarios;
	}
	public void setListarUsuarios(List<Usuario> listarUsuarios) {
		this.listarUsuarios = listarUsuarios;
	}
	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}
	public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
		this.usuariosFiltrados = usuariosFiltrados;
	}
	public List<Pessoa> getListarPessoas() {
		return listarPessoas;
	}
	public void setListarPessoas(List<Pessoa> listarPessoas) {
		this.listarPessoas = listarPessoas;
	}
	
/*+++++++++++++++++++++++++++++++++++++++++++++OPERACIONAL++++++++++++++++++++++++++++++++++++++++++*/	

	@PostConstruct
	public void listar() {
		UsuarioDAO dao = new UsuarioDAO();
		
		listarUsuarios = dao.listar();
		
	}
	
	public void novo() {
		usuario = new Usuario();
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		listarPessoas=pessoaDAO.listar();
	}
	
	
	public void salvar() {
		try {
			SimpleHash hash = new SimpleHash("md5", usuario.getSenha());//convertendo para md5 a senha
			usuario.setSenha(hash.toHex());//Criando a senha
			
			UsuarioDAO dao = new UsuarioDAO();
			
			dao.merge(usuario);
			
			usuario = new Usuario();
			
			PessoaDAO  pessoaDAO = new PessoaDAO();
			listarPessoas = pessoaDAO.listar();
			
			listarUsuarios = dao.listarOrdenacao("tipo");
			
			Messages.addGlobalInfo("Usuário salvo com sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o usuário");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		listarPessoas=pessoaDAO.listar();
		
	}
	
	public void excluir(ActionEvent evento) {
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
		
		UsuarioDAO dao = new UsuarioDAO();
		dao.excluir(usuario);
		
		listarUsuarios = dao.listar();
	}
	
	
}

/*Corrigindo erro de salvar sem criptografia.
 * SimpleHash hash = new SimpleHash("md5", usuario.getSenha());//convertendo para md5
			usuario.setSenha(hash.toHex());

			A biblioteca shiro tem  que  está adicionada no pom.xml
			<dependency>
			<groupId>org.apache.shiro</groupId>
			<artifactId>shiro-core</artifactId>
			<version>1.2.4</version>
		</dependency>
			
 * */
