package com.geektcp.alpha.util.office.service;


import com.geektcp.alpha.util.office.model.BatchInfo;
import com.geektcp.alpha.util.office.util.XWpfTableUtils;
import com.geektcp.alpha.util.office.util.XWpfUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author haiyang on 3/26/20 3:02 PM.
 */
public class ExportWordService {
    private XWpfTableUtils wpfTableUtils = null;
    private XWpfUtils xWpfUtils = null;

    public ExportWordService() {
        wpfTableUtils = new XWpfTableUtils();
        xWpfUtils = new XWpfUtils();
    }

    /**
     * 创建好文档的基本 标题，表格  段落等部分
     */
    public XWPFDocument createXWPFDocument() {
        XWPFDocument doc = new XWPFDocument();
//        createTitleParagraph(doc);
//        createTableParagraph(doc, 10, 6);
        return doc;
    }

    /**
     * 创建表格的标题样式
     */
    public void createTitleParagraph(XWPFDocument document) {
        XWPFParagraph titleParagraph = document.createParagraph();    //新建一个标题段落对象（就是一段文字）
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);//样式居中
        XWPFRun titleFun = titleParagraph.createRun();    //创建文本对象
//        titleFun.setText(titleName); //设置标题的名字
        titleFun.setBold(true); //加粗
        titleFun.setColor("000000");//设置颜色
        titleFun.setFontSize(25);    //字体大小
//        titleFun.setFontFamily("");//设置字体
        //...
        titleFun.addBreak();    //换行
    }

    /**
     * 设置表格
     */
    public void createTableParagraph(XWPFDocument document, int rows, int cols) {
//        wpfTableUtils.createTable(xdoc, rowSize, cellSize, isSetColWidth, colWidths)
        XWPFTable infoTable = document.createTable(rows, cols);
        wpfTableUtils.setTableWidthAndHAlign(infoTable, "9072", STJc.CENTER);
        // 合并表格
        wpfTableUtils.mergeCellsHorizontal(infoTable, 1, 1, 5);
        wpfTableUtils.mergeCellsVertically(infoTable, 0, 3, 6);
        for (int col = 3; col < 7; col++) {
            wpfTableUtils.mergeCellsHorizontal(infoTable, col, 1, 5);
        }
        // 设置表格样式
        List<XWPFTableRow> rowList = infoTable.getRows();
        for (int i = 0; i < rowList.size(); i++) {
            XWPFTableRow infoTableRow = rowList.get(i);
            List<XWPFTableCell> cellList = infoTableRow.getTableCells();
            for (XWPFTableCell cell : cellList) {
                XWPFParagraph cellParagraph = cell.getParagraphArray(0);
                cellParagraph.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun cellParagraphRun = cellParagraph.createRun();
                cellParagraphRun.setFontSize(12);
                if (i % 2 != 0) {
                    cellParagraphRun.setBold(true);
                }
            }
        }
        wpfTableUtils.setTableHeight(infoTable, 560, STVerticalJc.CENTER);
    }

    /**
     * 往表格中填充数据
     */
    @SuppressWarnings("unchecked")
    public void exportTextWord(Map<String, Object> dataList, XWPFDocument document, String savePath) {
        XWPFParagraph paragraph = document.getParagraphArray(0);
        XWPFRun titleFun = paragraph.getRuns().get(0);
        titleFun.setText(String.valueOf(dataList.get("TITLE")));

        List<List<Object>> tableData = (List<List<Object>>) dataList.get("TABLEDATA");
        XWPFTable table = document.getTableArray(0);
        fillTableData(table, tableData);
        xWpfUtils.saveDocument(document, savePath);
    }

    public void exportPictureWord(List<BatchInfo> batchInfoList, String savePath) {
        try (XWPFDocument doc = new XWPFDocument()) {
            for (BatchInfo batchInfo: batchInfoList) {
                ByteArrayInputStream fileInputStream = new ByteArrayInputStream(batchInfo.getBytes());
                XWPFParagraph paragraph = doc.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText(batchInfo.getTitle());
                run.addCarriageReturn();
                run.addPicture(fileInputStream, XWPFDocument.PICTURE_TYPE_PNG, null, Units.toEMU(400), Units.toEMU(50));
                doc.write(new FileOutputStream(savePath));
                run.addCarriageReturn();
                run.addCarriageReturn();
            }
            xWpfUtils.saveDocument(doc, savePath);
        } catch (Exception e) {
            // do something
            System.out.println(e.getMessage());
        }
    }

    /**
     * 往表格中填充数据
     */
    public void fillTableData(XWPFTable table, List<List<Object>> tableData) {
        List<XWPFTableRow> rowList = table.getRows();
        for (int i = 0; i < tableData.size(); i++) {
            List<Object> list = tableData.get(i);
            List<XWPFTableCell> cellList = rowList.get(i).getTableCells();
            for (int j = 0; j < list.size(); j++) {
                XWPFParagraph cellParagraph = cellList.get(j).getParagraphArray(0);
                XWPFRun cellParagraphRun = cellParagraph.getRuns().get(0);
                cellParagraphRun.setText(String.valueOf(list.get(j)));
            }
        }
    }
}