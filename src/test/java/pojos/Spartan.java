package pojos;

import java.util.List;

public class Spartan{
	private String firstName;
	private String lastName;
	private String stream;
	private String university;
	private boolean graduated;
	private String degree;
	private String course;
	private List<LinksItem> links;
	private int id;

	public String getFirstName(){
		return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public String getStream(){
		return stream;
	}

	public String getUniversity(){
		return university;
	}

	public boolean isGraduated(){
		return graduated;
	}

	public String getDegree(){
		return degree;
	}

	public String getCourse(){
		return course;
	}

	public List<LinksItem> getLinks(){
		return links;
	}

	public int getId(){
		return id;
	}
}