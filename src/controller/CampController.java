package controller;

import model.Camp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CampController {
    private List<Camp> camps;

    public CampController() {
        camps = new ArrayList<>();
    }

    // Create a new camp and add it to the list
    public Camp createCamp(int campId, String name, Date startDate, Date endDate, Date registrationCloseDate,
                           String userGroup, String location, int totalSlots, int campCommSlots, String description, String staffIC) {
        if (isCampIdUnique(campId)) {
            Camp camp = new Camp(campId, name, startDate, endDate, registrationCloseDate, userGroup, location, totalSlots, campCommSlots, description, staffIC);
            camps.add(camp);
            return camp; //Camp successfully added
        } else {
            System.out.println("Camp ID already exists. Please choose a unique ID.");
            return null; //Camp not added
        }
    }

    // Get a list of all camps
    public List<Camp> getAllCamps() {
        return camps;
    }

    // Find a camp by its ID
    public Camp findCampById(int campId) {
        for (Camp camp : camps) {
            if (camp.getCampId() == campId) {
                return camp;
            }
        }
        return null; //No such camp
    }

    // Update camp information, making sure campId remains the same or is changed to a unique ID
    public boolean updateCampInformation(int oldCampId, int newCampId, String name, Date startDate, Date endDate,
                                         Date registrationCloseDate, String userGroup, String location, int totalSlots,
                                         int campCommSlots, String description, String staffIC) {
        if (newCampId == oldCampId || isCampIdUnique(newCampId)) { //CampID is reused or a new unique ID
            Camp camp = findCampById(oldCampId);
            if (camp != null) {
                camp.setCampId(newCampId);
                camp.setName(name);
                camp.setStartDate(startDate);
                camp.setEndDate(endDate);
                camp.setRegistrationCloseDate(registrationCloseDate);
                camp.setUserGroup(userGroup);
                camp.setLocation(location);
                camp.setTotalSlots(totalSlots);
                camp.setCampCommSlots(campCommSlots);
                camp.setDescription(description);
                camp.setStaffIC(staffIC);
                return true; //Updated camp information
            } else {
                System.out.println("Camp not found for update.");
            }
        } else {
            System.out.println("New Camp ID is not unique. Please choose a unique ID.");
        }
        return false;
    }

    // Remove a camp from the list
    public boolean deleteCamp(int campId) {
        Camp camp = findCampById(campId);
        if (camp != null) {
            camps.remove(camp);
            return true; //Camp is removed
        }
        return false; // Camp not found
    }

    // Check if a camp ID is unique
    private boolean isCampIdUnique(int campId) {
        for (Camp camp : camps) {
            if (camp.getCampId() == campId) {
                return false; // Camp ID is not unique
            }
        }
        return true; // Camp ID is unique
    }
}
