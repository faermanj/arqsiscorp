package jfaerman.flyfloyd.model;

import static jfaerman.flyfloyd.util.Utils.formatMoney;
import static jfaerman.flyfloyd.util.Utils.formatTime;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Cotacao implements Serializable{
	private static final long serialVersionUID = 4354139476400022556L;
	BigDecimal precoTotal;
	Date validoAte;

	public Cotacao() {}
	public Cotacao(double price) {
		precoTotal = new BigDecimal(price);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, 5);
		validoAte =  cal.getTime();
	}
	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}
	public Date getValidoAte() {
		return validoAte;
	}
	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}
	public void setValidoAte(Date validoAte) {
		this.validoAte = validoAte;
	}
	
	@Override
	public String toString() { 
		StringBuilder str = new StringBuilder();
		str.append("\n**** Cotacao FlyFloyd ******");
		str.append("\n ValorTotal: "+ formatMoney( precoTotal));
		str.append("\n ValidoAté: "+ formatTime(validoAte));
		str.append("\n********************************\n");
		return str.toString();
	}


}
