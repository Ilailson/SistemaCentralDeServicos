package br.com.centralservicos.util;
import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.centralservicos.bean.AutenticacaoBean;
import br.com.centralservicos.domain.Usuario;


/**
 * 
 * @author Ilailson Rocha
 *
 *@see AutenticacaoBean
 *@see Usuario
 */
@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener{

	

	@Override
	public void afterPhase(PhaseEvent event) {
	
		String paginaAtual = Faces.getViewId();//pegar a página atual que está sendo acessada através do ID.
		
		/*Verificando se a página atual contem a página autenticação.xhtml. 
         * Ou seja estou  dizendo  que pagAut está recebendo a páginatual com o nome autenticação. */
		boolean pagAut = paginaAtual.contains("autenticacao.xhtml");
		
		if(!pagAut) {
			AutenticacaoBean autenticacaoBean = (AutenticacaoBean) Faces.getSessionAttribute("autenticacaoBean");
			
			if(autenticacaoBean==null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
				
			}
			
			Usuario usuario = autenticacaoBean.getUsuarioLogado();
			if(usuario==null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
				
			}
		}
		
		
	}
	

	/**
     ** Método chamado sempre antes de se executar uma determinada PHASE
     * @param event - {@link PhaseEvent}
     */
	@Override
	public void beforePhase(PhaseEvent event) {
		 ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
	        HttpServletRequest request = (HttpServletRequest) context.getRequest();
	        
	        String loginPage = "/pages/autenticacao.xhtml";
	        
	        if (!request.getRequestURL().toString().endsWith(loginPage) && (context.getSession(false) == null)) {
	            try {
	                context.redirect(request.getContextPath() + loginPage);
	            } catch (IOException e) {
	            	 Messages.addGlobalError("Erro ao tentar redirecionar para a página solicitada: ");
	            }
	        }
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;//caso aconteça algum erro ele vai reiniciar a autenticação. 	
	}

}


/*Responsável por tratar a segurança das páginas. Faz com o usuário não possa burlar o sistema
 * entrar em um página sem autorização. 
 * 
 * Para essa classe funcionar tem que criar o faces-config.xml (webapp\WEB-INF) e adicionar a configuração abaixo. 
 * 
 *  <lifecycle>
 <phase-listener>
 br.com.centralservicos.util.AutenticacaoListener
 </phase-listener>
 </lifecycle>
 * 
 */


/*Método PhaseId getPhaseId()
 * Volta para a página de autenticação caso ocorra algum erro. 
 *  return PhaseId.RESTORE_VIEW;
 */

/*Método afterPhase(PhaseEvent event)
 * 
 * /*se não estiver na página autenticação ele vai jogar para a página autenticação. 
	 * 
	 * Se autenticação do usuário for igual a null ele ta tentando burlar o sistema
	 * vai direcionar para a página autenticação. 
	 * 
	 * se usuário for null ele vai direcionar para a página autinticaçãoxhtml. 
	 * */

