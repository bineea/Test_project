package test_project;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * Created by bineea on 2016/11/12.
 */
public class Test_zipFile
{
    public static void main(String[] args)
    {
        Test_zipFile zip = new Test_zipFile();
        String zipPath = "E:";
        String dir = "E:" + File.separator + "测试";
        String zipFileName = "test.zip";
        try
        {
            zip.zipCompress(dir, zipPath, zipFileName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

//        String zipFilePath = "E:/test.zip";
//        String unZipFilePath = "E:/";
//        try
//        {
//            zip.zipDecompress(zipFilePath,unZipFilePath);
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
    }

    //压缩
    private void zipCompress(String srcPath,String zipPath,String zipFileName)
        throws Exception
    {
        if(isEmpty(srcPath) || isEmpty(zipPath) || isEmpty(zipFileName))
            throw new Exception("参数不能为空！！！");
        ZipOutputStream zos = null;
        try
        {
            File srcFile = new File(srcPath);
            if(srcFile.isDirectory() && zipPath.contains(srcPath))
                throw new Exception("压缩路径不能为源文件路径的子路径！！！");
            File zipDir = new File(zipPath);
            if(!zipDir.exists() || !zipDir.isDirectory())
            {
                zipDir.mkdirs();
            }
            String zipFilePath = zipPath + File.separator + zipFileName;
            File zipFile = new File(zipFilePath);
            if(zipFile.exists())
            {
                SecurityManager s = new SecurityManager();
                s.checkDelete(zipFilePath);
                zipFile.delete();
            }
            CheckedOutputStream cos = new CheckedOutputStream(new FileOutputStream(zipFile), new CRC32());
            zos = new ZipOutputStream(cos);
            String srcRootDir = srcPath;
            if(srcFile.isFile())
            {
                int index = srcRootDir.lastIndexOf(File.separator);
                if(index != -1)
                {
                    srcRootDir = srcPath.substring(0,index);
                }
            }
            zipCompress(srcRootDir,srcFile,zos);
            zos.flush();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(zos != null)
                    zos.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void zipCompress(String srcRootDir, File srcFile, ZipOutputStream zos)
            throws Exception
    {
        if(srcFile == null)
            return;
        if(srcFile.isFile())
        {
            char[] data = new char[1024];
            String subPath = srcFile.getAbsolutePath();
            if(subPath.contains(srcRootDir)) {
                subPath = subPath.substring(srcRootDir.length() + File.separator.length());
            }
            ZipEntry entry = new ZipEntry(subPath);
            zos.putNextEntry(entry);
            FileReader reader = new FileReader(srcFile);
            while(reader.read(data,0,1024) != -1)
            {
                byte[] d = (new String(data)).getBytes("iso-8859-1");
                zos.write(d,0,d.length);
            }
            reader.close();
            zos.closeEntry();
        }
        else
        {
            File[] childFileList = srcFile.listFiles();
            for (File aChildFileList : childFileList) {
                zipCompress(srcRootDir, aChildFileList, zos);
            }
        }
    }

    //解压缩
    public void zipDecompress(String zipFilePath,String unZipFilePath) throws Exception
    {
        if(isEmpty(zipFilePath) || isEmpty(unZipFilePath))
            throw new Exception("解压方法参数不能为空！！！");
        File zipFile = new File(zipFilePath);
        String fileName = zipFile.getName();
        if(!isEmpty(fileName))
            fileName = fileName.substring(0,fileName.lastIndexOf("."));
//        unZipFilePath = unZipFilePath + File.separator +fileName;
//        File unZipFile = new File(unZipFilePath);
//        if(!unZipFile.exists() || !unZipFile.isDirectory())
//        {
//            unZipFile.mkdirs();
//        }

        ZipEntry entry;
        String entryFilePath,entryDirPath;
        File entryFile,entryDir;
        int count,bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        ZipFile zip = new ZipFile(zipFile);
        Enumeration<ZipEntry> enumeration = (Enumeration<ZipEntry>) zip.entries();
        while (enumeration.hasMoreElements())
        {
            entry = enumeration.nextElement();
//            entryFilePath = unZipFilePath + File.separator + entry.getName();
//            int index = entryFilePath.lastIndexOf(File.separator);
//            if(index != -1)
//                entryDirPath = entryFilePath.substring(0,index);
//            else
//                entryDirPath = "";
//            entryDir = new File(entryDirPath);
//            if(!entryDir.exists() || !entryDir.isDirectory())
//                entryDir.mkdirs();
//            entryFile = new File(entryFilePath);
//            if(entryFile.exists())
//            {
//                SecurityManager securityManager = new SecurityManager();
//                securityManager.checkDelete(entryFilePath);
//                entryFile.delete();
//            }
            BufferedInputStream bis = new BufferedInputStream(zip.getInputStream(entry));
//            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(entryFile));
            while ((count = bis.read(buffer,0,bufferSize)) != -1)
            {
//                bos.write(buffer,0,count);
                System.out.println("文件内容为："+new String(buffer));
            }
//            bos.flush();
//            bos.close();
        }
    }

    private boolean isEmpty(String str)
    {
        return (str == null || str.isEmpty() || str.trim().isEmpty());
    }
}
