package de.codingsolo.elasticsearch.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Anschrift {

	private long id;

	private String anschrift_Strasse, anschrift_hausnummer, anschrift_postleitzahl, anschrift_ort, Anschrift_land,
			land_kuerzel;
	private Date anschrift_erstellung;

	public Anschrift() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnschrift_Strasse() {
		return anschrift_Strasse;
	}

	public void setAnschrift_Strasse(String anschrift_Strasse) {
		this.anschrift_Strasse = anschrift_Strasse;
	}

	public String getAnschrift_hausnummer() {
		return anschrift_hausnummer;
	}

	public void setAnschrift_hausnummer(String anschrift_hausnummer) {
		this.anschrift_hausnummer = anschrift_hausnummer;
	}

	public String getAnschrift_postleitzahl() {
		return anschrift_postleitzahl;
	}

	public void setAnschrift_postleitzahl(String anschrift_postleitzahl) {
		this.anschrift_postleitzahl = anschrift_postleitzahl;
	}

	public String getAnschrift_ort() {
		return anschrift_ort;
	}

	public void setAnschrift_ort(String anschrift_ort) {
		this.anschrift_ort = anschrift_ort;
	}

	public String getAnschrift_land() {
		return Anschrift_land;
	}

	public void setAnschrift_land(String anschrift_land) {
		Anschrift_land = anschrift_land;
	}

	public String getLand_kuerzel() {
		return land_kuerzel;
	}

	public void setLand_kuerzel(String land_kuerzel) {
		this.land_kuerzel = land_kuerzel;
	}

	public Date getAnschrift_erstellung() {
		return anschrift_erstellung;
	}

	public void setAnschrift_erstellung(Date anschrift_erstellung) {
		this.anschrift_erstellung = anschrift_erstellung;
	}

}
