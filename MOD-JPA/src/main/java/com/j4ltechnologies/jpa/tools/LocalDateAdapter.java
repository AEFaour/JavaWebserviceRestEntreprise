package com.j4ltechnologies.jpa.tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	@Override
	public LocalDate unmarshal(String v) throws Exception {
		
		return LocalDate.parse(v, formatter);
	}

	@Override
	public String marshal(LocalDate v) throws Exception {
		
		return v.format(formatter);
	}

}
