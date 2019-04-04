package org.esdee.purecloud.utilities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.esdee.purecloud.models.Ewt;
import org.esdee.purecloud.models.Queue;

public class ExcelFromLocalDB {
	
	
	public static void createEwtExcel(String[] dateRange) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
			
		List<Ewt> ewtList = new ArrayList<Ewt>();
				
		String from = dateRange[0];
		String to = dateRange[1];
		
		ResultSet rs = DBUtil.dbExecuteQuery("SELECT * from ewts WHERE callStartDateTime > '" + from + " 00:00:00' AND callStartDateTime < '" + to + " 00:00:00' ORDER BY interactionId");
		
		while(rs.next()) {
			Ewt ewt = new Ewt();
			ewt.setId(rs.getString("_id"));
			ewt.setCallStartDateTime(rs.getString("callStartDateTime"));
			ewt.setCallAni(rs.getString("calledAddressOriginal"));
			ewt.setCallAni(rs.getString("callAni"));
			ewt.setCalledAddressOriginal(rs.getString("calledAddressOriginal"));
			ewt.setInteractionId(rs.getString("interactionId"));
			ewt.setCallCurrentQueue(rs.getString("callCurrentQueue"));
			ewt.setRealWaitTime(rs.getString("realWaitTime"));
			ewt.setCallEstWaitTime(rs.getString("callEstWaitTime"));
			ewt.setApiEstWaitTimeInSeconds(rs.getString("apiEstWaitTimeInSeconds"));
			ewt.setCallPositionInQueue(rs.getString("callPositionInQueue"));
			ewt.setCallLanguage(rs.getString("callLanguage"));
			ewtList.add(ewt);
		}
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(from + "-" + to);
		sheet.setColumnWidth(0, 6000);
		sheet.setColumnWidth(1, 6000);
		sheet.setColumnWidth(2, 6000);
		sheet.setColumnWidth(3, 6000);
		sheet.setColumnWidth(4, 6000);
		sheet.setColumnWidth(5, 6000);
		sheet.setColumnWidth(6, 6000);
		sheet.setColumnWidth(7, 6000);
		sheet.setColumnWidth(8, 6000);
		sheet.setColumnWidth(9, 6000);
		sheet.setColumnWidth(10, 6000);
		String[][] ewtArray = new String[ewtList.size() + 1][11];
		ewtArray[0][0] = "ID";
		ewtArray[0][1] = "CALL START TIME";
		ewtArray[0][2] = "CALL FROM";
		ewtArray[0][3] = "CALL TO";
		ewtArray[0][4] = "INTERACTION ID";
		ewtArray[0][5] = "QUEUE";
		ewtArray[0][6] = "TIME WAITING";
		ewtArray[0][7] = "EST WAIT TIME";
		ewtArray[0][8] = "API EST WAIT TIME";
		ewtArray[0][9] = "POS. IN QUEUE";
		ewtArray[0][10] = "LANGUAGE";
		
		for(int i = 0; i < ewtList.size(); i++) {
			ewtArray[i+1][0] = ewtList.get(i).getId();
			ewtArray[i+1][1] = ewtList.get(i).getCallStartDateTime();
			ewtArray[i+1][2] = ewtList.get(i).getCallAni();
			ewtArray[i+1][3] = ewtList.get(i).getCalledAddressOriginal();
			ewtArray[i+1][4] = ewtList.get(i).getInteractionId();
			ewtArray[i+1][5] = ewtList.get(i).getCallCurrentQueue();
			ewtArray[i+1][6] = ewtList.get(i).getRealWaitTime();
			ewtArray[i+1][7] = ewtList.get(i).getCallEstWaitTime();
			ewtArray[i+1][8] = ewtList.get(i).getApiEstWaitTimeInSeconds();
			ewtArray[i+1][9] = ewtList.get(i).getCallPositionInQueue();
			ewtArray[i+1][10] = ewtList.get(i).getCallLanguage();
		}
		
		int rowN = 0;
		
