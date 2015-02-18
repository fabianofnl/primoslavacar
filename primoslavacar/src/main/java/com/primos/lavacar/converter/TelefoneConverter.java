package com.primos.lavacar.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Classe que implementa Converter, os métodos converter o Telefone extraindo
 * mascara ou adicionando
 * 
 * @author Claudemir
 * 
 */
@FacesConverter("telefoneConverter")
public class TelefoneConverter implements Converter {

	/**
	 * Método que converte o valor digitado na tela para o objeto do sistema, é
	 * removido a mascara do Telefone para ser convertido em um Long.
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		String telefone = value;
		if (value != null && !value.equals(""))
			telefone = value.replaceAll("\\(", "").replaceAll("\\-", "")
					.replaceAll("\\)", "");

		return telefone;
	}

	/**
	 * Método que converte um valor para ser apresentado na tela na forma de
	 * String, é adicionado a mascará do Telefone
	 * 
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		String telefone = value.toString();
		if (telefone != null && telefone.length() == 10)
			telefone = "(" + telefone.substring(0, 2) + ")"
					+ telefone.substring(2, 6) + "-"
					+ telefone.substring(6, 10);

		return telefone;
	}

}
