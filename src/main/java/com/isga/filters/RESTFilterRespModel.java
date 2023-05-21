package com.isga.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.isga.model.ErrorAPIResponse;

public class RESTFilterRespModel {
	public static void generate(HttpServletResponse resp,int code,String message) {
		try {
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");
	        resp.setStatus(code);
	        out.print(new Gson().toJson(new ErrorAPIResponse(code,message)));
	        out.flush();   
	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}

}
