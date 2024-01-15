package com.example.PdfReportBackend.service;

import com.example.PdfReportBackend.entity.User;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
@Service
public class PdfService {
    public byte[] generateUserDetailsPDF(User user) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.showText("User Details PDF");
                contentStream.newLineAtOffset(0, -20);

                // Add user details
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.showText("Username:"+user.getUsername());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Date of Birth:"+user.getDateOfBirth());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Place:"+user.getPlace());

                contentStream.endText();
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

//    public byte[] generatePdf(User user) throws IOException {
//        try (PDDocument document = new PDDocument()) {
//            PDPage page = new PDPage();
//            document.addPage(page);
//
//            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
//                contentStream.beginText();
//                contentStream.newLineAtOffset(100, 700);
//                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
//                contentStream.showText("User Details PDF");
//                contentStream.newLineAtOffset(0, -20);
//
//                // Add user details
//                contentStream.setFont(PDType1Font.HELVETICA, 12);
//                contentStream.showText("Username: Guru");
//                contentStream.newLineAtOffset(0, -20);
//                contentStream.showText("Date of Birth: 1990-01-01");
//                contentStream.newLineAtOffset(0, -20);
//                contentStream.showText("Place: Some Place");
//
//                contentStream.endText();
//            }
//
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            document.save(byteArrayOutputStream);
//            return byteArrayOutputStream.toByteArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new byte[0];
//        }
//    }
}
