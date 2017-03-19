package br.com.casadocodigo.loja.infra;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

@ApplicationScoped
public class FacesContextProducer {

	@Produces
	public FacesContext get() {
		return FacesContext.getCurrentInstance();
	}
}