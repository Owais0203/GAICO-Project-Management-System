package com.java.springBoot.backend.Service;

import com.java.springBoot.backend.Model.Charter;
import com.java.springBoot.backend.Model.Project;
import com.java.springBoot.backend.Repository.CharterRepository;
import com.java.springBoot.backend.Request.CharterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharterServiceImpl implements CharterService {

    private final CharterRepository charterRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    public CharterServiceImpl(CharterRepository charterRepository) {
        this.charterRepository = charterRepository;
    }

    @Override
    public Charter createCharter(CharterRequest charterRequest) throws Exception {

        Project project = projectService.getProjectById(charterRequest.getProjectID());
        Charter newCharter = new Charter();
        newCharter.setTitle(charterRequest.getTitle());
        newCharter.setDescription(charterRequest.getDescription());
        newCharter.setSponsors(charterRequest.getSponsors());
        newCharter.setObjective(charterRequest.getObjective());
        newCharter.setDeliverables(charterRequest.getDeliverables());
        newCharter.setApproved(charterRequest.getApproved());
        newCharter.setProject(project);
        newCharter.setProjectID(charterRequest.getProjectID());

        return charterRepository.save(newCharter);
    }

    @Override
    public List<Charter> getAllCharters() {
        return charterRepository.findAll();
    }

    @Override
    public Charter getCharterById(Long id) {
        return charterRepository.findById(id).orElse(null);
    }

    @Override
    public Charter updateCharter(Long id, Charter charter) {
        if (charterRepository.existsById(id)) {
            charter.setId(charter.getId());
            charter.setDescription(charter.getDescription());
            charter.setDeliverables(charter.getDeliverables());
            charter.setApproved(charter.getApproved());
            charter.setProject(charter.getProject());
            charter.setObjective(charter.getObjective());
            charter.setSponsors(charter.getSponsors());
            charter.setTitle(charter.getTitle());

            return charterRepository.save(charter);
        }
        return null;
    }

    @Override
    public void deleteCharter(Long id) {
        if (charterRepository.existsById(id)) {
            charterRepository.deleteById(id);
        }
    }

    @Override
    public Charter getChartersByProjectId(Long projectId) {
        return charterRepository.findByProjectId(projectId);
    }
}
