package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Generation_Data {
	private SimpleStringProperty Description;
	private SimpleIntegerProperty Day_Peak;
	private SimpleIntegerProperty Evening_Peak;

	public String getDescription() {
		return Description.get();
	}

	public void setDescription(String description) {
		Description.set(description);
	}

	public int getDay_Peak() {
		return Day_Peak.get();
	}

	public void setDay_Peak(int day_Peak) {
		Day_Peak.set(day_Peak);
	}

	public int getEvening_Peak() {
		return Evening_Peak.get();
	}

	public void setEvening_Peak(int evening_Peak) {
		Evening_Peak.set(evening_Peak);
	}

	public Generation_Data(String description, int day_Peak, int evening_Peak) {
		
		Description = new SimpleStringProperty(description);
		Day_Peak = new SimpleIntegerProperty(day_Peak);
		Evening_Peak = new SimpleIntegerProperty(evening_Peak);
	}

	public Generation_Data() {
		Description = null;
		Day_Peak = null;
		Evening_Peak = null;
	}

}
