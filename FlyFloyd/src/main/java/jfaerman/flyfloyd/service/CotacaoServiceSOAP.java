package jfaerman.flyfloyd.service;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import jfaerman.flyfloyd.model.Cotacao;

@WebService
public class CotacaoServiceSOAP {
	@Inject CotacaoLogicPOJO cotacaoLgc;
	
	@WebMethod
	public Cotacao getQuote(String origem, String destino){
		return cotacaoLgc.getQuote(origem, destino);
	}

}
