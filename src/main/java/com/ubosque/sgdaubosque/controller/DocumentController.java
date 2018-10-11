package com.ubosque.sgdaubosque.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.ubosque.sgdaubosque.exception.AppException;
import com.ubosque.sgdaubosque.model.Affair;
import com.ubosque.sgdaubosque.model.User;
import com.ubosque.sgdaubosque.payload.DocumentRequest;
import com.ubosque.sgdaubosque.repository.AffairRepository;
import com.ubosque.sgdaubosque.repository.DocumentRepository;
import com.ubosque.sgdaubosque.model.Document;
import com.ubosque.sgdaubosque.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DocumentController
 */

@RestController()
@RequestMapping("/api")
public class DocumentController {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AffairRepository affairRepository;

    @GetMapping("/documents")
    public List<Document> getAllAffair() {
        return documentRepository.findAll();
    }

    @PostMapping("/document")
    public String createDocument(@Valid @RequestBody DocumentRequest document) {

        Document doc = new Document(document.getTitle(), document.getDateDoc(), document.getOrigin(),
                document.getDocNumber(), document.getAnnexe(), document.getComments());

        User target;
        target = userRepository.findById(UUID.fromString(document.getUserTarget()))
                .orElseThrow(() -> new AppException("User target not set."));

        User receive = userRepository.findById(UUID.fromString(document.getUserRecieve()))
                .orElseThrow(() -> new AppException("User receive not set."));

        Affair affair;
        affair = affairRepository.findById(document.getAffair())
                .orElseThrow(() -> new AppException("Affair Profile not set."));
        doc.setAffair(affair);
        doc.setUserTarget(target);
        doc.setUserRecieve(receive);

        Document resultDoc = documentRepository.save(doc);

        return "{message:Success}";
    }

}