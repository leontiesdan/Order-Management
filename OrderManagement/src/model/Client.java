package model;

/**
 * @author Leonties Dan Alexandru
 * Class for the Client Object
 *
 */

public class Client {

	private int id;
	private String name;
	private String address;
	private String email;
	private int age;
	
	/**
	 * Simple Client constructor
	 */
	
	public Client()
	{
		
	}
	
	/**
	 * Client constructor with parameters
	 * @param id	Id of client
	 * @param name	Name of client
	 * @param address	Address of client
	 * @param email	Email of client
	 * @param age	Age of client
	 */
	public Client(int id, String name, String address, String email, int age)
	{
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.age = age;
	}
	
	/**
	 * Id setter
	 * @param id	Id of client
	 */
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	/**
	 * Name setter
	 * @param name	Name of client
	 */
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Address setter
	 * @param addr	Address of client
	 */
	
	public void setAddr(String addr)
	{
		this.address = addr;
	}
	
	/**
	 * Email setter
	 * @param email	Email of client
	 */
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	/**
	 * Age setter
	 * @param age	Age of client
	 */
	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	/**
	 * Id getter
	 * @return id of client
	 */
	
	public int getID()
	{
		return this.id;
	}
	
	/**
	 * Name getter
	 * @return	name of client
	 */
	
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Address getter
	 * @return	address of client
	 */
	
	public String getAddr()
	{
		return this.address;
	}
	
	/**
	 * Email getter
	 * @return	email of client
	 */
	
	public String getEmail()
	{
		return this.email;
	}
	
	/**
	 * Age getter
	 * @return	age of client
	 */
	
	public int getAge()
	{
		return this.age;
	}
	
}
