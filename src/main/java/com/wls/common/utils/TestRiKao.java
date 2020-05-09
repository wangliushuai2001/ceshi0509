package com.wls.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestRiKao {

	
	//方法1：(10分)
	//给一个时间对象，返回该时间所在月的1日0时0分0秒。例如一个Date对象的值是2019-05-18 11:37:22
	// 则返回的结果为2019-05-01 00:00:00
	public static String getDateByInitMonth(String src){
		Date data=null;
		try {
			data= new SimpleDateFormat("yyyy-MM-dd").parse(src);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		//设置日期为档期的第一天
		c.set(Calendar.DAY_OF_MONTH,1);
		//小时至0
		c.set(Calendar.HOUR_OF_DAY, 0);
		//分钟至0
		c.set(Calendar.MINUTE, 0);
		//秒至0
		c.set(Calendar.SECOND, 0);
		//毫秒至0
		c.set(Calendar.MILLISECOND,0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm:ss");
		Date date;
		try {
			date=sdf.parse(sdf.format(new Date(new Long(c.getTimeInMillis()))));
			return sdf.format(date);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	* 方法2：(10分)
	* 给任意一个时间对象，返回该时间所在月的最后日23时59分59秒，需要考虑月大月小和二月特殊情况。
	* 例如一个Date对象的值是2019-05-18 11:37:22，则返回的时间为2019-05-31 23:59:59
	* 例如一个Date对象的值是2019-02-05 15:42:18，则返回的时间为2019-02-28 23:59:59
	*/
	public static String getDateByFullMonth(String src){
		Date data=null;
		try {
			data= new SimpleDateFormat("yyyy-MM-dd").parse(src);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		//当月最后一天
		c.set(Calendar.DAY_OF_MONTH,c.getActualMaximum(Calendar.DAY_OF_MONTH));
		//小时至23
		c.set(Calendar.HOUR_OF_DAY, 23);
		//分钟至59
		c.set(Calendar.MINUTE, 59);
		//秒至59
		c.set(Calendar.SECOND, 59);
		//毫秒至999
		c.set(Calendar.MILLISECOND, 999);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm:ss");
		Date date;
		try {
			date=sdf.parse(sdf.format(new Date(new Long(c.getTimeInMillis()))));
			return sdf.format(date);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
       return null;
	}
	
	
	//方法1：给定一个文件名，返回该文件名的扩展名，例如“aaa.jpg”，返回“.jpg”
	public static String getExtendName(String fileName){
		
		String[] split = fileName.split(".");
		
		return split[1];
	}
	
	
	//、按要求创建StreamUtil流处理工具类及方法，不得有Bug（40分）
	//分数明细如下：
	/*
	* 方法1：批量关闭流，参数能传入无限个。(10分)
	* 例如传入FileInputStream对象、JDBC中Connection对象都可以关闭，并且参数个数不限。
	*/
	public static void closeAll(File srcFile){
	      
		  try {
			  FileInputStream fis=	new FileInputStream(srcFile);
			  try {
				fis.read();
				fis.close();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//方法2：传入一个文本文件对象，默认为UTF-8编码，返回该文件内容(10分)，要求方法内部调用上面第1个方法关闭流
	public static String readTextFile(InputStream src){
		BufferedReader reader = new BufferedReader(new InputStreamReader(src));
		boolean s=false;
		String ss="";
		try {
			while(s=reader.readLine()!=null){
				 ss+=s;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ss;
	}
	
	//方法3：传入文本文件对象，返回该文件内容(10分)，并且要求内部调用上面第2个方法(5分)。* 这是典型的方法重载，记住了吗？少年…
	
	public static String readTextFile(File txtFile){
		FileReader reader;
		try {
			reader = new FileReader(txtFile);
			BufferedReader bReader = new BufferedReader(reader);
			StringBuilder sb = new StringBuilder();

			boolean s=false;
			String ss="";
			try {
				while(s=bReader.readLine()!=null){
					 ss+=s;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 return null;
	}
}
