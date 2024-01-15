package com.example.PdfReportBackend.controller;

import com.example.PdfReportBackend.entity.User;
import com.example.PdfReportBackend.repository.UserRepository;
import com.example.PdfReportBackend.service.PdfService;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PdfService pdfService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok("User added successfully!");
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<User> searchUser(@PathVariable String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
//    @GetMapping("/download-pdf/{username}")
//    public ResponseEntity<byte[]> downloadUserDetailsPDF(@PathVariable String username) {
//        Optional<User> userOptional = userRepository.findByUsername(username);
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            try {
//
//                byte[] pdfByteArray = pdfService.generatePdf(user);
//
//                HttpHeaders headers = new HttpHeaders();
//                headers.setContentType(MediaType.APPLICATION_PDF);
//                headers.setContentDispositionFormData("attachment", "user_details.pdf");
//
//                return new ResponseEntity<>(pdfByteArray, headers, HttpStatus.OK);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return ResponseEntity.status(HttpStatus.OK).body(("Error generating PDF: " + e.getMessage()).getBytes());
//            }
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @GetMapping("/download-pdf/{username}")
//    public ResponseEntity<byte[]> downloadUserDetailsPDF(@PathVariable String username) {
//        Optional<User> userOptional = userRepository.findByUsername(username);
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            try {
//                byte[] pdfByteArray = pdfService.generatePdf(user);
//
//                HttpHeaders headers = new HttpHeaders();
//                headers.setContentType(MediaType.APPLICATION_PDF);
//                headers.setContentDispositionFormData("attachment", "user_details.pdf");
//
//                return new ResponseEntity<>(pdfByteArray, headers, HttpStatus.OK);
//            } catch (IOException e) {
//                // Handle exception (e.g., log it)
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//            }
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    @GetMapping("/download-pdf/{username}")
    public ResponseEntity<byte[]> downloadUserDetailsPDF(@PathVariable String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.get();

        byte[] pdfBytes = pdfService.generateUserDetailsPDF(user); // Generate PDF

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "user_details.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }


}
