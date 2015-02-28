/**
 * 
 */
package com.primos.lavacar.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

/**
 * @author Claudemir
 * 
 */
@ManagedBean
@ViewScoped
public class ReceitaDespesaManagedBean implements Serializable {

	private static final long serialVersionUID = -2728428944291838197L;
	private static final Logger LOG = Logger
			.getLogger(ReceitaDespesaManagedBean.class);

	public void carregarTabela(ActionEvent event) {
		LOG.info("carregarTabela");
	}

}
