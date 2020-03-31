/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package myapp;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
import java.io.FileReader;
import java.util.Scanner;
import java.net.URL;
import org.apache.commons.io.FileUtils;

public class DemoServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    resp.setContentType("text/plain");
    resp.getWriter().println("{ \"name\": \"World\" }");
    run(); 
  }

  public void MakeGraphFile(String filename) throws IOException  {

	/**
csvfile[i][0]	date
csvfile[i][1]	HCL
csvfile[i][2]	HCM
csvfile[i][3]	IAS
csvfile[i][4]	ICLS
csvfile[i][5]	LSS
csvfile[i][6]	FHCS
csvfile[i][7]	ISS
csvfile[i][8]	ICC
csvfile[i][9]	MobilityI
csvfile[i][10]	CACM
csvfile[i][11]	RBE
csvfile[i][12]	SACHtml
csvfile[i][13]	Title
csvfile[i][14]	SeriesName
csvfile[i][15]	dateUTC 	 **/

	String datediv = null;
	String datediv2 = null;
	String html = null;
	String title = null;
	String state = null;
	String form = null;
	String sac = null;
	String htmltemp = null;
	String titletemp = null;
	String statetemp = null;
	String formtemp = null;
	String sactemp = null;

	String[] dateArray;
	dateArray= new String[174];

	int[][] dataArray;
	dataArray = new int[174][11];

	String year = null;
	String month = null;
	//String filequote = null;

	String fileshort = null;
	String filehtml = null;

	String Datedata = null;
	String HCLdata = null;
	String HCMdata = null;
	String IASdata = null;
	String ICLSdata = null;
	String LSSdata = null;
	String FHCSdata = null;
	String ISSdata = null;
	String ICCdata = null;
	String MobilityIdata = null;
	String CACMdata = null;
	String RBEdata = null;

	String ch1 = null;

	try {
		//filequote = "\"" + filename + "\"";
		URL link = new URL(filename);
		//URL link = new URL("https://storage.googleapis.com/highcostbq/100003.csv");
		Scanner detailsIn =new Scanner(link.openStream());
		//Scanner detailsIn =new Scanner(new FileReader(System.getProperty("user.dir") + "/359060c.csv"));
    	detailsIn.useDelimiter("\\s*,\\s*");


        String[] numbersStrings = detailsIn.nextLine().split(",");
        //f0_	HCL	HCM	IAS	ICLS	LSS	FHCS	ISS	ICC	MobilityI	CACM	RBE	SACHtml	Title	SeriesName	date

        String[] inputArr;
        inputArr = new String[35];  //should be 35

        int i = 0;
        int c = 0;
        while (detailsIn.hasNextLine()){
        	inputArr = init1dStringArray(inputArr);
        	inputArr = detailsIn.nextLine().split(",");

    		if (i != 0) { //not header row
    			//if (inputArr != null) {
    				c++;  //counter of dates up to 174
    			//}
    		}
    		if (i == 0) {
    			htmltemp = inputArr[12];
    			//System.out.println(htmltemp.replace("&quote;", ""));
    			html = htmltemp.replace("&quote;", "");
    			titletemp = inputArr[13];
    			//System.out.println(titletemp.replace("&quote;", ""));
    			title = titletemp.replace("&quote;", "");
    			statetemp = inputArr[15];
    			//System.out.println(statetemp.replace("&quote;", ""));
    			state = statetemp.replace("&quote;", "");
    			//System.out.println(state.replace(" State", "State"));
    			statetemp = state.replace(" State", "State");
    			state = statetemp;
    			formtemp = inputArr[16];
    			//System.out.println(formtemp.replace("&quote;", ""));
    			form = formtemp.replace("&quote;", "");
    			//System.out.println(form.replace(" Form", "Form"));
    			formtemp = form.replace(" Form", "Form");
    			form = formtemp;
    			sactemp = inputArr[17];
    			//System.out.println(sactemp.replace("&quote;", ""));
    			sac = sactemp.replace("&quote;", "");
    			//System.out.println(sac.replace(" SAC :", "SAC:"));
    			sactemp = sac.replace(" SAC :", "SAC:");
    			sac = sactemp;

    			ch1 = "State:";

    			if (!(state.contains(ch1))){
    				for (int j = 17; j < 20; j++) {
    					if (inputArr[j].contains(ch1)) {
    						statetemp = inputArr[j];
    		    			//System.out.println(statetemp.replace("&quote;", ""));
    		    			state = statetemp.replace("&quote;", "");
    		    			//System.out.println(state.replace(" State", "State"));
    		    			statetemp = state.replace(" State", "State");
    		    			state = statetemp;
    		    			formtemp = inputArr[j+1];
    		    			//System.out.println(formtemp.replace("&quote;", ""));
    		    			form = formtemp.replace("&quote;", "");
    		    			//System.out.println(form.replace(" Form", "Form"));
    		    			formtemp = form.replace(" Form", "Form");
    		    			form = formtemp;
    		    			sactemp = inputArr[j+2];
    		    			//System.out.println(sactemp.replace("&quote;", ""));
    		    			sac = sactemp.replace("&quote;", "");
    		    			//System.out.println(sac.replace(" SAC :", "SAC:"));
    		    			sactemp = sac.replace(" SAC :", "SAC:");
    		    			sac = sactemp;

    		    			// fix 13, 14
    		    			titletemp = inputArr[13].replace("\"", "");
    		    			sactemp = inputArr[14].replace("\"", "");
    		    			title = titletemp + sactemp;
    		    			System.out.println(title);
    					}



    				}
    			}
    		}

        	for(int j = 0; j < 12; j++){
        		if ((inputArr[0] != null) || (inputArr[0] != "0")) { //no more dates

            			if (j == 0) {	//date column
        					datediv = inputArr[0];
        					//System.out.println(datediv.replace("/1", ""));
        					//datediv2 = datediv.replace("/1", "");
        					//datediv = datediv2.replace("-", "/");
        					//datediv2 = datediv.replace("/01", "");
        					datediv2 = datediv.substring(0, datediv.length() - 3);
        					//System.out.println(datediv2);
        					year = datediv2.substring(0,4);
        					//System.out.println(year);
        					month = datediv2.substring(5,7);
        					//System.out.println(month);
        					datediv2 = year.replace("20", "");
        					datediv = month.replace("0", "");
        					year = "'" + datediv + "/" + datediv2 + "'";
        					//System.out.println(year);
            				dateArray[c] = year;
        				} else {
        					//System.out.println(inputArr[j]);
        					//System.out.println(Integer.parseInt(inputArr[j]));
        					dataArray[c][j-1] = Integer.parseInt(inputArr[j]);
        				}

        		}
            }
        	i++;
        }
        	//System.out.println(detailsIn.nextLine());

        detailsIn.close();
        //this.print1dStringArray(dateArray);
        //this.print2dIntArray(dataArray);

    	//c is the number of data points
    	Datedata = getDatestring(dateArray);
    	HCLdata = getColumnstring(0, dataArray, c);
    	HCMdata = getColumnstring(1, dataArray, c);
    	IASdata = getColumnstring(2, dataArray, c);
    	ICLSdata = getColumnstring(3, dataArray, c);
    	LSSdata = getColumnstring(4, dataArray, c);
    	FHCSdata = getColumnstring(5, dataArray, c);
    	ISSdata = getColumnstring(6, dataArray, c);
    	ICCdata = getColumnstring(7, dataArray, c);
    	MobilityIdata = getColumnstring(8, dataArray, c);
    	CACMdata = getColumnstring(9, dataArray, c);
    	RBEdata = getColumnstring(10, dataArray, c);

    	fileshort = filename.substring(42,48);
    	System.out.println(fileshort);
    	filehtml = fileshort + ".html";
    	System.out.println(filehtml);

    	//http://stackoverflow.com/questions/5936003/write-html-file-using-java

    	File htmlTemplateFile = new File("template.html");
    	String htmlString = FileUtils.readFileToString(htmlTemplateFile, "UTF-8");

    	htmlString = htmlString.replace("$title", title);
    	htmlString = htmlString.replace("$state", state);
    	htmlString = htmlString.replace("$form", form);
    	htmlString = htmlString.replace("$sac", sac);
    	htmlString = htmlString.replace("$Datedata", Datedata);
    	htmlString = htmlString.replace("$HCLdata", HCLdata);
    	htmlString = htmlString.replace("$HCMdata", HCMdata);
    	htmlString = htmlString.replace("$IASdata", IASdata);
    	htmlString = htmlString.replace("$ICLSdata", ICLSdata);
    	htmlString = htmlString.replace("$LSSdata", LSSdata);
    	htmlString = htmlString.replace("$FHCSdata", FHCSdata);
    	htmlString = htmlString.replace("$ISSdata", ISSdata);
    	htmlString = htmlString.replace("$ICCdata", ICCdata);
    	htmlString = htmlString.replace("$MobilityIdata", MobilityIdata);
    	htmlString = htmlString.replace("$CACMdata", CACMdata);
    	htmlString = htmlString.replace("$RBEdata", RBEdata);
    	File newHtmlFile = new File(filehtml);
    	FileUtils.writeStringToFile(newHtmlFile, htmlString, "UTF-8");



	} catch (FileNotFoundException e) {
        System.out.println("file not found: s.txt");
    }

}

