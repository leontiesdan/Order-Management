package businessLayer;

import dataAccessLayer.AccessDB;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
  
/**
 * 
 * @author Leonties Dan Alexandru
 *	Class used to create the commands for updating or modifying the DB 
 */

public class Commands {
	
	/**
	 * 	 Simple constructor
	 */
	
	public Commands()
	{
		
	}
	
	/**
	 *	Method used for inserting a new client in the DB
	 * 	@param	acc	The Object Connector created used to connect to the DB
	 *	@param	name	Name of the client
	 *	@param	location	Address/Location of the client
	 */
	
	public void insertClient(AccessDB acc, String name, String location)
	{
		String sql = "insert into client " + " (name, address)" + " values ('" + name + "', '" + location + "')";
		String sel = "select * from client";
		acc.setSel(sel);
		acc.updateConn();
		acc.updateStmt();
		acc.execUpd(sql);
		acc.closeStmt(acc.getStmt());
		acc.closeConnection(acc.getConn());
	}
	
	/**
	 *	Method used for deleting a client from the DB
	 * 	@param	acc	The Object Connector created used to connect to the DB
	 *	@param	name	Name of the client
	 *	@param	city	Address/Location of the client
	 */
	
	public void deleteClient(AccessDB acc, String name, String city)
	{
		String sql = "delete from client where name = '" + name +"'" + "AND address = '" + city + "'";
		String sel = "select * from client";
		acc.setSel(sel);
		acc.updateConn();
		acc.updateStmt();
		acc.execUpd(sql);
		acc.closeStmt(acc.getStmt());
		acc.closeConnection(acc.getConn());
	}
	
	/**
	 *	Method used to edit a client's information from the DB
	 * 	@param	acc	The Object Connector created used to connect to the DB
	 * 	@param	col	The Column which needs to be modified
	 *	@param	info	The new information which needs to be updated
	 *	@param	name	Name of the client
	 */
	
	public void editClientInfo(AccessDB acc, String col, String info, String name)
	{
		String sql = "update client " + " set " + col + "=" + "'" + info + "'" + " where name = " + "'" + name + "'";
		String sel = "select * from client";
		acc.setSel(sel);
		acc.updateConn();
		acc.updateStmt();
		acc.execUpd(sql);
		acc.closeStmt(acc.getStmt());
		acc.closeConnection(acc.getConn());
	}
	
	/**
	 *	Method used for inserting a new product in the DB, or if the product already exists the method for modifying an existing product is called
	 * 	@param	acc	The Object Connector created used to connect to the DB
	 *	@param	prod	Name of the product
	 *	@param	quantity	Quantity of the product
	 *	@param	price	Price of product
	 *	@throws SQLException In case the product that exists doesn't have a quantity value
	 */
	public void insertProd(AccessDB acc, String prod, int quantity, double price) throws SQLException
	{
		String sql = "insert into Product  " + " (productName, quantity, price)" + " values ('" + prod + "', '" + quantity + "', '" + price + "')";
		String sel = "select * from product";
		String sel1 = "select * from product where productName = '" + prod + "'";
		acc.setSel(sel1);
		acc.updateConn();
		acc.updateStmt();
		acc.updateResSel(sel1);
		if(acc.getRes().next()){
			int q = acc.getRes().getInt("quantity");
			q += quantity;
			editProd(acc, prod, q, price);
		}
		else{
		  acc.closeRes(acc.getRes());
		  acc.closeStmt(acc.getStmt());
		  acc.closeConnection(acc.getConn());
		  acc.setSel(sel);
		  acc.updateConn();
		  acc.updateStmt();
		  acc.updateResSel(sel);
		  acc.execUpd(sql); 
		  acc.closeRes(acc.getRes());
		  acc.closeStmt(acc.getStmt());
		  acc.closeConnection(acc.getConn());
		}
	}
	
	/**
	 *	Method used for modifying a product's quantity and price in the DB
	 * 	@param	acc	The Object Connector created used to connect to the DB
	 *	@param	prodName	Name of the product
	 *	@param	quantity	Quantity of the product
	 *	@param	price	Price of product
	 */
	
	public void editProd(AccessDB acc, String prodName, int quantity, double price)
	{
		String sql = "update Product " + " set quantity = '" + quantity + "', price = '" + price + "' " + " where productName = " + "'" + prodName + "'";
		String sel = "select * from product";
		acc.setSel(sel);
		acc.updateConn();
		acc.updateStmt();
		acc.execUpd(sql);
		acc.closeStmt(acc.getStmt());
		acc.closeConnection(acc.getConn());
	}
	
