package com.dms.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dms.beans.Smslogger;
import com.dms.dao.SmsDao;

public class SmsApi {

public static void sendSms(List<String> mobNumbers,String message,String msgType){
		
		try{
			
			message = URLEncoder.encode(message, "UTF-8");
			
			String mobs = "";
			for(String mb : mobNumbers){
				if(mobs.trim().length()>0)
					mobs += ",";
				mobs+=mb;
			}
			 
			String urlParameters = "User=ourdigitalsociety"
					+ "&passwd=123day789"
					+ "&mobilenumber="+mobs
					+ "&message="+message
					+ "&sid=DIGITL"
					+ "&mtype=N&DR=Y";
			
			String url = "http://api.smscountry.com/SMSCwebservice_bulk.aspx?"+urlParameters;
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			Smslogger smslogger = new Smslogger(msgType, message, Long.valueOf(String.valueOf(mobNumbers.size())) , mobs, new Date(),response.toString(),String.valueOf(responseCode));
			SmsDao sdao = new SmsDao();
			sdao.logSmsToDB(smslogger);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	public static void sendSms(String  mobs,String orgMsg,String msgType){
	
	try{
		
		String message = URLEncoder.encode(orgMsg, "UTF-8");
		 
		 
		String urlParameters = "User=ourdigitalsociety"
				+ "&passwd=123day789"
				+ "&mobilenumber="+mobs
				+ "&message="+message
				+ "&sid=DIGITL"
				+ "&mtype=N&DR=Y";
		
		String url = "http://api.smscountry.com/SMSCwebservice_bulk.aspx?"+urlParameters;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		Smslogger smslogger = new Smslogger(msgType, orgMsg, 1 , mobs, new Date(),response.toString(),String.valueOf(responseCode));
		SmsDao sdao = new SmsDao();
		sdao.logSmsToDB(smslogger);
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
}
	
	public static void main(String args[]){
		
		List<String> mobNos = new ArrayList<String>();
		mobNos.add("8976724255");
		SmsApi.sendSms(mobNos, "OTP = Test-msg", "OTP");
		
	}
}
