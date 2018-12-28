package com.zjrt.util;

import com.zjrt.config.InstantiationTracingBeanPostProcessor;
import com.zjrt.exception.LogicException;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Properties;

/**
 * 文件操作工具类，主要操作文件上传和
 * Created by Apple on 2018/2/11.
 */
@Component("fileUtil")
public class FileUtils extends PropertyPlaceholderConfigurer {
    private static Logger logger = Logger.getLogger(FileUtils.class);

//    private static  String UPLOAD_FILE_PATH = "/Users/Apple/java/fileUpload";
    private static FileUtils utils = null;
    private static Properties props;
    static {
        try {
            props = new Properties();
            InputStream in = FileUtils.class.getClassLoader().getResourceAsStream("sys.properties");
            props.load(in);
        } catch (IOException e) {
            logger.error("加载配置文件异常", e);
        }

    }

    private FileUtils() {
    }

    public synchronized static FileUtils getInstance() {
        if (utils == null) {
            utils = new FileUtils();
        }
        return utils;
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory,
                                     Properties props) throws BeansException {
        super.processProperties(beanFactory, props);
        this.props = props;
    }

    /**
     * 写出文件
     *
     * @param file
     * @param fileName
     * @return
     */
    public void writeFile(MultipartFile file, String fileName) {
        /**
         * 检查磁盘目录，创建文件，输出文件
         */
        String aa = InstantiationTracingBeanPostProcessor.aa+"/"+fileName;
        File file1 = new File(aa);
        if (file1.exists()){
            throw new LogicException("文件已存在");
        }
        try {
            OutputStream os = new FileOutputStream(file1);
            byte[] bytes = file.getBytes();
            os.write(bytes);
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPath(String key) {
        return props.getProperty(key);
    }

    public void checkedDirectory(String path) {
        File file = new File(path);
        if (!file.isDirectory() || !file.exists()) {
            logger.debug("make dir " + path);
            file.mkdir();
            logger.debug("make dir end");
        }

    }

}
