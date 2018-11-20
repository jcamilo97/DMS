package com.ubosque.sgdaubosque.controller;

import com.google.gson.Gson;
import com.ubosque.sgdaubosque.exception.AppException;
import com.ubosque.sgdaubosque.model.Affair;
import com.ubosque.sgdaubosque.model.Document;
import com.ubosque.sgdaubosque.model.User;
import com.ubosque.sgdaubosque.payload.DocumentRequest;
import com.ubosque.sgdaubosque.repository.AffairRepository;
import com.ubosque.sgdaubosque.repository.DocumentRepository;
import com.ubosque.sgdaubosque.repository.UserRepository;
import com.ubosque.sgdaubosque.services.EmailService;
import com.ubosque.sgdaubosque.services.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * DocumentController
 */

@RestController()
@RequestMapping("/api")
public class DocumentController {

    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);
    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AffairRepository affairRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UploadFileService uploadFileService;

    @GetMapping("/documents")
    public List<Document> getAllAffair() {
        return documentRepository.findAll();
    }

    @PostMapping("/document")
    public ResponseEntity<?> createDocument(@RequestParam Map<String, String> documentInfo, @RequestParam("file") MultipartFile file ) {

        Gson gson = new Gson();
        String pathAnnexe = "";
        DocumentRequest document = gson.fromJson(documentInfo.toString(), DocumentRequest.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        if(file.isEmpty()){
            return  new ResponseEntity<Object>("Debe cargarse archivo", HttpStatus.OK);
        }
        Document doc = new Document();
        try {
            pathAnnexe =  uploadFileService.saveFile(file);
            doc.setDocument(document.getTitle(), simpleDateFormat.parse(document.getDateDoc()), document.getOrigin(),
                    document.getComments());

        } catch (IOException ex){
            logger.error("Invalid file"+ex.getMessage());
            ex.printStackTrace();
        } catch ( ParseException ex) {

        }

        doc.setAnnexe(pathAnnexe);
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
        
        try {
            emailService.sendMail(target.getEmail(), "Tienes una radicacion Nueva");
        } catch (Exception e) {
                //TODO: handle exception
                return new ResponseEntity<Object>("{message: La radicacion fue exitosa pero tenemos problemas para notificar al destinario via email}", HttpStatus.OK);
        }

        return  new ResponseEntity<Object>("{\"mensaje\":\"Archivo load\"}", HttpStatus.OK);
        //return "{message:Success}";
    }
}
