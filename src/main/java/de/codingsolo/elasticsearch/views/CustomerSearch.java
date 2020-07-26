package de.codingsolo.elasticsearch.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.primefaces.event.SelectEvent;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.codingsolo.elasticsearch.models.Kundendaten;

@Named("customersearch")
@ViewScoped
public class CustomerSearch {

	private final Logger LOGGER = LogManager.getLogger("de.codingsolo.elasticsearch.ElasticSearchSpringApplication");

	private String fieldQuickSearch, name, firstname, customerID;
	private List<Kundendaten> searchResults = new ArrayList<Kundendaten>();

	public void quickSearch() {
		LOGGER.info("QuickSearch for the Value: " + this.fieldQuickSearch);
		this.searchResults = new ArrayList<Kundendaten>();

		RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();

		Request request = new Request("GET", "/kundendaten/_search?q=" + this.fieldQuickSearch);
		ObjectMapper objectMapper = new ObjectMapper();

		Response response = null;

		try {
			LOGGER.info("Open Rest-Client (Quicksearch)");
			response = restClient.performRequest(request);
		} catch (IOException ioe) {
			LOGGER.error("Error while opening Rest-Client (Quicksearch)", ioe);
		}

		try {
			LOGGER.info("Conerting of Data (Quicksearch)");

			JSONObject jsonHitsObject = new JSONObject(EntityUtils.toString(response.getEntity()))
					.getJSONObject("hits");
			JSONArray jsonHitsArray = jsonHitsObject.getJSONArray("hits");

			for (Object object : jsonHitsArray) {
				JSONObject customerDataJSON = (JSONObject) object;
				Kundendaten customer = objectMapper.readValue(customerDataJSON.get("_source").toString(),
						Kundendaten.class);
				this.searchResults.add(customer);
			}
		} catch (Exception e) {
			LOGGER.error("Error while converting Data (Quicksearch)", e);
		}

		try {
			LOGGER.info("Closing Rest-Client (Quicksearch)");
			restClient.close();
		} catch (IOException ioe) {
			LOGGER.error("Error closing the Rest-Client (Quicksearch)", ioe);
		}

	}

	public void search() {
		LOGGER.info("Normal Search with this Values - Customer ID:" + this.customerID + ", Firstname: " + this.firstname
				+ ", Name: " + this.name);

		this.searchResults = new ArrayList<Kundendaten>();

		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));

		SearchRequest searchRequest = new SearchRequest("kundendaten");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		BoolQueryBuilder boolList = QueryBuilders.boolQuery()
				.should(QueryBuilders.matchQuery("kunde_kundennummer", this.customerID))
				.should(QueryBuilders.matchPhrasePrefixQuery("kunde_vorname", this.firstname))
				.should(QueryBuilders.matchPhrasePrefixQuery("kunde_name", this.name)).minimumShouldMatch(1);
		searchSourceBuilder.query(boolList);
		searchRequest.source(searchSourceBuilder);

		SearchResponse searchResponse = null;

		try {
			LOGGER.info("Open Rest-Client (Search)");
			System.out.println(searchRequest.toString());

			searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException ioe) {
			LOGGER.error("Error while opening Rest-Client (Search)", ioe);
		}

		try {
			LOGGER.info("Conerting the Data (Search)");
			SearchHits hits = searchResponse.getHits();
			System.out.println(hits.toString());
			SearchHit[] searchHits = hits.getHits();
			for (SearchHit hit : searchHits) {
				// do something with the SearchHit
				System.out.println(hit.getSourceAsString());
				Kundendaten customer = new ObjectMapper().readValue(hit.getSourceAsString(), Kundendaten.class);
				this.searchResults.add(customer);
			}

		} catch (IOException ioe) {
			LOGGER.error("Error while Converting Data (Search)", ioe);
		}

		try {
			LOGGER.info("Closing Rest-Client (Search)");
			client.close();
		} catch (IOException ioe) {
			LOGGER.error("Error while closing Rest-Client (Search)", ioe);
		}

	}

	public void selectCustomer(SelectEvent event) {
		Kundendaten customer = (Kundendaten) event.getObject();
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			context.getExternalContext().redirect("/customerdetails.xhtml?id=" + customer.getId());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	public String getFieldQuickSearch() {
		return fieldQuickSearch;
	}

	public void setFieldQuickSearch(String fieldQuickSearch) {
		this.fieldQuickSearch = fieldQuickSearch;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public List<Kundendaten> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<Kundendaten> searchResults) {
		this.searchResults = searchResults;
	}

}
