package cs544.mum.edu.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


@Entity
@DiscriminatorValue("Volunteer")
public class Volunteer extends User {
	
	@OneToMany(mappedBy="volunteer")
	@Transient
	private List<Task> tasks = new ArrayList<Task>();
	
	public Volunteer(){
	}

	public Volunteer(Integer id, String name){
		super.setId(id);
		super.setName(name);
	}
	
	public List<Task> getTasks(){
		return Collections.unmodifiableList(tasks);
	}
	
	public void addTask(Task task) {
		tasks.add(task);
		task.setVolunteer(this);
	}
	
	public void removeTask(Task task) {
		task.setVolunteer(null);
		tasks.remove(task);
	}
	
	
}
