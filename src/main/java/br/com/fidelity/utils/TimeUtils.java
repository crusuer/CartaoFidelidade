package br.com.fidelity.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {

	public SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
	public SimpleDateFormat interDate = new SimpleDateFormat("yyyy-MM-dd");
	public SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
	private Date date;

	public TimeUtils() {
		sdfDate.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		sdfTime.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
	}

	public Date getTime() {
		date = new Date();
		return date;
	}
}
