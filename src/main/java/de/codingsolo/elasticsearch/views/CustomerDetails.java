package de.codingsolo.elasticsearch.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.http.HttpHost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.codingsolo.elasticsearch.models.Bestellposten;
import de.codingsolo.elasticsearch.models.Bestellung;
import de.codingsolo.elasticsearch.models.Kundendaten;

@Named("customerdetails")
@ViewScoped
public class CustomerDetails {

	private final Logger LOGGER = LogManager.getLogger("de.codingsolo.elasticsearch.ElasticSearchSpringApplication");

	private String id;
	private Kundendaten customerData;
	private List<Bestellung> openOrders;
	private List<Bestellposten> orderItems;

	@PostConstruct
	private void init() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		this.id = params.get("id");
		orderItems = new ArrayList<Bestellposten>();
		this.getData();
	}

	public void getData() {

		LOGGER.info("getData for ID: " + this.id);

		GetRequest getRequest = new GetRequest("kundendaten", this.id);

		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));

		GetResponse getResponse = null;

		try {
			LOGGER.info("Open Rest-Client (getData)");

			getResponse = client.get(getRequest, RequestOptions.DEFAULT);
		} catch (IOException ioe) {
			LOGGER.error("Error while opening Rest-Client (getData)", ioe);
		}

		try {
			LOGGER.info("Converting the Data (getData)");

			System.out.println(getResponse.getSourceAsString());

			this.customerData = new ObjectMapper().readValue(getResponse.getSourceAsString(), Kundendaten.class);
			this.sortOpenOrders();

			PrimeFaces.current().ajax().update();

		} catch (IOException ioe) {
			LOGGER.error("Error while converting Data (getData)", ioe);
		}

		try {
			LOGGER.info("Closing Rest-Client (getData)");
			client.close();
		} catch (IOException ioe) {
			LOGGER.error("Error while closing Rest-Client (getData)", ioe);
		}

	}

	private void sortOpenOrders() {
		this.openOrders = new ArrayList<Bestellung>();
		for (Bestellung order : this.customerData.getKunde_bestellung()) {
			if (order.getBestellung_status().equals("In Bearbeitung")
					|| order.getBestellung_status().equals("Verzögert")) {
				this.openOrders.add(order);

			}
		}

	}

	public double clalculateSum(List<Bestellposten> items) {
		double value = 0.0;

		for (Bestellposten orderItem : items) {
			value += orderItem.getArtikel_preis();
		}

		return value;
	}

	public void selectOrder(SelectEvent event) {
		this.orderItems = new ArrayList<Bestellposten>();
		Bestellung orders = (Bestellung) event.getObject();
		for (Bestellposten items : orders.getBestellung_posten()) {
			this.orderItems.add(items);
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Kundendaten getCustomerData() {
		return customerData;
	}

	public void setCustomerData(Kundendaten customerData) {
		this.customerData = customerData;
	}

	public List<Bestellung> getOpenOrders() {
		return openOrders;
	}

	public void setOpenOrders(List<Bestellung> openOrders) {
		this.openOrders = openOrders;
	}

	public List<Bestellposten> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Bestellposten> orderItems) {
		this.orderItems = orderItems;
	}

}
