package com.coherentlogic.geofred.client.core.domain;

/**
 * @see <a href="https://research.stlouisfed.org/docs/api/geofred/series_group.html">GeoFRED API - Series Group Info</a>
 *
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class SeriesGroup {

	public static final String TITLE = "title", REGION_TYPE = "regionType";
	
	private String title;

	private String regionType;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRegionType() {
		return regionType;
	}

	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}

	@Override
	public String toString() {
		return "SeriesGroup [title=" + title + ", regionType=" + regionType + "]";
	}
}
//{"series_group":[{"title":"All Employees: Total Private","region_type":"state","series_group":"1223","season":"NSA","units":"Thousands of Persons","frequency":"a","min_date":"1990-01-01","max_date":"2015-01-01"}]}