public String getDatestring(String[] dateArray){

	String dates = null;
	String datebase = null;

	for(int i = 0; i < 174; i++){
		if (dateArray[i] != null) {
			if (dates == null) {
				dates = dateArray[i];
				datebase = dates;
			} else {
				dates = datebase + "," + dateArray[i];
				datebase = dates;
			}
		}
	}
	System.out.println(dates);
	return dates;
}

public String getColumnstring(int s, int[][] dataArray, int c){

	//dataArray = new int[174][11];

	String data = null;
	String database = null;

	for(int i = 0; i <= c; i++){
		if (data == null) {
			data = Integer.toString(dataArray[i][s]);
			database = data;
		} else {
			data = database + "," + dataArray[i][s];
			database = data;
		}

	}

	//System.out.println(s);
	//System.out.println(c);
	//System.out.println(data);


	return data;
}




public String[] init1dStringArray(String[] sArray){

	for (int i = 0; i < sArray.length; i++) {
				sArray[i] = "";
	}
	return sArray;
}

public void print1dStringArray(String[] anArray) {
	// Prints a 2-dimensional array in a list
	// Goes through the array

			for(int i=0; i< anArray.length; i++){
					System.out.print(anArray[i]);
					System.out.print(",");
			}
			System.out.println("");
}

