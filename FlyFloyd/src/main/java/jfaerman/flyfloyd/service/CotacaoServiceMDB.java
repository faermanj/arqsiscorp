package jfaerman.flyfloyd.service;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.ejb.ActivationConfigProperty;

import jfaerman.flyfloyd.model.Cotacao;


@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/CotacaoReqQueue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class CotacaoServiceMDB implements MessageListener {

	@Resource(mappedName = "java:/ConnectionFactory")
	private static ConnectionFactory connectionFactory;

	
	@Inject CotacaoLogicPOJO cotacaoLgc;
	
	@Override
	public void onMessage(Message msg) {
		try {
			String origem = msg.getStringProperty("origem");
			String destino = msg.getStringProperty("destino");
			Cotacao cotacao = cotacaoLgc.getQuote(origem, destino);
			System.out.println("**Cotacao MDB "+cotacao);
			Destination dest = msg.getJMSReplyTo();
			if(dest != null){
				Session session = connectionFactory.createConnection().createSession(false,
						Session.AUTO_ACKNOWLEDGE);
				MessageProducer messageProducer = session.createProducer(dest);
				ObjectMessage reply = session.createObjectMessage(cotacao);
				messageProducer.send(reply);
				session.close();
			}else{
				System.out.println("Forever alone...");
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
