package com.lms.ctaa.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

/**
 * @Description 操作文件的工具类
 * @author zhengjiajia
 * @date 2017年4月12日
 * @version 1.0
 */
public class FileUtils {
	
	private static Logger log = LoggerFactory.getLogger(FileUtils.class);

	
	private String allowSuffix = "jpg,png,gif,jpeg";//允许文件格式
	private long allowSize = 2L;//允许文件大小
	private String fileName;
	private String[] fileNames;
	/**
	 * @return the allowSuffix
	 */
	public String getAllowSuffix() {
		return allowSuffix;
	}
	/**
	 * @param allowSuffix the allowSuffix to set
	 */
	public void setAllowSuffix(String allowSuffix) {
		this.allowSuffix = allowSuffix;
	}
	/**
	 * @return the allowSize
	 */
	public long getAllowSize() {
		return allowSize*1024*1024;
	}
	/**
	 * @param allowSize the allowSize to set
	 */
	public void setAllowSize(long allowSize) {
		this.allowSize = allowSize;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the fileNames
	 */
	public String[] getFileNames() {
		return fileNames;
	}
	/**
	 * @param fileNames the fileNames to set
	 */
	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}
	
	private String getFileNameNew(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return  sdf.format(new Date());
	}
	
	/**
	 * 
	 * @Description:多文件上传
	 * @param files  
	 * @param destDir  目标文件
	 * @param request
	 * @throws Exception
	 */
	public void uploads(MultipartFile[] files,String destDir,HttpServletRequest request) throws Exception{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
		try {
			fileNames = new String[files.length];
			int index = 0;
			for(MultipartFile file :files){
				String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
				int length = getAllowSuffix().indexOf(suffix);
				if(length == -1){
					throw new Exception("请上传允许格式的文件");
				}
				if(file.getSize() > getAllowSize()){
					throw new Exception("您上传的文件大小已经超出范围");
				}
				String realPath= request.getSession().getServletContext().getRealPath("/");
				File destFile = new File(realPath+destDir);
				if(!destFile.exists()){
					destFile.mkdirs();
				}
				String fileNameNew = getFileNameNew()+"."+suffix;
				File f = new File(destFile.getAbsoluteFile()+"\\"+fileNameNew);
				file.transferTo(f);
				f.createNewFile();
				fileNames[index++] = basePath+destDir+fileNameNew;
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * @Description:单文件上传
	 * @param file  上传文件
	 * @param destDir  传到的目标目录
	 * @param request
	 * @throws Exception
	 */
	public void upload(MultipartFile file,String destDir,HttpServletRequest request) throws Exception{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
		try {
				String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
				int length = getAllowSuffix().indexOf(suffix);
				if(length == -1){
					throw new Exception("请上传允许格式的文件");
				}
				if(file.getSize() > getAllowSize()){
					throw new Exception("您上传的文件大小已经超出范围");
				}
				String realPath= request.getSession().getServletContext().getRealPath("/");
				File destFile = new File(realPath+destDir);
				if(!destFile.exists()){
					destFile.mkdirs();
				}
				String fileNameNew = getFileNameNew()+"."+suffix;
				File f = new File(destFile.getAbsoluteFile()+"、"+fileNameNew);
				file.transferTo(f);
				f.createNewFile();
				fileName =basePath+destDir+fileNameNew;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * @Description:处理文件名显示乱码
	 * @param request
	 * @param fileName  文件名称
	 * @return
	 */
	public static String processFileName(HttpServletRequest request,String fileNames){
		String codefilename = null;
		try {
			String agent = request.getHeader("USER-AGENT");
			if(null != agent && -1 != agent.indexOf("MSIE") ||null !=agent &&
					-1 != agent.indexOf("Trident")){ //IE
				String name = java.net.URLEncoder.encode(fileNames,"UTF8");
				
				codefilename = name;
			} else if(null != agent && -1 != agent.indexOf("Mozilla")){//火狐、谷歌等
				codefilename = new String(fileNames.getBytes("UTF-8"),"iso-8859-1");
			}  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codefilename;
	}
	
	/**
	 * @Description:上传附件
	 */
	public static void uploadEnclosure(HttpServletRequest request,String enclosure){
		String enclesureUrl = enclosure;
		String realPath = request.getServletContext().getRealPath("/temp");
		String destPath = request.getServletContext().getRealPath("/fileUpload/temp");
		File oldFile = new File(new File(destPath),enclosure);
		if(!oldFile.exists()){
			File file = new File(new File(destPath),enclesureUrl);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			try {
				FileCopyUtils.copy(new File(realPath, enclesureUrl),file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 复制文件
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
	public static void copyFile(File sourceFile,File targetFile) throws IOException{
		if(sourceFile.exists()){
			BufferedInputStream inBuff = null;
			BufferedOutputStream outBuff = null;
			try {
				inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
				outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
				byte[] b = new byte[1024*5];
				int len;
				while((len=inBuff.read(b))!=-1){
					outBuff.write(b, 0, len);
				}
				outBuff.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(inBuff!=null){
					inBuff.close();
				}
				if(outBuff!=null){
					outBuff.close();
				}
			}
		}
		
	}
	
	/**
	 * 创建目录
	 * 
	 * @param descDirName
	 *            目录名,包含路径
	 * @return 1目录已存在；2创建成功；3创建失败
	 */
	public static int createDirectory(String descDirName) {
		String descDirNames = descDirName;
		if (!descDirNames.endsWith(File.separator)) {
			descDirNames = descDirNames + File.separator;
		}
		File descDir = new File(descDirNames);
		if (descDir.exists()) {
			log.debug("目录 " + descDirNames + " 已存在!");
			return 1;
		}
		// 创建目录
		if (descDir.mkdirs()) {
			log.debug("目录 " + descDirNames + " 创建成功!");
			return 2;
		} else {
			log.debug("目录 " + descDirNames + " 创建失败!");
			return 3;
		}

	}
	
	/**
	 * 按文件修改日期由远到近倒序排序
	 * @param files
	 * @return
	 */
	public static File[] fileSortByLastModifiedDESC(File[] files){
		if(files.length == 0){
			return null;
		}
		
		List<File> fileList = Arrays.asList(files);
		Collections.sort(fileList, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				Long o1Date = o1.lastModified();
				Long o2Date = o2.lastModified();
				return o2Date.compareTo(o1Date);
			}
		});
		File[] sortFile = fileList.toArray(new File[fileList.size()]);

		return sortFile;
	}
	
	
	/**
	 * 复制文件
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
	public static void copySmbFile(File sourceFile,SmbFile targetFile) throws IOException{
		if(sourceFile.exists()){
			BufferedInputStream inBuff = null;
			BufferedOutputStream outBuff = null;
			try {
				inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
				outBuff = new BufferedOutputStream(new SmbFileOutputStream(targetFile));
				byte[] b = new byte[1024*5];
				int len;
				while((len=inBuff.read(b))!=-1){
					outBuff.write(b, 0, len);
				}
				outBuff.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(inBuff!=null){
					inBuff.close();
				}
				if(outBuff!=null){
					outBuff.close();
				}
			}
		}
		
	}
}

 