public void print2dIntArray(int[][] anArray) {
	// Prints a 2-dimensional array in a list
	// Goes through the array

			for(int i=0; i< anArray.length; i++){
				for(int j=0; j< anArray[i].length; j++) {
					System.out.print(anArray[i][j]);
					System.out.print(",");
				}
				System.out.println("");
			}
			System.out.println("");
}

public String[] loadfileArray() throws FileNotFoundException {

	String[] fileArray;
	fileArray = new String[3934];

	int i = 0;
	Scanner detailsIn =new Scanner(new FileReader(System.getProperty("user.dir") + "/bucketfiles.csv"));
    while (detailsIn.hasNextLine()) {
    	fileArray[i] = detailsIn.nextLine();
    	i++;
    }
	detailsIn.close();
    //this.print1dStringArray(fileArray);
	return fileArray;
}


public void run() throws IOException{
	String[][] stationArray;
	String[] fileArray;
	String filename = null;

	stationArray = new String[174][10];
	fileArray = new String[3934];
	fileArray = loadfileArray();

	//filename = fileArray[1];
	//this.MakeGraphFile(filename);

	for (int i = 0; i < 3934; i++) {
		filename = fileArray[i];
		this.MakeGraphFile(filename);
	}

	/**for (int i = 0; i < 3934; i++) {
		filename = fileArray[i];
		this.MakeGraphFile(filename);
	}**/
}

public void printHeader() {
	//0. Header Information
	//Simply prints out a string Hello World
	System.out.println("Sarah Oh, Highchart Graphs");
	System.out.println("");
}


}