		for(String[] s : ewtArray) {
			XSSFRow row = sheet.createRow(rowN++);
			int colN = 0;
			for (Object field : s) {
				XSSFCell cell = row.createCell(colN++);
				if (row.getRowNum() == 0) {
					if (colN == 3) {
						// TODO create header cell style
						// cell.setCellStyle(new CellStyles().csHeaderSubject(workbook));
					} else {
						// TODO create cell style
						// cell.setCellStyle(new CellStyles().csHeader(workbook));
					}
				}
				int c = cell.getRowIndex();
				Row rowC = sheet.getRow(c);
				rowC.setHeightInPoints(20);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}
		}
		try {
			String filename = "C:\\eclipse-out\\ewts-" + String.valueOf(Instant.now().getEpochSecond()) + ".xlsx";
			FileOutputStream fos = new FileOutputStream(filename);
			workbook.write(fos);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void createMetricsExcel(String[] dateRange) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		List<Queue> queueList = new ArrayList<Queue>();
		
		String from = dateRange[0];
		String to = dateRange[1];
		
		ResultSet rs = DBUtil.dbExecuteQuery("SELECT * from metrics WHERE timestamp > '" + from + " 00:00:00' AND timestamp < '" + to + " 00:00:00' ORDER BY timestamp");
		
		while(rs.next()) {
			Queue queue = new Queue();
			queue.setUuid(rs.getString("uuid"));
			queue.setDateTime(rs.getString("timestamp"));
			queue.setQueueId(rs.getString("queueId"));
			queue.setInteracting(rs.getInt("interacting"));
			queue.setIdle(rs.getInt("idle"));
			queue.setCommunicating(rs.getInt("communicating"));
			queue.setNotResponding(rs.getInt("notResponding"));
			queue.setOnQueue(rs.getInt("onQueue"));
			queue.setOffQueue(rs.getInt("offQueue"));
			queueList.add(queue);
		}
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(from + "-" + to);
		sheet.setColumnWidth(0, 6000);
		sheet.setColumnWidth(1, 6000);
		sheet.setColumnWidth(2, 6000);
		sheet.setColumnWidth(3, 6000);
		sheet.setColumnWidth(4, 6000);
		sheet.setColumnWidth(5, 6000);
		sheet.setColumnWidth(6, 6000);
		sheet.setColumnWidth(7, 6000);
		sheet.setColumnWidth(8, 6000);
		sheet.setColumnWidth(9, 6000);
		sheet.setColumnWidth(10, 6000);
		String[][] queueArray = new String[queueList.size() + 1][9];
		queueArray[0][0] = "UUID";
		queueArray[0][1] = "TIMESTAMP";
		queueArray[0][2] = "QUEUE ID";
		queueArray[0][3] = "INTERACTING";
		queueArray[0][4] = "COMMUNICATING";
		queueArray[0][5] = "IDLE";
		queueArray[0][6] = "NOT RESPONDING";
		queueArray[0][7] = "ON QUEUE";
		queueArray[0][8] = "OFF QUEUE";
				
		for(int i = 0; i < queueList.size(); i++) {
			queueArray[i+1][0] = queueList.get(i).getUuid();
			queueArray[i+1][1] = queueList.get(i).getDateTime();
			queueArray[i+1][2] = queueList.get(i).getQueueId();
			queueArray[i+1][3] = String.valueOf(queueList.get(i).getInteracting());
			queueArray[i+1][4] = String.valueOf(queueList.get(i).getCommunicating());
			queueArray[i+1][5] = String.valueOf(queueList.get(i).getIdle());
			queueArray[i+1][6] = String.valueOf(queueList.get(i).getNotResponding());
			queueArray[i+1][7] = String.valueOf(queueList.get(i).getOnQueue());
			queueArray[i+1][8] = String.valueOf(queueList.get(i).getOffQueue());
		}
		
		int rowN = 0;
		
		for(String[] s : queueArray) {
			XSSFRow row = sheet.createRow(rowN++);
			int colN = 0;
			for (Object field : s) {
				XSSFCell cell = row.createCell(colN++);
				if (row.getRowNum() == 0) {
					if (colN == 3) {
						// TODO create header cell style
						// cell.setCellStyle(new CellStyles().csHeaderSubject(workbook));
					} else {
						// TODO create cell style
						// cell.setCellStyle(new CellStyles().csHeader(workbook));
					}
				}
				int c = cell.getRowIndex();
				Row rowC = sheet.getRow(c);
				rowC.setHeightInPoints(20);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}
		}
		try {
			String filename = "C:\\eclipse-out\\metrics-" + String.valueOf(Instant.now().getEpochSecond()) + ".xlsx";
			FileOutputStream fos = new FileOutputStream(filename);
			workbook.write(fos);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**	public static void main(String[] args) {
			String dateRange[] = new String[2];
			dateRange[0] = "2019-03-25";
			dateRange[1] = "2019-03-26";
			try {
				createMetricsExcel(dateRange);
			} catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
		}
	*/
 }
