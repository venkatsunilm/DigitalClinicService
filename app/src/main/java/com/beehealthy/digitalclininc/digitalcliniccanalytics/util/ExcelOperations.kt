package com.beehealthy.digitalclininc.digitalcliniccanalytics.util

import android.os.Environment
import com.beehealthy.digitalclininc.digitalcliniccanalytics.dto.InfoDto
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.IndexedColors
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * Internal helper class to create workbook, sheet, updates headers and values and write to an
 * external storage.
 *
 */
internal class ExcelOperations(private val screenInfoMap: Map<String, InfoDto.ScreenInfo>,
                               sheetName: String = "Analytics",
                               workbookFile: String = "AnalyticsProject.xls") {

    private lateinit var workbook: HSSFWorkbook
    private lateinit var sheet: HSSFSheet
    private val headers = arrayOf<String>(
        AnalyticConstants.H_APPNAME,
            AnalyticConstants.H_APPDURATION, AnalyticConstants.H_SCREENNAME,
            AnalyticConstants.H_SCREENCOUNT, AnalyticConstants.H_EVENTNAME,
            AnalyticConstants.H_EVENTCOUNT)

    init {
        createWorkbook(sheetName)
        pushHeaders(workbook)
        pushValue(sheet)
        closeWorkbook(workbook, workbookFile)
    }

    /**
     * creates a workbook and a sheet Hvac
     * @param: sheet name
     */
    private fun createWorkbook(sheetName: String) {
        workbook = HSSFWorkbook()
        sheet = workbook.createSheet(sheetName)
    }

    /**
     * Creates header row and assign header values to each cell
     * @param: workbook instance
     */
    private fun pushHeaders(workbook: HSSFWorkbook) {
        val headerFont = workbook.createFont()
        headerFont.bold = true
        headerFont.color = IndexedColors.BLUE.getIndex()
        // create row for Headers
        val headerRow = sheet.createRow(0)
        for (col in headers.indices) {
            val cell = headerRow.createCell(col)
            cell.setCellValue(headers[col])
        }
    }

    /**
     * Creates values rows and assign values to each cell
     * @param: sheet of workbook instance
     */
    private fun pushValue(sheet: HSSFSheet) {
        var rowIdx = 1
        for (screenKey in screenInfoMap.keys) {
            val eventInfoMap = screenInfoMap[screenKey]?.eventInfoList
            val eventKeys = eventInfoMap?.keys!!
            for (eventKey in eventKeys) {
                val row = sheet.createRow(rowIdx++)
                row.createCell(0).setCellValue(AnalyticConstants.APP_HVAC)
                row.createCell(1).setCellValue("2")
                row.createCell(2).setCellValue(screenInfoMap[screenKey]?.name) // prepare a DTO
                row.createCell(3).setCellValue(screenInfoMap[screenKey]?.count.toString())
                row.createCell(4).setCellValue(eventInfoMap[eventKey]?.name)
                row.createCell(5).setCellValue(eventInfoMap[eventKey]?.count.toString())
            }
        }
    }

    /**
     * closed the workook once it is written to the excel sheet
     * @param: workbook instance
     * @param: Excel file name with extension
     */
    private fun closeWorkbook(workbook: HSSFWorkbook, workbookFile: String) {
        var fos: FileOutputStream? = null
        fos.use {
            try {
                val strPath = Environment.getExternalStorageDirectory().toString()
                fos = FileOutputStream(File(strPath, workbookFile))
                workbook.write(fos)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                fos?.close()
            }
        }

        workbook.close()
    }

}