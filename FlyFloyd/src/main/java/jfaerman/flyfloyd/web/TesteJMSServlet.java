package jfaerman.flyfloyd.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/testeCotacao")
public class TesteJMSServlet extends HttpServlet {

	@Resource(mappedName = "java:/ConnectionFactory")
	private static ConnectionFactory connectionFactory;

	@Resource(mappedName = "java:/queue/CotacaoReqQueue")
	private static Queue cotacaoReqQueue;

	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(cotacaoReqQueue);
			TextMessage message = session.createTextMessage();

			message.setText("This is message ");
			System.out.println("Sending message: " + message.getText());
			messageProducer.send(message);

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hello from Servlet");
	}

}
