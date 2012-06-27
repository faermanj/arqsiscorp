package jfaerman.flyfloyd.service;

import java.math.BigDecimal;
import java.util.Random;

import jfaerman.flyfloyd.model.Cotacao;

public class CotacaoLogicPOJO {
	private static Random rand = new Random();

	public Cotacao getQuote(String origem, String destino) {
		double price = (origem.length() + destino.length())
				* (1 + (rand.nextDouble() * 0.30)) * 100;
		return new Cotacao(price);
	}
}
