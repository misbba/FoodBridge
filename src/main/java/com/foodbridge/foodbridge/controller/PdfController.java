package com.foodbridge.foodbridge.controller;

import com.foodbridge.foodbridge.entity.Food;
import com.foodbridge.foodbridge.repository.FoodRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PdfController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping("/downloadReport")
    public void downloadReport(HttpServletResponse response) throws Exception {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "attachment; filename=FoodBridge_Report.pdf");

        Document document = new Document();

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        document.add(new Paragraph("FoodBridge Donation Report"));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(4);

        table.addCell("Food");
        table.addCell("Quantity");
        table.addCell("Location");
        table.addCell("Status");

        List<Food> foods = foodRepository.findAll();

        for (Food food : foods) {

            table.addCell(food.getFoodName());
            table.addCell(food.getQuantity());
            table.addCell(food.getLocation());
            table.addCell(food.getStatus());

        }

        document.add(table);

        document.close();
    }
}