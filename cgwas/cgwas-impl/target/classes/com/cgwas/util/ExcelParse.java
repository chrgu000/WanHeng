package com.cgwas.util;


public class ExcelParse {
	protected IExcelParse excelParse = null;

	/**
	 * 加载实例，根据不同版本的Excel文件，加载不同的具体实现实例
	 * 
	 * @param path
	 * @return
	 */
	private boolean getInstance(String path) throws Exception {
		path = path.toLowerCase();
		if (path.endsWith(".xls")) {
			excelParse = new ExcelParse2003();
		} else if (path.endsWith(".xlsx")) {
			try{
				excelParse = new ExcelParse2007();
			}catch(Exception e){
				e.printStackTrace();
			}
			 
		} else {
			throw new Exception("对不起，目前系统不支持对该版本Excel文件的解析。");
		}
		return true;
	}

	/**
	 * 获取excel工作区
	 * 
	 * @param path
	 * @throws Exception
	 */
	public void loadExcel(String filePathAndName) throws Exception {
		getInstance(filePathAndName);
		excelParse.loadExcel(filePathAndName);
	}

	/**
	 * 获取sheet页名称
	 * 
	 * @param sheetNo
	 * @return
	 */
	public String getSheetName(int sheetNo) {
		return excelParse.getSheetName(sheetNo);
	}

	/**
	 * 获取sheet页数
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getSheetCount() throws Exception {
		return excelParse.getSheetCount();
	}

	/**
	 * 获取sheetNo页列数
	 * 
	 * @param sheetNo
	 * @return
	 */
	public int getColumn(int sheetNo) {
		return excelParse.getColumn(sheetNo);
	}

	/**
	 * 获取sheetNo页行数
	 * 
	 * @param sheetNo
	 * @return
	 * @throws Exception
	 */
	public int getRowCount(int sheetNo) {
		return excelParse.getRowCount(sheetNo);
	}

	/**
	 * 获取sheetNo页行数(含有操作或者内容的真实行数)
	 * 
	 * @param sheetNo
	 * @return
	 * @throws Exception
	 */
	public int getRealRowCount(int sheetNo) {
		return excelParse.getRealRowCount(sheetNo);
	}

	/**
	 * 读取第sheetNo个sheet页中第rowNo行第cellNo列的数据
	 * 
	 * @param sheetNo
	 *            sheet页编号
	 * @param rowNo
	 *            行号
	 * @param cellNo
	 *            列号
	 * @return 返回相应的excel单元格内容
	 * @throws Exception
	 */
	public String readExcelByRowAndCell(int sheetNo, int rowNo, int cellNo)
			throws Exception {
		return excelParse.readExcelByRowAndCell(sheetNo, rowNo, cellNo);
	}

	/**
	 * 读取指定SHEET页指定行的Excel内容
	 * 
	 * @param sheetNo
	 *            指定SHEET页
	 * @param lineNo
	 *            指定行
	 * @return
	 * @throws Exception
	 */
	public String[] readExcelByRow(int sheetNo, int rowNo) throws Exception {
		return excelParse.readExcelByRow(sheetNo, rowNo);
	}

	/**
	 * 读取指定SHEET页指定列中的数据
	 * 
	 * @param sheetNo
	 *            指定SHEET页
	 * @param cellNo
	 *            指定列号
	 * @return
	 * @throws Exception
	 */
	public String[] readExcelByCell(int sheetNo, int cellNo) throws Exception {
		return excelParse.readExcelByCell(sheetNo, cellNo);
	}

	/**
	 * 关闭excel工作区，释放资源
	 * 
	 */
	public void close() {
		 excelParse.close();
	}
}
