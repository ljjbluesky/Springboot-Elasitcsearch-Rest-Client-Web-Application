package de.codingsolo.elasticsearch.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Kundendaten {

	private long id;
	private String kunde_name, kunde_vorname, kunde_kundennummer;
	private Boolean kunde_sperrung;
	private Date kunde_geburtsdatum, kunde_erstellung, kunde_bearbeitet;
	private List<Anschrift> kunde_anschrift;
	private List<Bestellung> kunde_bestellung;

	public Kundendaten() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKunde_name() {
		return kunde_name;
	}

	public void setKunde_name(String kunde_name) {
		this.kunde_name = kunde_name;
	}

	public String getKunde_vorname() {
		return kunde_vorname;
	}

	public void setKunde_vorname(String kunde_vorname) {
		this.kunde_vorname = kunde_vorname;
	}

	public String getKunde_kundennummer() {
		return kunde_kundennummer;
	}

	public void setKunde_kundennummer(String kunde_kundennummer) {
		this.kunde_kundennummer = kunde_kundennummer;
	}

	public Boolean getKunde_sperrung() {
		return kunde_sperrung;
	}

	public void setKunde_sperrung(Boolean kunde_sperrung) {
		this.kunde_sperrung = kunde_sperrung;
	}

	public Date getKunde_geburtsdatum() {
		return kunde_geburtsdatum;
	}

	public void setKunde_geburtsdatum(Date kunde_geburtsdatum) {
		this.kunde_geburtsdatum = kunde_geburtsdatum;
	}

	public Date getKunde_erstellung() {
		return kunde_erstellung;
	}

	public void setKunde_erstellung(Date kunde_erstellung) {
		this.kunde_erstellung = kunde_erstellung;
	}

	public Date getKunde_bearbeitet() {
		return kunde_bearbeitet;
	}

	public void setKunde_bearbeitet(Date kunde_bearbeitet) {
		this.kunde_bearbeitet = kunde_bearbeitet;
	}

	public List<Anschrift> getKunde_anschrift() {
		return kunde_anschrift;
	}

	public void setKunde_anschrift(List<Anschrift> kunde_anschrift) {
		this.kunde_anschrift = kunde_anschrift;
	}

	public List<Bestellung> getKunde_bestellung() {
		return kunde_bestellung;
	}

	public void setKunde_bestellung(List<Bestellung> kunde_bestellung) {
		this.kunde_bestellung = kunde_bestellung;
	}

}
