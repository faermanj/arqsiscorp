package jfaerman.flyfloyd.service;

import java.math.BigDecimal;
import java.util.Random;

import javax.ejb.Stateless;

@Stateless
public class CotacaoService {
	private static Random rand = new Random();
	
	public BigDecimal getQuote(String origem, String destino){
		double price = (origem.length() + destino.length()) 
		* (1 + (rand.nextDouble()*0.30) )
		* 100;
		return new BigDecimal(price);
	}
}
