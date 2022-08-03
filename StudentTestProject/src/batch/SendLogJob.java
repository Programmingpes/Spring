package batch;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SendLogJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		File file = new File("error.txt");
		FileReader fr = null;
		BufferedReader br = null;
		String result = "";
		JSONObject obj = new JSONObject();
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			while(true) {
				String str = br.readLine();
				if(str == null) break;
				result += str;
			}
//			System.out.println(result);
			String[] arr = result.split("\t");
			
			
			
			for (int i=0; i<arr.length;i=i+3) {
				for(int j=i; j<i+3; j++) {
					if(j == i) 
						obj.put("log_date", arr[j]);
					if(j == i+1)
						obj.put("error_code", arr[j]);
					if(j == i+2)
						obj.put("content", arr[j]);
				}
				System.out.println(obj.toString());
				
				String content = URLEncoder.encode(obj.getString("content"),"utf-8");
				
				String apiUrl = "http://localhost:9999/error.do?date="
						+ obj.getString("log_date")+"&code=" + obj.getString("error_code") + "&content="
						+ content;
				URL url = new URL(apiUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				BufferedReader br1 = 	
						new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				System.out.println(br1.readLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null)br.close();
				if(fr != null)fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		File file1 = new File("error.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			} catch (IOException e) {
					e.printStackTrace();
			} finally {
			try {
				if(fw != null)fw.close();
			} catch (IOException e) {
				e.printStackTrace();
				}
			}
	}

}
