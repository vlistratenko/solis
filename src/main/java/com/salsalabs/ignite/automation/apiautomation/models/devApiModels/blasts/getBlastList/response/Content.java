package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.blasts.getBlastList.response;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "subject", "pageTitle", "pageUrl", "webVersionEnabled", "webVersionRedirectUrl",
		"webVersionRedirectDate" })
public class Content {

	@JsonProperty("subject")
	private String subject;
	@JsonProperty("pageTitle")
	private String pageTitle;
	@JsonProperty("pageUrl")
	private String pageUrl;
	@JsonProperty("webVersionEnabled")
	private Boolean webVersionEnabled;
	@JsonProperty("webVersionRedirectUrl")
	private String webVersionRedirectUrl;
	@JsonProperty("webVersionRedirectDate")
	private String webVersionRedirectDate;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("subject")
	public String getSubject() {
		return subject;
	}

	@JsonProperty("subject")
	public void setSubject(String subject) {
		this.subject = subject;
	}

	@JsonProperty("pageTitle")
	public String getPageTitle() {
		return pageTitle;
	}

	@JsonProperty("pageTitle")
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	@JsonProperty("pageUrl")
	public String getPageUrl() {
		return pageUrl;
	}

	@JsonProperty("pageUrl")
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	@JsonProperty("webVersionEnabled")
	public Boolean getWebVersionEnabled() {
		return webVersionEnabled;
	}

	@JsonProperty("webVersionEnabled")
	public void setWebVersionEnabled(Boolean webVersionEnabled) {
		this.webVersionEnabled = webVersionEnabled;
	}

	@JsonProperty("webVersionRedirectUrl")
	public String getWebVersionRedirectUrl() {
		return webVersionRedirectUrl;
	}

	@JsonProperty("webVersionRedirectUrl")
	public void setWebVersionRedirectUrl(String webVersionRedirectUrl) {
		this.webVersionRedirectUrl = webVersionRedirectUrl;
	}

	@JsonProperty("webVersionRedirectDate")
	public String getWebVersionRedirectDate() {
		return webVersionRedirectDate;
	}

	@JsonProperty("webVersionRedirectDate")
	public void setWebVersionRedirectDate(String webVersionRedirectDate) {
		this.webVersionRedirectDate = webVersionRedirectDate;
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
		return "Content [subject=" + subject + ", pageTitle=" + pageTitle + ", pageUrl=" + pageUrl
				+ ", webVersionEnabled=" + webVersionEnabled + ", webVersionRedirectUrl=" + webVersionRedirectUrl
				+ ", webVersionRedirectDate=" + webVersionRedirectDate + ", additionalProperties="
				+ additionalProperties + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalProperties == null) ? 0 : additionalProperties.hashCode());
		result = prime * result + ((pageTitle == null) ? 0 : pageTitle.hashCode());
		result = prime * result + ((pageUrl == null) ? 0 : pageUrl.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((webVersionEnabled == null) ? 0 : webVersionEnabled.hashCode());
		result = prime * result + ((webVersionRedirectDate == null) ? 0 : webVersionRedirectDate.hashCode());
		result = prime * result + ((webVersionRedirectUrl == null) ? 0 : webVersionRedirectUrl.hashCode());
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
		Content other = (Content) obj;
		if (additionalProperties == null) {
			if (other.additionalProperties != null)
				return false;
		} else if (!additionalProperties.equals(other.additionalProperties))
			return false;
		if (pageTitle == null) {
			if (other.pageTitle != null)
				return false;
		} else if (!pageTitle.equals(other.pageTitle))
			return false;
		if (pageUrl == null) {
			if (other.pageUrl != null)
				return false;
		} else if (!pageUrl.equals(other.pageUrl))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (webVersionEnabled == null) {
			if (other.webVersionEnabled != null)
				return false;
		} else if (!webVersionEnabled.equals(other.webVersionEnabled))
			return false;
		if (webVersionRedirectDate == null) {
			if (other.webVersionRedirectDate != null)
				return false;
		} else if (!webVersionRedirectDate.equals(other.webVersionRedirectDate))
			return false;
		if (webVersionRedirectUrl == null) {
			if (other.webVersionRedirectUrl != null)
				return false;
		} else if (!webVersionRedirectUrl.equals(other.webVersionRedirectUrl))
			return false;
		return true;
	}
	
	

}