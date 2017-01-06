/**
 * 
 */
package com.cubestack.social.candidate;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;



/**
 * @author supal
 *
 */
public class TweepleCandidate {

	private long id;

	@Range(min = 1950, max = 2050, message = "Year should be between 1950 and 2050")
	private int year;

	@NotEmpty(message ="Make is Required")
	private String make;

	@NotEmpty(message ="Model is Required")
	private String model;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}


}