	/**
	 *	Method used for deleting a product from the DB
	 * 	@param	acc	The Object Connector created used to connect to the DB
	 *	@param	prodName	Name of the product
	 *	
	 */
	
	public void deleteProd(AccessDB acc, String prodName)
	{
		String sql = "delete from Product where productName = '" + prodName + "'";
		String sel = "select * from product";
		acc.setSel(sel);
		acc.updateConn();
		acc.updateStmt();
		acc.execUpd(sql);
		acc.closeStmt(acc.getStmt());
		acc.closeConnection(acc.getConn());
	}
	
	/**
	 *	Method used for inserting an Order in the DB (This method will be used in another method for inserting in the DB, this is just a simple insert)
	 * 	@param	acc	The Object Connector created used to connect to the DB
	 *	@param	client	Name of the client
	 *	@param	prod	Name of the product
	 *	@param	quantity	Quantity which will be ordered
	 */
	
	public void insertOrder(AccessDB acc, String client, String prod, int quantity)
	{
		String sel = "select * from orders ";
		String sql = "insert into orders " + " (orderer, item, nbItems) " + " values ('" + client + "', '" + prod + "', '" + quantity + "') ";
		acc.setSel(sel);
		acc.updateConn();
		acc.updateStmt();
		acc.updateResSel(sel);
		acc.execUpd(sql);
		acc.closeRes(acc.getRes());
		acc.closeStmt(acc.getStmt());
		acc.closeConnection(acc.getConn());
	}
	
	/**
	 *	Method used for inserting an Order in the DB, at the same time creating either a Bill in a successful order, or an Under-Stock PDF printing the corresponding message
	 * 	@param	acc	The Object Connector created used to connect to the DB
	 *	@param	client	Name of the client
	 *	@param	prod	Name of the product
	 *	@param	quantity	Quantity which will be ordered
	 *	@throws SQLException	In case the Quantity and Price don't exist
	 *	@throws FileNotFoundException	In case the File cannot be created, or the File is already open
	 *	@throws DocumentException	In case the Document cannot be created, or the Document is already open
	 *	 
	 */
	
	public void order(AccessDB acc, String client, String prod, int quantity) throws FileNotFoundException, DocumentException
	{
		String sel = "select * from product where productName = '" + prod + "'";	acc.setSel(sel); acc.updateConn(); acc.updateStmt(); acc.updateResSel(sel);
		try {
				if(acc.getRes().next())
				{
					int q = acc.getRes().getInt("quantity");
					double price = acc.getRes().getDouble("price");
					if(quantity > q || q == 0){ generatePDFError(acc, client, prod); acc.closeRes(acc.getRes()); acc.closeStmt(acc.getStmt()); acc.closeConnection(acc.getConn());}
					else{	
						generatePDFBill(acc, client, prod, quantity, price);
						System.out.println("THIS IS Q BEFORE : " + q); q -= quantity; System.out.println("THIS IS Q : " + q);
						String updSql = "update product " + " set quantity = " + q + " where productName = ' " + prod + "'";
						acc.execUpd(updSql);
						acc.closeRes(acc.getRes());
						acc.closeStmt(acc.getStmt());
						acc.closeConnection(acc.getConn());
						insertOrder(acc, client, prod, quantity);}
				}
			} 
			catch (SQLException e) 	{e.printStackTrace();}	 
	}
	
	/**
	 *	Method used for generating a Bill in a PDF format for a successful Order 
	 * 	@param	acc	The Object Connector created used to connect to the DB
	 *	@param	client	Name of the client
	 *	@param	prod	Name of the product
	 *	@param	quantity	Quantity which will be ordered
	 *	@param	price	Price of the Order
	 *	@throws FileNotFoundException	In case the File cannot be created, or the File is already open
	 *	@throws DocumentException	In case the Document cannot be created, or the Document is already open
	 *	 
	 */
	
	public void generatePDFBill(AccessDB acc, String Client, String prod, int quantity, double price)
	{
		try {
			String fileName = "BillFor" + Client.trim() + "Of" + prod + quantity + ".pdf";
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(fileName));
			doc.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk chunk = new Chunk(Client + "   " + prod + "   " + quantity + "   " + price, font);
			doc.add(chunk);
			doc.close();
			}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 *	Method used for creating the Under-Stock PDF with the error message that the client is asking for a quantity greater than the remaining stock 
	 * 	@param	acc	The Object Connector created used to connect to the DB
	 *	@param	client	Name of the client
	 *	@param	prod	Name of the product
	 *	@throws FileNotFoundException	In case the File cannot be created, or the File is already open
	 *	@throws DocumentException	In case the Document cannot be created, or the Document is already open
	 *	 
	 */
	
