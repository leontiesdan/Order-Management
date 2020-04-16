package model;

/**
 * @author Leonties Dan Alexandru
 * Class for the Orders Object
 *
 */

public class Orders {

	private int id;
	private String orderer;
	private String item;
	private int nbItems;
	
	
	/**
	 * Simple constructor
	 */
	
	public Orders()
	{
		
	}
	
	/**
	 * Constructor with parameters
	 * @param id	Order id
	 * @param orderer	Name of orderer
	 * @param item	Name of item
	 * @param nbItems	Nb of items
	 */
	
	public Orders(int id, String orderer, String item, int nbItems)
	{
		this.id = id;
		this.orderer = orderer;
		this.item = item;
		this.nbItems = nbItems;
	}
	
	/**
	 * Id setter
	 * @param id	Id of Orderer
	 */
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	/**
	 * Name setter
	 * @param name Name of orderer
	 */
	
	public void setName(String name)
	{
		this.orderer = name;
	}
	
	/**
	 * Item setter
	 * @param item	Name of the ordered item
	 */
	
	public void setItem(String item)
	{
		this.item = item;
	}
	
	/**
	 * Nb of items setter
	 * @param nbItems	Number of ordered items
	 */
	
	public void setNbItems(int nbItems)
	{
		this.nbItems = nbItems;
	}
	
	/**
	 * Id getter
	 * @return	Id of orderer
	 */
	
	public int getId()
	{
		return this.id;
	}
	
	/**
	 * Name getter
	 * @return	Name of orderer
	 */
	
	public String getName()
	{
		return this.orderer;
	}
	
	/**
	 * Item getter
	 * @return	Name of item
	 */
	
	public String getItem()
	{
		return this.item;
	}
	
	/**
	 * Nb of items getter
	 * @return	Number of items
	 */
	
	public int getNbItems()
	{
		return this.nbItems;
	}
	
}
	
