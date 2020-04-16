package start;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.text.DocumentException;

import businessLayer.Commands;
import dataAccessLayer.AccessDB;
import presentation.userInterf;

/**
 * @author Leonties Dan Alexandru
 * Class used to start the project
 *
 */

public class start {
	
	/**
	 * This is where the project starts
	 * @throws FileNotFoundException	In case the file is not found
	 * @throws IOException	In case the file cannot be accessed
	 * @throws DocumentException	In case the Document cannot be opened
	 * @throws SQLException	In case the SQL Query cannot be executed
	 */
	
	public static void main(String[] args) throws FileNotFoundException, IOException, DocumentException, SQLException
	{
		String file = "commands.txt";
		String[] cmds = new String[5];
		AccessDB acc = new AccessDB();
		Commands com = new Commands();
		userInterf interf = new userInterf(file);		
		interf.parser(acc, com, cmds);

	}

}
