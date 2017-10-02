package com.dms.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class TestQueryBuilder {
	
	public static void main(String args[]){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://166.62.90.161:3306/dms","odsdbuser","12345");  
			
			
			String tablename = "IPR_ExpenseMaster";
			 String QueryType = "insert";
			//String QueryType = "update";
			
			TestQueryBuilder t1 = new TestQueryBuilder();
			System.out.println(t1.QueryBuiler(tablename.toLowerCase(), QueryType, con));
			
			con.close(); 
			
		}catch (Exception e) {
				e.printStackTrace();
			} 
		}
	
		public String QueryBuiler(String tablename,String QueryType,Connection con){
			try{

				
				String SQL = "select * from "+tablename;
				PreparedStatement pst = con.prepareStatement(SQL);
				ResultSet rs = pst.executeQuery();
				String QM=" (";
				ResultSetMetaData rsmd = rs.getMetaData();
				String query="";
				if(QueryType.equals("update"))
					query+= "Update "+tablename+" set ";
				else if(QueryType.equals("insert"))
					query+= "insert into "+tablename+"(";
				
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					if(QueryType.equals("update")){	
						query += rsmd.getColumnName(i)+"=?";
						if(i!=rsmd.getColumnCount())
							query +=", ";
					}
					else if(QueryType.equals("insert")){
						query += rsmd.getColumnName(i);
						if(i!=rsmd.getColumnCount())
							query +=", ";
						else
							query +=") values";
					}
					
					QM += "?,";
				}
				if(QueryType.equals("insert")){
					query = query+QM.substring(0,QM.length()-1)+")";
				}
				
				return "["+rsmd.getColumnCount()+"] "+query;
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
			
		}
	}
 
