package jfaerman.flyfloyd.service;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import jfaerman.flyfloyd.model.Cotacao;

@Path("cotacao")
@Produces({APPLICATION_JSON,APPLICATION_XML})
public class CotacaoServiceREST {
	@Inject CotacaoLogicPOJO cotacaoLgc;

	@GET
	public Cotacao getQuote(@QueryParam("origem") @DefaultValue("") String origem,
			@QueryParam("origem") @DefaultValue("") String destino){
		return cotacaoLgc.getQuote(origem, destino);
	}
	
}
