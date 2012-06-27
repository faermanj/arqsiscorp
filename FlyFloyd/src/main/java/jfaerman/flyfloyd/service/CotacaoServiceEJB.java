package jfaerman.flyfloyd.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import jfaerman.flyfloyd.model.Cotacao;

@Stateless
public class CotacaoServiceEJB {
	@Inject CotacaoLogicPOJO cotacaoLgc;
	
	public Cotacao getQuote(String origem, String destino){
		return cotacaoLgc.getQuote(origem, destino);
	}
}
