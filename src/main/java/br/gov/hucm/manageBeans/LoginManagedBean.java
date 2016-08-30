package br.gov.hucm.manageBeans;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.gov.hucm.bd.UsuarioDAO;
import br.gov.hucm.entidades.Usuario;

@ManagedBean(name = "LoginMB")
@ViewScoped
public class LoginManagedBean {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario usuario = new Usuario();

	public String envia() throws IOException {

		setUsuario(usuarioDAO.getUsuario(usuario.getNomeUsuario(), usuario.getSenha()));
		if (getUsuario() == null) {
			setUsuario(new Usuario());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!", "Erro no Login!"));
			return null;
		} else {
			FacesContext.getCurrentInstance().getExternalContext().redirect("pages/index.xhtml");
			return "/main";
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}