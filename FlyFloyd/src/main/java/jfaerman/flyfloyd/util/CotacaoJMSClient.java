package jfaerman.flyfloyd.util;

import java.io.Serializable;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class CotacaoJMSClient  {
	
	public static void main(String[] args) throws Exception {
		final Properties env = new Properties();
		 
		env.put(Context.INITIAL_CONTEXT_FACTORY, org.jboss.naming.remote.client.InitialContextFactory.class.getName());
		env.put(Context.PROVIDER_URL, "remote://localhost:4447");
		env.put("jboss.naming.client.ejb.context", true);
		env.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");       
		env.put(Context.SECURITY_PRINCIPAL, "admin");
		env.put(Context.SECURITY_CREDENTIALS, "redhat");
		Context ctx = new InitialContext(env);  
	     
		ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/RemoteConnectionFactory");
		Queue cotacaoReqQueue = (Queue) ctx.lookup("jms/queue/CotacaoReqQueue");
		
		Connection connection = connectionFactory.createConnection("admin","redhat");
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);

		TemporaryQueue respQueue = session.createTemporaryQueue();
		MessageConsumer consumer = session.createConsumer(respQueue);
		
		
		MessageProducer messageProducer = session.createProducer(cotacaoReqQueue);
		TextMessage msg = session.createTextMessage();
		msg.setStringProperty("origem", "Uberlândia");
		msg.setStringProperty("destino", "Araguari");
		msg.setJMSReplyTo(respQueue);
		System.out.println("Sending message");
		messageProducer.send(msg);
		System.out.println("Esperando resposta... ");
		connection.start();
		Message reply = consumer.receive();
		System.out.println("Recebido!");
		Serializable cotacao = ((ObjectMessage) reply).getObject();
		System.out.println(cotacao);
		connection.close();
		
		
	}

}
