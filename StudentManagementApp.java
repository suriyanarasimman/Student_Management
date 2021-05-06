import java.util.Scanner;
import java.sql.*;

class Student{
	int id;
	String name;
	float marks_1, marks_2;
	String status;
	int getId() {
		return id;
	}
	void setId(int id) {
		this.id = id;
	}
	String getName() {
		return name;
	}
	void setName(String name) {
		this.name = name;
	}
	
	float getMarks_1() {
		return marks_1;
	}
	void setMarks_1(float marks_1) {
		this.marks_1 = marks_1;
	}
	float getMarks2() {
		return marks_2;
	}
	void setMarks_2(float marks_2) {
		this.marks_2 = marks_2;
	}
	String getStatus() {
		return status;
	}
	void setStatus(String status) {
		this.status = status;
	}
}
class Admin{
	static void add(Student student) {
		try {
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","Speed@123");
		Statement query=con.createStatement();
		System.out.println("Connected!");
		ResultSet rs1=query.executeQuery("select * from student");
		int match = 0;
		while(rs1.next()) {
			  if(rs1.getInt(1) == student.getId()) {
				  match = 1;break;
			  }
		}
		if(match == 0) {
			query.executeUpdate("INSERT INTO student VALUES ('"+student.getId()+"','"+student.getName()+"','"+student.getMarks_1()+"',"+student.getMarks2()+",'"+student.getStatus()+"')");
			System.out.println("Inserted!");
		}
		else
			System.out.println("This Student ID already exist");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	static void edit(int editId) {
		try {
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","Speed@123");
			Statement query=con.createStatement();
			ResultSet rs1=query.executeQuery("select * from student");
			int match = 0;
			while(rs1.next()) {
				  if(rs1.getInt(1) == editId) {
					  match = 1;break;
				  }
			}
			if(match == 1) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter Marks of Subject 1");
				float updateMarks_1 = sc.nextFloat();
				System.out.println("Enter Marks of Subject 2");
				float updateMarks_2 = sc.nextFloat();
				query.executeUpdate("Update student Set marks_1='"+updateMarks_1+"', marks_2='"+updateMarks_2+"' where id = '"+editId+"'");
				//query.executeUpdate("INSERT INTO student VALUES ('"+student.getId()+"','"+student.getName()+"','"+student.getMarks_1()+"',"+student.getMarks2()+",'"+student.getStatus()+"')");
				System.out.println("Updated!");
			}
			else
				System.out.println("Student ID doen't exist");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		
	}
	static void delete(int deleteId) {
		try {
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","Speed@123");
			Statement query=con.createStatement();
			ResultSet rs1=query.executeQuery("select * from student");
			int match = 0;
			while(rs1.next()) {
				  if(rs1.getInt(1) == deleteId) {
					  match = 1;break;
				  }
			}
			if(match == 1) {
				query.executeQuery("delete from student where id = '"+deleteId+"'");
				System.out.println("Deleted!");
			}
			else
				System.out.println("This Student ID doesn't exist ");
			
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	static void view() {
		try {
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","Your_Password");
			Statement query=con.createStatement();
			ResultSet rs=query.executeQuery("select * from student");
			System.out.println("ID         Name        Sub_1     Sub_2      Status");
			while(rs.next())  
			System.out.println(rs.getInt(1)+"        "+rs.getString(2)+"        "+rs.getString(3)+"        "+rs.getString(4)+"         "+rs.getString(5));  
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}
	
}
class StudentManagementApp{
	public static void main(String []args) {
		
		String arg[] = {""};
		Scanner sc = new Scanner(System.in);
		System.out.println("1. Add");
		System.out.println("2. Edit");
		System.out.println("3. Delete");
		System.out.println("4. View");
		System.out.println("5. Exit");
		int input = sc.nextInt();
		if(input == 1) {
			System.out.println("Enter Id : ");
			int addId = sc.nextInt();
			System.out.println("Enter Name : ");
			String addName = sc.next();
			System.out.println("Enter Marks in Subject_1 : ");
			float addMarks1 = sc.nextFloat();
			System.out.println("Enter Marks in subject_2 : ");
			float addMarks2 = sc.nextFloat();
			float totalMarks = addMarks1 + addMarks2;
			String addStatus = "";
			if(totalMarks > 50.0) 
				addStatus = "Pass";
			else
				addStatus = "Fail";
			Student student = new Student();
			student.setId(addId);
			student.setName(addName);
			student.setMarks_1(addMarks1);
			student.setMarks_2(addMarks2);
			student.setStatus(addStatus);	
			Admin.add(student);
			StudentManagementApp.main(arg);
		}
		else if(input == 2) {
			System.out.println("Enter Id : ");
			int editId = sc.nextInt();
			Admin.edit(editId);
			StudentManagementApp.main(arg);
		}
		else if(input == 3) {
			System.out.println("Enter Id : ");
			int deleteId = sc.nextInt();
			Admin.delete(deleteId);
			StudentManagementApp.main(arg);
		}
		else if(input == 4) {
			Admin.view();
			StudentManagementApp.main(arg);
		}
		else if(input == 5) {
			System.out.println("Thanks to use Student Management App :)");
			System.exit(0);
		}
	}
}
