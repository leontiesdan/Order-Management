package presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.itextpdf.text.DocumentException;

import businessLayer.Commands;
import dataAccessLayer.AccessDB;

/**
 * @author Leonties Dan Alexandru
 * Class used to implement the user input/output with a string for reading a line, and a Scanner for accessing the commands.txt file
 *
 */

public class userInterf {

	
	String command = new String();
	Scanner scanner;
	
	/**
	 *	Constructor used to create the Object used to access the file and iterate through each line  
	 * @param file The commands.txt file we use to get the instructions
	 * @throws IOException	In case the file can't be opened
	 * @throws FileNotFoundException	In case the file doesn't exist
	 */
	
	public userInterf(String file) throws IOException, FileNotFoundException
	{
		this.command = new String();	
		this.scanner = new Scanner(new File(file));
		
	}
	
	/**
	 *	Initializes the command string 
	 */
	
	public void initCommand()
	{
		this.command = new String();
	}
	
	/**
	 *	Method used to insert a client in the DB
	 * @param acc	The Object Connector created used to connect to the DB
	 * @param cmd	The Object Commands used to execute the needed command
	 * @param cmds	The Parameters needed to execute the command 
	 */
	
	public void insertClient(AccessDB acc, Commands cmd, String[] cmds)
	{
		cmd.insertClient(acc, cmds[3] + " " + cmds[4], cmds[6]);
	}
	
	/**
	 *	Method used to insert a product in the DB
	 * @param acc	The Object Connector created used to connect to the DB
	 * @param cmd	The Object Commands used to execute the needed command
	 * @param cmds	The Parameters needed to execute the command 
	 * @throws NumberFormatException In case we can't parse the value of the string, which should be an integer
	 * @throws SQLException	In case the SQL command cannot be executed
	 */
	
	public void insertProd(AccessDB acc, Commands cmd, String[] cmds) throws NumberFormatException, SQLException 
	{
		cmd.insertProd(acc, cmds[3], Integer.parseInt(cmds[5]), Double.parseDouble(cmds[7]));
	}
	
	/**
	 *	Method used to insert an order in the DB
	 * @param acc	The Object Connector created used to connect to the DB
	 * @param cmd	The Object Commands used to execute the needed command
	 * @param cmds	The Parameters needed to execute the command 
	 * @throws NumberFormatException In case we can't parse the value of the string, which should be an integer
	 * @throws FileNotFoundException In case the file cannot be accessed 
	 * @throws DocumentException	In case the Document cannot be created
	 */
	
	public void insertOrder(AccessDB acc, Commands cmd, String[] cmds) throws NumberFormatException, FileNotFoundException, DocumentException
	{
		cmd.order(acc, cmds[2] + " " + cmds[3], cmds[5], Integer.parseInt(cmds[7]));
	}
	
	/**
	 *	Method used to delete a client from the DB
	 * @param acc	The Object Connector created used to connect to the DB
	 * @param cmd	The Object Commands used to execute the needed command
	 * @param cmds	The Parameters needed to execute the command 
	 */
	
	public void deleteClient(AccessDB acc, Commands cmd, String[] cmds)
	{
		cmd.deleteClient(acc, cmds[3] + " " + cmds[4], cmds[6]);
	}
	
	/**
	 *	Method used to delete a product from the DB
	 * @param acc	The Object Connector created used to connect to the DB
	 * @param cmd	The Object Commands used to execute the needed command
	 * @param cmds	The Parameters needed to execute the command 
	 */
	
	public void deleteProd(AccessDB acc, Commands cmd,String[] cmds)
	{
		cmd.deleteProd(acc, cmds[3]);
	}
	
	/**
	 *	Method used to create a report, inside determining which type of report it should create : client, order or product
	 * @param acc	The Object Connector created used to connect to the DB
	 * @param cmd	The Object Commands used to execute the needed command
	 * @param cmds	The Parameters needed to execute the command 
	 * @throws	FileNotFoundException	In case the file cannot be accessed	
	 * @throws	DocumentException	In case the document cannot be created
	 * @throws	SQLException	In case the SQL Query cannot be executed
	 */
	
	public void report(AccessDB acc, Commands cmd,String[] cmds) throws FileNotFoundException, DocumentException, SQLException
	{
		if(cmds[1].equals("client"))	cmd.reportClient(acc);
		if(cmds[1].equals("order"))		cmd.reportOrder(acc);
		if(cmds[1].equals("product"))	cmd.reportProduct(acc);
	}
	
	/**
	 *	Method used to parse through the commands.txt file, and separate the needed parts of the command line to execute it 
	 * @param acc	The Object Connector created used to connect to the DB
	 * @param cmd	The Object Commands used to execute the needed command
	 * @param cmdParts	The String used for extracting the needed parameters for the commands 
	 * @throws	FileNotFoundException	In case the file cannot be accessed	
	 * @throws	DocumentException	In case the document cannot be created
	 * @throws	SQLException	In case the SQL Query cannot be executed
	 */
	
	public void parser(AccessDB acc, Commands cmd,String[] cmdParts) throws FileNotFoundException, DocumentException, SQLException
	{
		while(scanner.hasNextLine())
		{
			initCommand();
			this.command += scanner.nextLine();
			System.out.println(this.command);
			String[] cmds = this.command.split("\\s|\\,|\\:");
			cmdParts = cmds;
			System.out.println(cmdParts.length);
			for(int x = 0; x < cmdParts.length; x++) 
			{				
				cmdParts[x].trim();	
			}
			cmdInterp(acc, cmd, cmdParts);
			cmdParts = new String[5];
		}
	}
	
	/**
	 *	Method used to Interpret the commands and execute them 
	 * @param acc	The Object Connector created used to connect to the DB
	 * @param cmd	The Object Commands used to execute the needed command
	 * @param cmds	The parameters needed to execute the command 
	 * @throws	FileNotFoundException	In case the file cannot be accessed	
	 * @throws	DocumentException	In case the document cannot be created
	 * @throws	SQLException	In case the SQL Query cannot be executed
	 */
	
	public void cmdInterp(AccessDB acc, Commands cmd,String[] cmds) throws FileNotFoundException, DocumentException, SQLException
	{
		if(cmds[0].equals("Insert"))	
		{
			if(cmds[1].equals("client"))	{this.insertClient(acc, cmd, cmds); }
			if(cmds[1].equals("product")) {this.insertProd(acc, cmd, cmds);}
		}
		if(cmds[0].equals("Order"))	{this.insertOrder(acc, cmd,cmds);}
		if(cmds[0].equals("Report")) {this.report(acc, cmd,cmds);}
		if(cmds[0].equals("Delete")) 
		{
			if(cmds[1].equals("Client") || cmds[1].equals("client")) {this.deleteClient(acc, cmd,cmds);}
			if(cmds[1].equals("Product") || cmds[1].equals("product")) {this.deleteProd(acc, cmd,cmds);}
		}
	}
}
