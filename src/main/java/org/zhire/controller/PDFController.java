package org.zhire.controller;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.zhire.demo.spring.ioc.IOCUser;
import org.zhire.service.PdfExportService;
import org.zhire.service.impl.PdfView;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/pdf")
@RestController
public class PDFController {


    // 导出PDF
    @GetMapping("/export")
    public ModelAndView exportPdf(String userName, String note) {
        // 查询用户信息列表
        List<IOCUser> userList = Arrays.asList(new IOCUser(1, "111", "111"), new IOCUser(2, "2222", "1222"));
        // 定义 PDF 视图
        View view = new PdfView(exportService());
        ModelAndView mv = new ModelAndView();
        // 设置视图
        mv.setView(view);
        // 加入数据模型
        mv.addObject("userList", userList);
        return mv;
    }

    // 导出 PDF 自定义
    @SuppressWarnings("unchecked")
    private PdfExportService exportService() {
        // 使用 Lambda 表达式定义自定义导出
        return (model, document, writer, request, response) -> {
            try {
                // A4 纸张国 10.6 视图和视图解析器 223
                document.setPageSize(PageSize.A4);
                // 标题
                document.addTitle("用户信息");
                // 换行
                document.add(new Chunk("\n"));
                // 表格，3 列
                PdfPTable table = new PdfPTable(3);
                // 单元格
                PdfPCell cell = null;
                // 字体，定义为蓝色加粗
                Font f8 = new Font();
                f8.setColor(Color.BLUE);
                f8.setStyle(Font.BOLD);
                // 标题
                cell = new PdfPCell(new Paragraph("id", f8));
                // 居中对齐
                cell.setHorizontalAlignment(1);
                // 将单元格加入表格
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("user_name", f8));
                // 居中对齐
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("note", f8));
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                // 获取数据模型中的用户列表
                List<IOCUser> userList = (List<IOCUser>) model.get("userList");
                for (IOCUser user : userList) {
                    document.add(new Chunk("\n"));
                    cell = new PdfPCell(new Paragraph(user.getId() + ""));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(user.getName()));
                    table.addCell(cell);
                    String note = user.getAddress() == null ? "" : user.getAddress();
                    cell = new PdfPCell(new Paragraph(note));
                    table.addCell(cell);
                }
                // 在文档中加入表格
                document.add(table);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

}
