package participants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
@Entity
@Table(name="ParticipantEntity")
public class ParticipantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participantId")
    private int participantId;

    @Column(name = "participant_name")
    private String participantName;
    @Email
    @Column(name = "participant_email")
    private String participantEmail;

    @Column(name = "participant_phone")
    private String participantPhone;

	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}
    
	public String getParticipantEmail() {
		return participantEmail;
	}

	public void setParticipantEmail(String participantEmail) {
		this.participantEmail = participantEmail;
	}

	public String getParticipantPhone() {
		return participantPhone;
	}

	public void setParticipantPhone(String participantPhone) {
		this.participantPhone = participantPhone;
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Participant ID: ").append(participantId).append("\n");
	    sb.append("Participant Name: ").append(participantName).append("\n");
	    sb.append("Participant Email: ").append(participantEmail).append("\n");
	    sb.append("Participant Phone: ").append(participantPhone).append("\n");
	    return sb.toString();
	}


	public ParticipantEntity( String participantName, String participantEmail,
			String participantPhone) {
		super();
		
		this.participantName = participantName;
		this.participantEmail = participantEmail;
		this.participantPhone = participantPhone;
	}

	public ParticipantEntity() {
		super();
	}

    

}
