package com.util.searchfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SearchFile {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

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

		try { // 用 OutputStreamWriter可設定輸出時的編碼 // 設定是否要串接 append = fales
			OutputStreamWriter fw1 = new OutputStreamWriter(new FileOutputStream(fileListtext, false), "UTF-8");
//			FileWriter fw1 = new FileWriter(fileListtext, false);
			BufferedWriter bw1 = new BufferedWriter(fw1);
			PrintWriter pw1 = new PrintWriter(bw1);

			OutputStreamWriter fw2 = new OutputStreamWriter(new FileOutputStream(searchLogtext, true), "UTF-8");
//			FileWriter fw2 = new FileWriter(searchLogtext, true);
			BufferedWriter bw2 = new BufferedWriter(fw2);
			PrintWriter pw2 = new PrintWriter(bw2);

			boolean switch_a = true;
			out: while (switch_a) {
				System.out.println("請輸入資料夾路徑(輸入0為結束)");
				String filepath = sc.next().trim();
//				InputStreamReader isr = new InputStreamReader(System.in);
//				BufferedReader br = new BufferedReader(isr);
//				String filepath = br.readLine();
						
				filepath = new String(filepath.getBytes(), Charset.defaultCharset());
				
				if ("0".equals(filepath)) {
					System.out.println("結束程式");
					break;
				}

				File dir = new File(filepath);
				System.out.println(dir.getPath());
				if (!dir.isDirectory()) {
					System.out.println("這不是目錄");
					System.out.println();
					continue out;
				}

				String contents[] = dir.list();
				File dirarry[] = dir.listFiles();
				if (contents == null) {
					System.out.println("目錄" + dir.getName() + "內無檔案");
					System.out.println();
					continue out;
				}

				int j = 0, k = 0, l = 0;
				boolean switch_b = true;
				while (switch_b) {
					++l;
					System.out.println();
					System.out.println("請輸入搜尋名稱(輸入0為停止搜尋此資料夾)");
					String searchtext = sc.next().trim();
					if ("0".equals(searchtext)) {
						switch_b = false;
						System.out.println("此資料夾搜尋結束");
						System.out.println();
						continue out;
					}
					if (l > 0) {
						if (l > 1) {
							pw2.println();
							pw2.println();
						}
						// 取得現在時間
						Calendar cal = Calendar.getInstance();
						java.util.Date du = cal.getTime();
						pw2.println();
						pw2.println("第" + l + "次搜尋時間 ： " + du);
						pw2.println();
						pw2.println();
						pw2.flush();
					}

					// 印出檔案名稱至text文字檔
					for (int i = 0; i < contents.length; i++) {
						if (l == 1) {
							pw1.println(++j);
							pw1.println("檔案名稱 ： " + contents[i]);
							pw1.println("檔案大小 ： " + (int) (dirarry[i].length() / Math.pow(10, 6)) + "MB");
							pw1.println();
							pw1.println("======================");
							pw1.flush();
						}
						// 比對是否有搜尋關鍵字
						if (contents[i].contains(searchtext)) {
							++k;
							pw2.println("第" + k + "項");
							pw2.println("檔案名稱 ： " + contents[i]);
							pw2.println("檔案大小 ： " + (int) (dirarry[i].length() / Math.pow(10, 6)) + "MB");
							pw2.println("======================");
							pw2.flush();

							System.out.println("第 " + k + " 項 Bingo");
							System.out.println(contents[i]);
						}
					}
					pw1.println("總共" + contents.length + "個檔案");
					pw1.flush();

					if (k == 0 && switch_b == true) {
						System.out.println("沒有此檔案");
						pw2.println("沒有此檔案");
						pw2.flush();
					}
					k = 0;
				}
			}

			sc.close();
			pw1.close();
			bw1.close();
			fw1.close();
			pw2.close();
			bw2.close();
			fw2.close();

		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}
	

	// 其他轉換路徑亂碼問題的參考資料
//	BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "UTF-8")); 
//	String data;
//    while ((data = reader.readLine()) != null)
//        System.out.println(data);

	// 轉換編碼成UTF-8
//	filepath = new String(filepath.getBytes("Unicode"), Charset.defaultCharset());
//	filepath = new String(filepath.getBytes("GBK"), "UTF-8");

//	try {
//		String filepath_format = SearchFile.class.getResource(filepath).toURI().getPath();
//		filepath_format_done = filepath_format;
//	} catch (URISyntaxException e) {
//		e.printStackTrace();
//	}

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
