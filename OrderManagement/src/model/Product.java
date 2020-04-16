package model;

/**
 * @author Leonties Dan Alexandru
 * Class for the Product Object
 *
 */


public class Product {
	
	private int id;
	private String name;
	private int quant;
	private double price;
	
	/**
	 * Simple construct
	 */
	
	public Product()
	{
		
	}
	
	/**
	 * Construct with parameters
	 * @param id	Id of product
	 * @param name	Name of product
	 * @param quant	Quantity of product
	 * @param price	Price of product
	 */
	
	public Product(int id, String name, int quant, double price)
	{
		this.id = id;
		this.name = name;
		this.quant = quant;
		this.price = price;
	}
	
	/**
	 * Id setter
	 * @param id	Id of product
	 */
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	/**
	 * Name setter
	 * @param name	Name of product
	 */
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Quantity setter
	 * @param q	Quantity of product
	 */
	
	public void setQ(int q)
	{
		this.quant = q;
	}
	
	/**
	 * Price setter
	 * @param price	Price of product
	 */
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	/**
	 * Id getter
	 * @return	Id of product
	 */
	
	public int getId()
	{
		return this.id;
	}
	
	/**
	 * Name getter
	 * @return	Name of product 
	 */
	
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Quantity getter
	 * @return	Quantity of product
	 */

	public int getQ()
	{
		return this.quant;
	}
	
	/**
	 * Price getter
	 * @return	Price of product
	 */
	
	public double getPrice()
	{
		return this.price;
	}
}

