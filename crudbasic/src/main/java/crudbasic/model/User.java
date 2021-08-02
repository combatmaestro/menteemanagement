package crudbasic.model;

public class User {

	
	private int id;
	private String name;
	private String email;
	private String courses;
	private String batch;
	
	
	
	
	public User(int id, String name, String email, String courses, String batch) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.courses = courses;
		this.batch = batch;
	}
	
	
	
	public User(String name, String email, String courses, String batch) {
		super();
		this.name = name;
		this.email = email;
		this.courses = courses;
		this.batch = batch;
	}



			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getEmail() {
				return email;
			}
			public void setEmail(String email) {
				this.email = email;
			}
			public String getCourses() {
				return courses;
			}
			public void setCourses(String courses) {
				this.courses = courses;
			}
			public String getBatch() {
				return batch;
			}
			public void setBatch(String batch) {
				this.batch = batch;
			}
	
	
}
