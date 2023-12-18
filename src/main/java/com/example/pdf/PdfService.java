package com.example.pdf;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.List;
@Service
public class PdfService {
    @Autowired
    private  DataRepository dataRepository;

    public byte[] generatePdf(List<DataModel> data) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();

        for (DataModel item : data) {
            document.add(new Paragraph("ID: " + item.getId() + ", Name: " + item.getName()));
        }

        document.close();

        return byteArrayOutputStream.toByteArray();
    }
    public List<DataModel> getdetails(){
        List<DataModel> data= dataRepository.findAll();
        return data;
    }
}