	public void generatePDFError(AccessDB acc, String Client, String prod) throws FileNotFoundException, DocumentException
	{
		String fileName = "Under_Stock_For_" + Client.trim() + "_Of_" + prod  + ".pdf";
		Document doc = new Document();
		PdfWriter.getInstance(doc, new FileOutputStream(fileName));
		doc.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Paragraph para = new Paragraph("Under-stock for Client :  " + Client + " on order of " + prod, font);
		doc.add(para);
		doc.close();
	}
	
	/**
	 *	Method used for creating the Report for all clients, with 5 columns : Id, Name, Address, Email and Age
	 * 	@param	acc	The Object Connector created used to connect to the DB
	 *	@throws FileNotFoundException	In case the File cannot be created, or the File is already open
	 *	@throws DocumentException	In case the Document cannot be created, or the Document is already open
	 *	@throws SQLException	If case the table can't be accessed 
	 *	 
	 */
	
	public void reportClient(AccessDB acc) throws FileNotFoundException, DocumentException, SQLException
	{
		String sql = "Select * from client";
		acc.updateConn();
		acc.updateStmt();
		acc.updateResSel(sql);
		Document doc = new Document();
		PdfWriter.getInstance(doc, new FileOutputStream("ClientReport.pdf"));
		doc.open();
		PdfPTable table = new PdfPTable(5);
		Stream.of("ID", "Name", "Address", "Email", "Age").forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
		while(acc.getRes().next())
		{
			table.addCell(acc.getRes().getString("id"));				
			table.addCell(acc.getRes().getString("name"));
			table.addCell(acc.getRes().getString("address"));
			table.addCell(acc.getRes().getString("email"));
			table.addCell(acc.getRes().getString("age"));
		}
		doc.add(table);
		doc.close();
	}
	
	/**
	 *	Method used for creating the Report for all orders, with 4 columns : Id, Orderer, Item and NbOfItems
	 * 	@param	acc	The Object Connector created used to connect to the DB
	 *	@throws FileNotFoundException	In case the File cannot be created, or the File is already open
	 *	@throws DocumentException	In case the Document cannot be created, or the Document is already open
	 *	@throws SQLException	If case the table can't be accessed 
	 *	 
	 */
	public void reportOrder(AccessDB acc) throws FileNotFoundException, DocumentException, SQLException
	{
		String sql = "Select * from orders";
		acc.updateConn();
		acc.updateStmt();
		acc.updateResSel(sql);
		Document doc = new Document();
		PdfWriter.getInstance(doc, new FileOutputStream("OrderReport.pdf"));
		doc.open();
		PdfPTable table = new PdfPTable(4);
		Stream.of("ID", "Orderer", "Item", "NbOfItems").forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
		while(acc.getRes().next())
		{
			table.addCell(acc.getRes().getString("idOrder"));				
			table.addCell(acc.getRes().getString("orderer"));
			table.addCell(acc.getRes().getString("item"));
			table.addCell(acc.getRes().getString("nbItems"));
		}
		doc.add(table);
		doc.close();
	}
	
	/**
	 *	Method used for creating the Report for all products, with 4 columns : Id, Product, Quantity and Price
	 * 	@param	acc	The Object Connector created used to connect to the DB
	 *	@throws FileNotFoundException	In case the File cannot be created, or the File is already open
	 *	@throws DocumentException	In case the Document cannot be created, or the Document is already open
	 *	@throws SQLException	If case the table can't be accessed 
	 *	 
	 */
	public void reportProduct(AccessDB acc) throws FileNotFoundException, DocumentException, SQLException
	{
		String sql = "Select * from product";
		acc.updateConn();
		acc.updateStmt();
		acc.updateResSel(sql);
		Document doc = new Document();
		PdfWriter.getInstance(doc, new FileOutputStream("ProductReport.pdf"));
		doc.open();
		PdfPTable table = new PdfPTable(4);
		Stream.of("ID", "Product", "Quantity", "Price").forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
		while(acc.getRes().next())
		{
			table.addCell(acc.getRes().getString("id"));				
			table.addCell(acc.getRes().getString("productName"));
			table.addCell(acc.getRes().getString("quantity"));
			table.addCell(acc.getRes().getString("price"));
		}
		doc.add(table);
		doc.close();
	}
	}
