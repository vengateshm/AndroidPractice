package dev.vengateshm.excel_to_pdf

import com.itextpdf.text.Document
import com.itextpdf.text.Phrase
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

fun convertToPdf(ipFile: File, opFile: File) {
    //First we read the XLSX in binary format into FileInputStream
    val inputDocument = FileInputStream(ipFile)
    // Read workbook into XSSFWorkbook
    val myXlsWorkbook = XSSFWorkbook(inputDocument)
    // Read worksheet into XSSFSheet
    val myWorksheet = myXlsWorkbook.getSheetAt(0)
    // To iterate over the rows
    val rowIterator: Iterator<Row> = myWorksheet.iterator()
    //We will create output PDF document objects at this point
    val itextXls2Pdf = Document()
    PdfWriter.getInstance(itextXls2Pdf, FileOutputStream(opFile))
    itextXls2Pdf.open()
    //we have 14 columns in the Excel sheet, so we create a PDF table with two columns
    val myTable = PdfPTable(14)
    //cell object to capture data
    var tableCell: PdfPCell?
    //Loop through rows.
    while (rowIterator.hasNext()) {
        val row: Row = rowIterator.next()
        if (row.rowNum > 4) {
            val cellIterator: Iterator<Cell> = row.cellIterator()
            while (cellIterator.hasNext()) {
                val cell: Cell = cellIterator.next() //Fetch CELL
                when (cell.cellType) {
                    CellType.STRING -> {
                        //Push the data from Excel to PDF Cell
                        tableCell = PdfPCell(Phrase(cell.stringCellValue))
                        myTable.addCell(tableCell)
                    }
                    else -> {}
                }
                //next line
            }
        }
    }
    //Finally add the table to PDF document
    itextXls2Pdf.add(myTable)
    itextXls2Pdf.close()
    //we created our pdf file..

    inputDocument.close() //close xlsx
}