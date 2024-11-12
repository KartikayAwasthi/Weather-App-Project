package com.WeatherApp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.sql.*;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   	{
		String apiKey="fbb76d36f219c4e25d05b75430aeabec";
;
		String city=req.getParameter("city");
		String apiUrl="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+apiKey;
		
		try {
			URL url=new URL(apiUrl);
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			
			InputStream inputstream=connection.getInputStream();
			InputStreamReader reader=new InputStreamReader(inputstream);
			
			Scanner sc=new Scanner(reader);
			StringBuilder respContent=new StringBuilder();
			
			while(sc.hasNext())
			{
				respContent.append(sc.nextLine());
			}
			sc.close();
			
			Gson gson=new Gson();
			
			JsonObject jsonobject=gson.fromJson(respContent.toString(),JsonObject.class);
			
			
			 //Date & Time
            long dateTimestamp = jsonobject.get("dt").getAsLong() * 1000;
            String date = new Date(dateTimestamp).toString();
            
            //Temperature
            double temperatureKelvin = jsonobject.getAsJsonObject("main").get("temp").getAsDouble();
            int temperatureCelsius = (int) (temperatureKelvin - 273.15);
           
            //Humidity
            int humidity = jsonobject.getAsJsonObject("main").get("humidity").getAsInt();
            
            //Wind Speed
            double windSpeed = jsonobject.getAsJsonObject("wind").get("speed").getAsDouble();
            
            //Weather Condition
            String weatherCondition = jsonobject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
            
            // Set the data as request attributes (for sending to the jsp page)
            req.setAttribute("date", date);
            req.setAttribute("city", city);
            req.setAttribute("temperature", temperatureCelsius);
            req.setAttribute("weatherCondition", weatherCondition); 
            req.setAttribute("humidity", humidity);    
            req.setAttribute("windSpeed", windSpeed);
            req.setAttribute("weatherData", respContent.toString());
            
            connection.disconnect();
			
		}
		catch(IOException e){
			e.printStackTrace();
			
		}
		// Forward the request to the weather.jsp page for rendering
        req.getRequestDispatcher("index.jsp").forward(req, resp);
		
	}

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
	resp.sendRedirect("index.html");
	}

}
