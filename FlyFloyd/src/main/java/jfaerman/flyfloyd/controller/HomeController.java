package jfaerman.flyfloyd.controller;

import java.math.BigDecimal;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import jfaerman.flyfloyd.service.CotacaoService;

@Model
public class HomeController {
	@Inject
	private CotacaoService cotSvc;
	private String origem;
	private String destino;
	private BigDecimal price;
	
	public void buscar(){
		System.out.println(origem+" -> "+destino);
		price = cotSvc.getQuote(origem, destino);
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	
}
