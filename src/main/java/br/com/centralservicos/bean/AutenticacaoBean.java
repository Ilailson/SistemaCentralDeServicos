package br.com.centralservicos.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.centralservicos.dao.UsuarioDAO;
import br.com.centralservicos.domain.Pessoa;
import br.com.centralservicos.domain.Usuario;
import br.com.centralservicos.util.AutenticacaoListener;

/**
 * 
 *@author Ilailson Rocha
 *
 *@see UsuarioDAO
 *@see Pessoa
 *@see Usuario
 *@see AutenticacaoListener
 */

@SuppressWarnings("serial")
@SessionScoped
@ManagedBean
public class AutenticacaoBean implements Serializable{

	private  Usuario usuario;
	private  Usuario usuarioLogado;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	
/*++++++++++++++++++++++++++++++++++OPERACIONAL+++++++++++++++++++++++++++++++++++++++++++++++++++*/	
	


@PostConstruct
public void iniciar() {
	usuario = new Usuario();
	usuario.setPessoa(new Pessoa());
	
	//limpa a pagiina de autenticação  toda vez  que  for chamada.
	
	/*Limpa os dados do usuário e os dados da pessoa.*/
}



public void autenticar() {
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getNomeusuario(), usuario.getSenha());
	
	try {
		
		if(usuarioLogado==null) {
			Messages.addGlobalError("Login ou senha incorretos");
			return;//retorna a mesma pagina autenticação
		}
		
		Faces.redirect("./pages/chefia.xhtml");
		
	} catch (IOException e) {
		e.printStackTrace();
		Messages.addGlobalError("Erro ao conectar ao banco de dados");
	}
	
}

public boolean temPermissoes(List<String> permissoes) {
	for (String permissao : permissoes) {
		if(usuarioLogado.getTipo()==permissao.charAt(0)) {
			return true;
		}
	}
	
	return false;
}


/**
 * Invalida a sessão do usuário quando clicar em sair. 
 * @return
 */
public String logout() {
	
	//Redireciona o usuário para tela de login efetuando o logout.
    String loginPage = "/pages/autenticacao.xhtml";
    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    HttpServletRequest request = (HttpServletRequest) context.getRequest();
    try {
        context.redirect(request.getContextPath() + loginPage);
    } catch (IOException e) {
        Messages.addGlobalError("Erro ao tentar redirecionar para página solicitada ao efetuar Logout: ");
    	
    }
    HttpSession session = (HttpSession) context.getSession(false);
    session.invalidate();

    return "logout";
	
}

}

/*Criar a classe autenticacaoBean (@SessionScoped, @ManagedBean) para permanecer com o usuário logado.
 * 
 * 1 Criar o metodo autenticar dentro da classe UsuarioDAO
 * 
 * 2 criar a classe AutenticacaoBean
 * 
 * 3 Criar a classe autenticarListener dentro do pacote util. 
 * 
 * */

/*Método INICIAR. Toda 
 * Instancia um novo usuário e uma nova pessoa toda vez que for chamada a página de autenticação: limpados 
 * todos os dados de autenticação anteriores*/
		

/*Metodo autenticar: 
 * - usuario logado recebe os dados da autenticação (cpf e senha)
 * - Verifica se é null: 
 * 		Caso null seja informa na tela CPF ou senha incorretos
 * 		Caso encontrado direcionada para a pagina principal através da framework ominefaces
 */
 

/*Classe AutenticacaoListener
 * Criar a classe dentro do pacote util. Essa classe será responsável pela segurança do sistema
 * Fazer com  que o usuário não acesso outras páginas se autorização. */



/*Arquivo faces-config.xml: dentro da pasta WEB-inf
 * 
 * Para a autenticação funcionar tem que criar esse arquivo com a configuração abaixo: 
 * 
	<?xml version="1.0"?>
	<faces-config version="2.2" xmlns="http://xmlns.jcp.org/xml/ns/javaee"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-facesconfig_2_2.xsd">
	<application/>
	
		<lifecycle>
		 <phase-listener>
		 	br.com.financeiro.util.AutenticacaoListener
		 </phase-listener>
		</lifecycle>
	</faces-config>* 
 */


/*Método  temPermissoes(List<String> permissoes)
 * 
 * Se a permissãodo usuário logado for igual a variavel permissão do tipo caracter retorna verdadeiro.
 * Se não  false
 * 
 * Testando no template ou em páginas individuais.
 * Exemplo tudo que está dentro da página de cadastro só vai renderizar (aparecer) se for do TIPO A e G
 * <p:submenu label="Cadastro" rendered="#{autenticacaoBean.temPermissoes(['A', 'G'])}">
 * */

