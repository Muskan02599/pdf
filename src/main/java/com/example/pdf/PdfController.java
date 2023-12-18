package com.example.pdf;

// src/main/java/com/example/schedulermicroservice/controller/PdfController.java

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/generate")
    public void generatePdf(HttpServletResponse response) throws IOException, DocumentException {
        List<DataModel> data = pdfService.getdetails();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=data.pdf");

        byte[] pdfBytes = pdfService.generatePdf(data);
        response.getOutputStream().write(pdfBytes);
    }
}

