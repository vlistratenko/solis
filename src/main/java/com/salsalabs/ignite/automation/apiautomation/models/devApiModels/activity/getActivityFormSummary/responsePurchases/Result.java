package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.activity.getActivityFormSummary.responsePurchases;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "purchaserFirstName", "purchaserLastName", "purchaserId", "registrantName",
		"registrantEmail", "registrantTicket", "quantity", "product", "cost", "status", "purchased" })
public class Result {

	@JsonProperty("id")
	private String id;
	@JsonProperty("purchaserFirstName")
	private String purchaserFirstName;
	@JsonProperty("purchaserLastName")
	private String purchaserLastName;
	@JsonProperty("purchaserId")
	private String purchaserId;
	@JsonProperty("registrantName")
	private String registrantName;
	@JsonProperty("registrantEmail")
	private String registrantEmail;
	@JsonProperty("registrantTicket")
	private String registrantTicket;
	@JsonProperty("quantity")
	private Integer quantity;
	@JsonProperty("product")
	private String product;
	@JsonProperty("cost")
	private String cost;
	@JsonProperty("status")
	private String status;
	@JsonProperty("purchased")
	private String purchased;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("purchaserFirstName")
	public String getPurchaserFirstName() {
		return purchaserFirstName;
	}

	@JsonProperty("purchaserFirstName")
	public void setPurchaserFirstName(String purchaserFirstName) {
		this.purchaserFirstName = purchaserFirstName;
	}

	@JsonProperty("purchaserLastName")
	public String getPurchaserLastName() {
		return purchaserLastName;
	}

	@JsonProperty("purchaserLastName")
	public void setPurchaserLastName(String purchaserLastName) {
		this.purchaserLastName = purchaserLastName;
	}

	@JsonProperty("purchaserId")
	public String getPurchaserId() {
		return purchaserId;
	}

	@JsonProperty("purchaserId")
	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
	}

	@JsonProperty("registrantName")
	public String getRegistrantName() {
		return registrantName;
	}

	@JsonProperty("registrantName")
	public void setRegistrantName(String registrantName) {
		this.registrantName = registrantName;
	}

	@JsonProperty("registrantEmail")
	public String getRegistrantEmail() {
		return registrantEmail;
	}

	@JsonProperty("registrantEmail")
	public void setRegistrantEmail(String registrantEmail) {
		this.registrantEmail = registrantEmail;
	}

	@JsonProperty("registrantTicket")
	public String getRegistrantTicket() {
		return registrantTicket;
	}

	@JsonProperty("registrantTicket")
	public void setRegistrantTicket(String registrantTicket) {
		this.registrantTicket = registrantTicket;
	}

	@JsonProperty("quantity")
	public Integer getQuantity() {
		return quantity;
	}

	@JsonProperty("quantity")
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@JsonProperty("product")
	public String getProduct() {
		return product;
	}

	@JsonProperty("product")
	public void setProduct(String product) {
		this.product = product;
	}

	@JsonProperty("cost")
	public String getCost() {
		return cost;
	}

	@JsonProperty("cost")
	public void setCost(String cost) {
		this.cost = cost;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("purchased")
	public String getPurchased() {
		return purchased;
	}

	@JsonProperty("purchased")
	public void setPurchased(String purchased) {
		this.purchased = purchased;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "Result [id=" + id + ", purchaserFirstName=" + purchaserFirstName + ", purchaserLastName="
				+ purchaserLastName + ", purchaserId=" + purchaserId + ", registrantName=" + registrantName
				+ ", registrantEmail=" + registrantEmail + ", registrantTicket=" + registrantTicket + ", quantity="
				+ quantity + ", product=" + product + ", cost=" + cost + ", status=" + status + ", purchased="
				+ purchased + ", additionalProperties=" + additionalProperties + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalProperties == null) ? 0 : additionalProperties.hashCode());
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((purchased == null) ? 0 : purchased.hashCode());
		result = prime * result + ((purchaserFirstName == null) ? 0 : purchaserFirstName.hashCode());
		result = prime * result + ((purchaserId == null) ? 0 : purchaserId.hashCode());
		result = prime * result + ((purchaserLastName == null) ? 0 : purchaserLastName.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((registrantEmail == null) ? 0 : registrantEmail.hashCode());
		result = prime * result + ((registrantName == null) ? 0 : registrantName.hashCode());
		result = prime * result + ((registrantTicket == null) ? 0 : registrantTicket.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result other = (Result) obj;
		if (additionalProperties == null) {
			if (other.additionalProperties != null)
				return false;
		} else if (!additionalProperties.equals(other.additionalProperties))
			return false;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (purchased == null) {
			if (other.purchased != null)
				return false;
		} else if (!purchased.equals(other.purchased))
			return false;
		if (purchaserFirstName == null) {
			if (other.purchaserFirstName != null)
				return false;
		} else if (!purchaserFirstName.equals(other.purchaserFirstName))
			return false;
		if (purchaserId == null) {
			if (other.purchaserId != null)
				return false;
		} else if (!purchaserId.equals(other.purchaserId))
			return false;
		if (purchaserLastName == null) {
			if (other.purchaserLastName != null)
				return false;
		} else if (!purchaserLastName.equals(other.purchaserLastName))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (registrantEmail == null) {
			if (other.registrantEmail != null)
				return false;
		} else if (!registrantEmail.equals(other.registrantEmail))
			return false;
		if (registrantName == null) {
			if (other.registrantName != null)
				return false;
		} else if (!registrantName.equals(other.registrantName))
			return false;
		if (registrantTicket == null) {
			if (other.registrantTicket != null)
				return false;
		} else if (!registrantTicket.equals(other.registrantTicket))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	
}