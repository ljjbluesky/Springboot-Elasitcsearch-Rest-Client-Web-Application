package de.codingsolo.elasticsearch.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bestellung {

	private long bestellung_id;
	private String bestellung_status;
	private Date bestellung_datum;
	private List<Bestellposten> bestellung_posten;

	public Bestellung() {

	}

	public long getBestellung_id() {
		return bestellung_id;
	}

	public void setBestellung_id(long bestellung_id) {
		this.bestellung_id = bestellung_id;
	}

	public String getBestellung_status() {
		return bestellung_status;
	}

	public void setBestellung_status(String bestellung_status) {
		this.bestellung_status = bestellung_status;
	}

	public Date getBestellung_datum() {
		return bestellung_datum;
	}

	public void setBestellung_datum(Date bestellung_datum) {
		this.bestellung_datum = bestellung_datum;
	}

	public List<Bestellposten> getBestellung_posten() {
		return bestellung_posten;
	}

	public void setBestellung_posten(List<Bestellposten> bestellung_posten) {
		this.bestellung_posten = bestellung_posten;
	}

}
