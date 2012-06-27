package jfaerman.flyfloyd.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;

public class Utils {
	private static final DateFormat sdf = DateFormat.getDateTimeInstance();
	private static final NumberFormat nf = NumberFormat.getCurrencyInstance();
	public static final String formatTime(Date d){
		return sdf.format(d);
	}
	public static final String formatMoney(BigDecimal n){
		return nf.format(n);
	}
}
