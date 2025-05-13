package com.java.springBoot.backend.Controller;

import com.java.springBoot.backend.DTO.CharterDTO;
import com.java.springBoot.backend.Model.Charter;
import com.java.springBoot.backend.Request.CharterRequest;
import com.java.springBoot.backend.Service.CharterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/charter")
public class CharterController {

    @Autowired
    private final CharterService charterService;

    public CharterController(CharterService charterService) {
        this.charterService = charterService;
    }

    // Create charter
    @PostMapping
    public ResponseEntity<CharterDTO> createCharter(@RequestBody CharterRequest charterRequest) throws Exception {
        Charter createdCharter = charterService.createCharter(charterRequest);

        CharterDTO charterDTO = new CharterDTO();
        charterDTO.setId(createdCharter.getId());
        charterDTO.setApproved(createdCharter.getApproved());
        charterDTO.setDescription(createdCharter.getDescription());
        charterDTO.setTitle(createdCharter.getTitle());
        charterDTO.setDeliverables(createdCharter.getDeliverables());
        charterDTO.setObjective(createdCharter.getObjective());
        charterDTO.setProjectID(createdCharter.getProjectID());
        charterDTO.setProject(createdCharter.getProject());
        charterDTO.setSponsors(createdCharter.getSponsors());

        return ResponseEntity.ok(charterDTO);
    }

    // Get all charters
    @GetMapping("/all")
    public List<Charter> getAllCharters() {
        return charterService.getAllCharters();
    }

    // Get charter by ID
    @GetMapping("/{id}")
    public Charter getCharterById(@PathVariable Long id) {
        return charterService.getCharterById(id);
    }

    // Update charter
    @PutMapping("/{id}")
    public Charter updateCharter(@PathVariable Long id, @RequestBody Charter charter) {
        return charterService.updateCharter(id, charter);
    }

    // Delete charter
    @DeleteMapping("/{id}")
    public void deleteCharter(@PathVariable Long id) {
        charterService.deleteCharter(id);
    }

    // Get charter by project ID
    @GetMapping("/project/{projectId}")
    public Charter getChartersByProjectId(@PathVariable Long projectId) {
        return charterService.getChartersByProjectId(projectId);
    }


}
