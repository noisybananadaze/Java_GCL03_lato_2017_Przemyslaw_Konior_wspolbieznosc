package app;

public class Student
{
	private double mark;
	private String firstName;
	private String lastName;
	private int age;


	public Student() {}

	public Student(double mark, String firstName, String lastName, int age) {
	    this.mark = mark;
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.age = age;
    }
	
	public double getMark()
	{
		return this.mark;
	}
	
	public void setMark( double mark )
	{
		this.mark = mark;
	}
	
	public String getFirstName()
	{
		return this.firstName;
	}
	
	public void setFirstName( String firstName )
	{
		this.firstName = firstName;
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
	
	public void setLastName( String lastName )
	{
		this.lastName = lastName;
	}
	
	public int getAge()
	{
		return this.age;
	}
	
	public void setAge( int age )
	{
		this.age = age;
	}
}
