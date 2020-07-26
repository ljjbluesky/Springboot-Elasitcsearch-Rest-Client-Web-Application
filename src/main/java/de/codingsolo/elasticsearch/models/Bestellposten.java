package de.codingsolo.elasticsearch.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bestellposten {

	private long id;
	private double artikel_preis;
	private long bestellung_id;
	private String artikel_name, artikel_status;
	private int anzahl;

	public Bestellposten() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getArtikel_preis() {
		return artikel_preis;
	}

	public void setArtikel_preis(double artikel_preis) {
		this.artikel_preis = artikel_preis;
	}

	public long getBestellung_id() {
		return bestellung_id;
	}

	public void setBestellung_id(long bestellung_id) {
		this.bestellung_id = bestellung_id;
	}

	public String getArtikel_name() {
		return artikel_name;
	}

	public void setArtikel_name(String artikel_name) {
		this.artikel_name = artikel_name;
	}

	public String getArtikel_status() {
		return artikel_status;
	}

	public void setArtikel_status(String artikel_status) {
		this.artikel_status = artikel_status;
	}

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

}
