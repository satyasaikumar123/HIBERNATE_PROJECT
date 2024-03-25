package organizer;

import com.event.EventEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Organizer")
public class OrganizerEntity {

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Organizer ID: ").append(id).append("\n");
	    sb.append("Organizer Name: ").append(name).append("\n");
	    sb.append("Organizer Email: ").append(email).append("\n");
	    return sb.toString();
	}


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organizer_id")
    private Long id;

    @Column(name = "organizer_name")
    private String name;

    @Column(name = "organizer_email")
    private String email;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public List<EventEntity> getEvents() {
		return events;
	}

	public void setEvents(List<EventEntity> events) {
		this.events = events;
	}

	// Assuming One-to-Many relationship with Event
    @OneToMany(mappedBy = "organizer")
    private List<EventEntity> events;

    public OrganizerEntity() {
        // Default constructor
    }

    public OrganizerEntity(Long id, String name, String email, List<EventEntity> events) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.events = events;
    }

    // Getters and setters
    // toString method
}
