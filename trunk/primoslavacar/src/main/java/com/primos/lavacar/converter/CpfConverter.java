/**
 * 
 */
package com.primos.lavacar.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Classe que implementa Converter, os métodos converter o CPF extraindo mascara
 * ou adicionando
 * 
 * @author Claudemir
 * 
 */
@FacesConverter("cpfConverter")
public class CpfConverter implements Converter {

	/**
	 * Método que converte o valor digitado na tela para o objeto do sistema, é
	 * removido a mascara do CPF para ser convertido em um Long.
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		String cpf = value;
		if (value != null && !value.equals(""))
			cpf = value.replaceAll("\\.", "").replaceAll("\\-", "");

		return cpf;
	}

	/**
	 * Método que converte um valor para ser apresentado na tela na forma de
	 * String, é adicionado a mascará do CPF
	 * 
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		String cpf = value.toString();

		if (cpf.length() == 10)
			cpf = "0" + cpf;

		if (cpf.length() == 9)
			cpf = "00" + cpf;

		if (cpf != null && cpf.length() == 11)
			cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "."
					+ cpf.substring(6, 9) + "-" + cpf.substring(9, 11);

		return cpf;
	}
}
