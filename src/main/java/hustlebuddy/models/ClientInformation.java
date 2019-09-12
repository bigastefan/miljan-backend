package hustlebuddy.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClientInformation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String gender;
	
	private Double weight;
	
	private Double height;
	
	private Double chestMeasurement;
	
	private Double waistMeasurement;
	
	//Constructors
	
	public ClientInformation() {
		
	}

	public ClientInformation(Long id, String gender, Double weight, Double height, Double chestMeasurement, Double waistMeasurement) {
		this.id = id;
		this.gender = gender;
		this.weight = weight;
		this.height = height;
		this.chestMeasurement = chestMeasurement;
		this.waistMeasurement = waistMeasurement;
	}

	public ClientInformation(String gender, Double weight, Double height, Double chestMeasurement, Double waistMeasurement) {
		this.gender = gender;
		this.weight = weight;
		this.height = height;
		this.chestMeasurement = chestMeasurement;
		this.waistMeasurement = waistMeasurement;
	}

	//Getters and Setters
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getChestMeasurement() {
		return chestMeasurement;
	}

	public void setChestMeasurement(Double chestMeasurement) {
		this.chestMeasurement = chestMeasurement;
	}

	public Double getWaistMeasurement() {
		return waistMeasurement;
	}

	public void setWaistMeasurement(Double waistMeasurement) {
		this.waistMeasurement = waistMeasurement;
	}
	
	
}
