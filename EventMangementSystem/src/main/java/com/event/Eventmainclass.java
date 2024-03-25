package com.event;

import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import categories.CategoryDao;
import categories.CategoryEntity;
import feedback.FeedbackDao;
import feedback.FeedbackEntity;
import organizer.OrganizerDAO;

import organizer.OrganizerEntity;
import participants.ParticipantDao;
import participants.ParticipantEntity;

public class Eventmainclass {
    private static final String ADMIN_USERNAME = "satya";
    private static final String ADMIN_PASSWORD = "satya12";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();        
        Eventdao edao = new Eventdao(sessionFactory);
        CategoryDao cat = new CategoryDao(sessionFactory);
        FeedbackDao fdao = new FeedbackDao(sessionFactory);
        ParticipantDao pdao = new ParticipantDao(sessionFactory);
        OrganizerDAO odao = new OrganizerDAO(sessionFactory);

        int choice;
        do {
        	System.out.println("Welcome to Event Management System");
            System.out.println("Choose your role:");
            System.out.println("1. Admin");
            System.out.println("2. Participant");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                	 String username;
                     String password;
                     System.out.print("Enter username: ");
                     username = scanner.nextLine();
                     System.out.print("Enter password: ");
                     password = scanner.nextLine();
                     int userType = authenticateUser(username, password);
                     if (userType == 0) {
                         System.out.println("Invalid username or password. Access denied.");
                         return;
                     }
                    adminActions(scanner, edao, cat,odao);
                    break;
                case 2:
                    participantActions(scanner, edao, pdao, fdao);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid role.");
            }
        } while (choice != 1 && choice != 2);

        scanner.close();
        sessionFactory.close();
    }

    private static void adminActions(Scanner scanner, Eventdao edao, CategoryDao cat,OrganizerDAO odao ) {
        int choice;
        do {
            System.out.println("Admin Actions:");
            System.out.println("1. Register new Event :");
            System.out.println("2. Find Event by ID");
            System.out.println("3. List Of All the  Events");
            System.out.println("4. update Event details:");
            System.out.println("5. remove Event :");
            System.out.println("6. Add different Categories of Events:");
            System.out.println("7. delete one category from categories:");
            System.out.println("8. Add organizer");
            System.out.println("9. list of events organized by organizer:"); 
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter event details:");
                    System.out.print("Enter Event Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Event Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Event Date: ");
                    String date = scanner.nextLine();
//                    System.out.print("Enter Event Time: ");
//                    String time = scanner.nextLine();
                    System.out.print("Enter Event Venue: ");
                    String venue = scanner.nextLine();
                    System.out.print("Enter Event organizer id: ");
                    Long organizerId = scanner.nextLong();
                    scanner.nextLine(); // Consume newline character
                    OrganizerEntity organizer = odao.getOrganizerById(organizerId);

                   

                 // Check if organizer exists
                 if (organizer != null) {
                     // Organizer found, proceed to create EventEntity object
                	 EventEntity event =new EventEntity(name,description,date,venue,organizer);
                     edao.saveEvents(event);
                     System.out.println("Event added successfully.");
                 } else {
                     // Organizer not found, handle the case appropriately
                     System.out.println("Error: Organizer not found.");
                 }
                 break;
                case 2:
                    System.out.print("Enter event ID to find: ");
                    int eventId = scanner.nextInt();
                    EventEntity foundEvent = edao.findbyid(eventId);
                    if (foundEvent != null) {
                        System.out.println("Found Event: " + foundEvent);
                    } else {
                        System.out.println("Event not found.");
                    }
                    break;
                case 3:
                    System.out.println("All Events:");
                    List<EventEntity> allEvents = edao.findall();
                    for (EventEntity e : allEvents) {
                        System.out.println(e);
                    }
                    break;
                case 4:
                    System.out.print("Enter event ID to update: ");
                    int updateEventId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    
                    // Fetch event by ID
                    EventEntity updateEvent = edao.findbyid(updateEventId);
                    
                    if (updateEvent != null) {
                        System.out.println("Enter new details for the event:");
                        System.out.print("Enter Event Name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter Event Description: ");
                        String newDescription = scanner.nextLine();
                        System.out.print("Enter Event Date: ");
                        String newDate = scanner.nextLine();
//                        System.out.print("Enter Event Time: ");
//                        String newTime = scanner.nextLine();
                        System.out.print("Enter Event Venue: ");
                        String newVenue = scanner.nextLine();
                        System.out.print("Enter new Organizer ID: ");
                        Long newOrganizerId = scanner.nextLong();
                        scanner.nextLine(); // Consume newline character
                        
                        // Fetch organizer
                        OrganizerEntity newOrganizer = odao.getOrganizerById(newOrganizerId);
                        
                        // Check if organizer exists
                        if (newOrganizer != null) {
                            // Update event details
                            updateEvent.setEventName(newName);
                            updateEvent.setEventDescription(newDescription);
                            updateEvent.setEventDate(newDate);
//                            updateEvent.setEventTime(newTime);
                            updateEvent.setEventVenue(newVenue);
                            updateEvent.setOrganizer(newOrganizer);
                            
                            edao.updateById(updateEvent);
                            System.out.println("Event updated successfully.");
                        } else {
                            // Organizer not found, handle the case appropriately
                            System.out.println("Error: Organizer not found.");
                        }
                    } else {
                        System.out.println("Event not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter event ID to delete: ");
                    int deleteEventId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    
                    // Fetch event by ID
                    EventEntity deleteEvent = edao.findbyid(deleteEventId);
                    
                    if (deleteEvent != null) {
                        // Delete event
                        edao.deleteById(deleteEvent);
                        System.out.println("Event deleted successfully.");
                    } else {
                        System.out.println("Event not found.");
                    }
                    break;

                case 6:
                    System.out.println("Enter category details:");
                    System.out.print("Name of the event: ");
                    String categoryName = scanner.nextLine();
                    System.out.print("Price: ");
                    int price = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    CategoryEntity category = new CategoryEntity( categoryName, price);
                    cat.saveCategory(category);
                    System.out.println("Category added successfully.");
                    break;
                case 7:
                    System.out.print("Enter category ID to delete: ");
                    int categoryIdToDelete = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    
                    // Delete the category
                    cat.deleteCategory(categoryIdToDelete);
                    System.out.println("Category deleted successfully.");
                    break;

                case 8:
                	  System.out.println("Enter organizer details:");
                      System.out.print("Name: ");
                      String name1 = scanner.nextLine();
                      System.out.print("Email: ");
                      String email = scanner.nextLine();

                      // Create an OrganizerEntity object
                      OrganizerEntity organizer1 = new OrganizerEntity();
                      organizer1.setName(name1);
                      organizer1.setEmail(email);

                      // Save organizer details using OrganizerDAO
                      odao.addOrganizer(organizer1);
                      System.out.println("Organizer details saved successfully.");
                	break;
                case 9:
                    System.out.print("Enter organizer ID to fetch events: ");
                    Long organizerId1 = scanner.nextLong();
                    scanner.nextLine(); // Consume newline character
                    
                    // Call the getEventsByOrganizerId method
                    List<EventEntity> events = odao.getEventsByOrganizerId(organizerId1);
                    
                    // Check if events are found
                    if (events != null) {
                        System.out.println("Events associated with organizer:");
                        for (EventEntity event1 : events) {
                            System.out.println(event1);
                        }
                    } else {
                        System.out.println("No events found for the organizer.");
                    }
                    break;
       
                case 10:
                   System.out.println("Exit from the admin");
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid operation.");
            }
        } while (choice != 11);
    }

    private static void participantActions(Scanner scanner, Eventdao edao, ParticipantDao pdao, FeedbackDao fdao) {
        int choice;
        do {
            System.out.println("organizer Actions:");
            System.out.println("1. Add Feedback");
            System.out.println("2. Fetch Feedback by ID");
            System.out.println("3. Fetch All Feedback");
            System.out.println("4. Add Participant");
            System.out.println("5. Fetch Participant by ID");
            System.out.println("6. Fetch All Participants");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
            case 1:
                System.out.println("Enter feedback details:");
                System.out.print("Event ID: ");
                int eventId = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                EventEntity event = edao.findbyid(eventId);
                if (event != null) {
                    System.out.print("Participant ID: ");
                    int participantId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    ParticipantEntity participant = pdao.findById(participantId);
                    if (participant != null) {
                        System.out.print("Feedback Message: ");
                        String feedbackMessage = scanner.nextLine();
                        System.out.print("Feedback Rating: ");
                        int feedbackRating = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        FeedbackEntity feedback = new FeedbackEntity(eventId, event, participant, feedbackMessage, feedbackRating);
                        fdao.saveFeedback(feedback);
                        System.out.println("Feedback added successfully.");
                    } else {
                        System.out.println("Participant not found.");
                    }
                } else {
                    System.out.println("Event not found.");
                }
                break;
            case 2:
                System.out.print("Enter feedback ID to fetch: ");
                int feedbackId = scanner.nextInt();
                FeedbackEntity fetchedFeedback = fdao.getFeedbackById(feedbackId);
                if (fetchedFeedback != null) {
                    System.out.println("Found Feedback: " + fetchedFeedback);
                } else {
                    System.out.println("Feedback not found.");
                }
                break;
            case 3:
            	 List<FeedbackEntity> allFeedback = fdao.getAllFeedback();
            	    if (!allFeedback.isEmpty()) {
            	        System.out.println("All Feedback:");
            	        for (FeedbackEntity feedback : allFeedback) {
            	            System.out.println(feedback);
            	        }
            	    } else {
            	        System.out.println("No feedback found.");
            	    }
            	    break;
            case 4:
                System.out.println("Enter participant details:");
                System.out.print("Participant Name: ");
                String participantName = scanner.nextLine();
                System.out.print("Participant Email: ");
                String participantEmail = scanner.nextLine();
                System.out.print("Participant Phone Number: ");
                String participantPhone = scanner.nextLine();
                ParticipantEntity newParticipant = new ParticipantEntity(participantName, participantEmail, participantPhone);
                pdao.saveparticipant(newParticipant);
                System.out.println("Participant added successfully.");
                break;
            case 5:
                System.out.print("Enter participant ID to fetch: ");
                int participantId = scanner.nextInt();
                ParticipantEntity fetchedParticipant = pdao.findById(participantId);
                if (fetchedParticipant != null) {
                    System.out.println("Found Participant: " + fetchedParticipant);
                } else {
                    System.out.println("Participant not found.");
                }
                break;
            case 6:
            	 List<ParticipantEntity> allParticipants = pdao.getAllParticipants();
            	    if (!allParticipants.isEmpty()) {
            	        System.out.println("All Participants:");
            	        for (ParticipantEntity participant : allParticipants) {
            	            System.out.println(participant);
            	        }
            	    } else {
            	        System.out.println("No participants found.");
            	    }
            	    break;
            
            case 7:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid operation.");
        }
        } while (choice != 7);
    }


    private static int authenticateUser(String username, String password) {
        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            return 1; // Admin
        }
        // You might add more authentication logic for participants
        return 0; // Invalid user
    }
}
