package br.com.centralservicos.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AprendendoDatas {

	public static void main(String[] args) {
		Date hoje = new Date();
		
		System.out.println(hoje);
		
		DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		System.out.println(formatador.format(hoje));
		
		DateFormat formatador1 = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		System.out.println(formatador1.format(hoje));
		
		//convertendo string para data
		String dataAniversario = "20/01/1985";
		DateFormat formatador2 = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date aniversario = formatador2.parse(dataAniversario);
			System.out.println("Data de aniversário: "+formatador2.format(aniversario));
		} catch (ParseException e) {
			System.out.println("Formato de data inválido");
			e.printStackTrace();
		}
		
		
		
		System.out.println("---------------------------------Trabalhando com Calendar------------------------------");
		Calendar c = new GregorianCalendar();
		
		c.set(Calendar.DAY_OF_MONTH, 10);
		c.set(Calendar.MONTH, 8);
		c.set(Calendar.YEAR, 2010);
		c.set(Calendar.HOUR_OF_DAY, 10);
		c.set(Calendar.MINUTE, 30);
		c.set(Calendar.SECOND, 0);
		
		DateFormat formatador3 = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(formatador3.format(c.getTime()));
		
		
		
	}

}
