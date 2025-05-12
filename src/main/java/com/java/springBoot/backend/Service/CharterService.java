package com.java.springBoot.backend.Service;

import com.java.springBoot.backend.Model.Charter;

import java.util.List;

public interface CharterService {

    // Method to create a charter
    Charter createCharter(Charter charter);

    // Method to get all charters
    List<Charter> getAllCharters();

    // Method to get a charter by ID
    Charter getCharterById(Long id);

    // Method to update a charter
    Charter updateCharter(Long id, Charter charter);

    // Method to delete a charter
    void deleteCharter(Long id);

    // Method to get charters by project ID
    Charter getChartersByProjectId(Long projectId);
}
