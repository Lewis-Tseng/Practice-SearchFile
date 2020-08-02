package com.util.searchfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SearchFile {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("請輸入資料夾路徑");
		String filepath = sc.next().trim();
	
		File dir = new File(filepath);
		if (!dir.isDirectory()) {
			System.out.println("這不是目錄");
		}

		String contents[] = dir.list();
		File dirarry[] = dir.listFiles();

		if (contents.length == 0) {
			System.out.println("目錄" + dir.getName() + "內無檔案");
		}

		File fileListtext = new File("./searchLogfile/putfiletext" + ".txt");
		File searchLogtext = new File("./searchLogfile/searchLogtext" + ".txt");
		if (!fileListtext.exists()) {
			try {
				fileListtext.getParentFile().mkdir();
				fileListtext.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (!searchLogtext.exists()) {
			try {
				fileListtext.getParentFile().mkdir();
				searchLogtext.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {                                // append = fales
			FileWriter fw = new FileWriter(fileListtext, false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			FileWriter fw1 = new FileWriter(searchLogtext, true);
			BufferedWriter bw1 = new BufferedWriter(fw1);
			PrintWriter pw1 = new PrintWriter(bw1);

			int j = 0, k = 0, l = 0;
			boolean turnoff = true;
			while (turnoff) {

				++l;
				System.out.println();
				System.out.println("請輸入搜尋名稱(輸入0為停止搜尋)");
				String searchtext = sc.next().trim();
				if (searchtext.equals("0")) {
					turnoff = false;
					System.out.println("結束");
					break;
				}
				if (l > 0) {
					if (l > 1) {
						pw1.println();
						pw1.println();
					}
					// 取得現在時間
					Calendar cal = Calendar.getInstance();
					java.util.Date du = cal.getTime();
					pw1.println("第" + l + "次搜尋時間 ： " + du);
					pw1.println();
					pw1.println();
					pw1.flush();
				}

				// 印出檔案名稱至text文字檔
				for (int i = 0; i < contents.length; i++) {
					if (l == 1) {
						pw.println(++j);
						pw.println("檔案名稱 ： " + contents[i]);
						pw.println("檔案大小 ： " + (int) (dirarry[i].length() / Math.pow(10, 6)) + "MB");
						pw.println();
						pw.println("======================");
						pw.flush();
					}
					// 比對是否有搜尋關鍵字
					if (contents[i].contains(searchtext)) {
						++k;
						pw1.println("第" + k + "項");
						pw1.println("檔案名稱 ： " + contents[i]);
						pw1.println("檔案大小 ： " + (int) (dirarry[i].length() / Math.pow(10, 6)) + "MB");
						pw1.println("======================");
						pw1.flush();

						System.out.println("第 " + k + " 項 Bingo");
						System.out.println(contents[i]);
					}
				}
				if (k == 0 && turnoff == true) {
					System.out.println("沒有此檔案");
					pw1.println("沒有此檔案");
					pw1.flush();
				}
				k = 0;
			}

			pw.println("總共" + contents.length + "個檔案");
			pw.flush();

			sc.close();
			pw1.close();
			bw1.close();
			fw1.close();
			pw.close();
			bw.close();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace(System.err);
		}

	}
//	Map<String, String> map = new HashMap<String, String>();
//	map.put("4", filepath);
//	String filegps = map.get("3");
//	File dir = new File(filegps);
//	public double prettynum(double d){
//			
//			String prtnum = String.valueOf(d);
//			
//			prtnum.substring(0, endIndex)
//			
//			return d;
//				
//	System.out.println("停止搜尋請按1");
//	int i = sc.nextInt();
//	switch(i) {
//	case 1:
//		turnoff = false;
//	}	

}
