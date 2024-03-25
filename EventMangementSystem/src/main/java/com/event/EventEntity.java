package com.event;

import java.util.HashSet;

//import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import categories.CategoryEntity;
import organizer.OrganizerEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EventEntity") // Specify the table name
public class EventEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventId")
    private int eventId;

	@Column(name = "event_name")
    private String eventName;

    @Column(name = "event_description")
    private String eventDescription;

    @Column(name = "event_date")
    private String eventDate;

    @Column(name = "event_venue")
    private String eventVenue;
    
    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private OrganizerEntity organizer;   

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}



	public String getEventVenue() {
		return eventVenue;
	}

	public void setEventVenue(String eventVenue) {
		this.eventVenue = eventVenue;
	}

    
	// Getters and setters

    @Override
	public String toString() {
		return "EventEntity [eventId=" + eventId + ", eventName=" + eventName + ", eventDescription=" + eventDescription
				+ ", eventDate=" + eventDate + ", eventVenue=" + eventVenue + ", organizer=" + organizer + "]";
	}

	public OrganizerEntity getOrganizer() {
        return organizer;
    }

    public void setOrganizer(OrganizerEntity organizer) {
        this.organizer = organizer;
    }

	// Default constructor
    public EventEntity() {
    }

	public EventEntity( String eventName, String eventDescription, String eventDate, String eventVenue,
			OrganizerEntity organizer) {
		super();
		
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
		this.eventVenue = eventVenue;
		this.organizer = organizer;
		
	}

}


