package pojos;

import java.util.List;

public class Course{
	private List<String> spartans;
	private String stream;
	private String endDate;
	private String trainer;
	private String name;
	private List<LinksItem> links;
	private int id;
	private String startDate;

	public List<String> getSpartans(){
		return spartans;
	}

	public String getStream(){
		return stream;
	}

	public String getEndDate(){
		return endDate;
	}

	public String getTrainer(){
		return trainer;
	}

	public String getName(){
		return name;
	}

	public List<LinksItem> getLinks(){
		return links;
	}

	public int getId(){
		return id;
	}

	public String getStartDate(){
		return startDate;
	}
}