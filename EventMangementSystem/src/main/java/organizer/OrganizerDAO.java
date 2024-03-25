package organizer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.event.EventEntity;

import java.util.List;

public class OrganizerDAO {
    private final SessionFactory sessionFactory;

    public OrganizerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addOrganizer(OrganizerEntity organizer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(organizer);
            transaction.commit();
        }
    }

    public OrganizerEntity getOrganizerById(Long organizerId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(OrganizerEntity.class, organizerId);
        }
    }

    public List<OrganizerEntity> getAllOrganizers() {
        try (Session session = sessionFactory.openSession()) {
            Query<OrganizerEntity> query = session.createQuery("FROM OrganizerEntity", OrganizerEntity.class);
            return query.list();
        }
    }

    public void updateOrganizer(OrganizerEntity organizer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(organizer);
            transaction.commit();
        }
    }

    public void deleteOrganizer(Long id) {
        try (Session session = sessionFactory.openSession()) {
            OrganizerEntity organizer = session.get(OrganizerEntity.class, id);
            if (organizer != null) {
                Transaction transaction = session.beginTransaction();
                session.delete(organizer);
                transaction.commit();
            }
        }
    }
    public List<EventEntity> getEventsByOrganizerId(Long organizerId) {
        try (Session session = sessionFactory.openSession()) {
            Query<EventEntity> query = session.createQuery(
                "SELECT e FROM EventEntity e WHERE e.organizer.id = :organizerId",
                EventEntity.class
            );
            query.setParameter("organizerId", organizerId);
            return query.list();
        } catch (Exception e) {
            // Handle exception appropriately
            e.printStackTrace();
        }
        return null;
    }



}

