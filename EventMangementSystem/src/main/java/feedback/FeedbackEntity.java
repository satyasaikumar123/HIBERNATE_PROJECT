package feedback;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.event.EventEntity;

import participants.ParticipantEntity;



@Entity
@Table(name = "feedback") // specify the table name if necessary
public class FeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "feedback_id") // specify the column name if necessary
    private int feedbackId;
    
    @ManyToOne
    @JoinColumn(name = "eventid") // specify the foreign key column name if necessary
    private EventEntity eventId;
    
    @ManyToOne
    @JoinColumn(name = "participantid") // specify the foreign key column name if necessary
    private ParticipantEntity participantId;
    
    @Column(name = "feedback_message") // specify the column name if necessary
    private String feedbackMessage;
    
    @Column(name = "feedback_rating") // specify the column name if necessary
    private int feedbackRating;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Feedback ID: ").append(feedbackId).append("\n");
        sb.append("Event ID: ").append(eventId).append("\n");
        sb.append("Participant ID: ").append(participantId).append("\n");
        sb.append("Feedback Message: ").append(feedbackMessage).append("\n");
        sb.append("Feedback Rating: ").append(feedbackRating).append("\n");
        return sb.toString();
    }

	public FeedbackEntity(int feedbackId, EventEntity event, ParticipantEntity participant, String feedbackMessage,
			int feedbackRating) {
		super();
		this.feedbackId = feedbackId;
		this.eventId = event;
		this.participantId = participant;
		this.feedbackMessage = feedbackMessage;
		this.feedbackRating = feedbackRating;
	}
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public EventEntity getEvent() {
		return eventId;
	}
	public void setEvent(EventEntity event) {
		this.eventId = event;
	}
	public FeedbackEntity() {
		super();
	}
	public ParticipantEntity getParticipant() {
		return participantId;
	}
	public void setParticipant(ParticipantEntity participant) {
		this.participantId = participant;
	}
	public String getFeedbackMessage() {
		return feedbackMessage;
	}
	public void setFeedbackMessage(String feedbackMessage) {
		this.feedbackMessage = feedbackMessage;
	}
	public int getFeedbackRating() {
		return feedbackRating;
	}
	public void setFeedbackRating(int feedbackRating) {
		this.feedbackRating = feedbackRating;
	}
	

}
