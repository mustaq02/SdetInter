package utils;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String screenshotName) throws IOException {
        String dir = "reports/screenshots/";
        Files.createDirectories(Paths.get(dir));

        String filePath = dir + screenshotName + "_" + System.currentTimeMillis() + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(filePath));

        return filePath;
    }
}
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.text.SimpleDateFormat;
//import java.util.Date;

//public class ScreenshotUtil {
//    public static String capture(WebDriver driver, String name) {
//        TakesScreenshot ts = (TakesScreenshot) driver;
//        File src = ts.getScreenshotAs(OutputType.FILE);
//
//        String folder = "screenshots";
//        new File(folder).mkdirs();
//
//        String path = folder + "/" + name + "_" + System.currentTimeMillis() + ".png";
//
//        try {
//            Files.copy(src.toPath(), Paths.get(path));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return path;
//    }
//}

//public class ScreenshotUtil {
//    public static String capture(WebDriver driver, String name) {
//        TakesScreenshot ts = (TakesScreenshot) driver;
//        File src = ts.getScreenshotAs(OutputType.FILE);
//        String path = "screenshots/" + name + "_" + System.currentTimeMillis() + ".png";
//        try {
//            Files.copy(src.toPath(), Paths.get(path));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return path;  // 🔑 return file path
//    }
//}

//public class ScreenshotUtil {
//
//    public static String capture(WebDriver driver, String screenshotName) {
//        String date = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date()); // milliseconds added
//        String filePath = System.getProperty("user.dir") + "/screenshots/" + screenshotName + "_" + date + ".png";
//
//        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        try {
//            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/screenshots/"));
//            Files.copy(srcFile.toPath(), Paths.get(filePath)); // no overwrite, always unique file
//            System.out.println("📸 Screenshot saved at: " + filePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return filePath;
//    }
//}

//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//
//public class ScreenshotUtil {
//    public static void capture(WebDriver driver, String name) throws IOException {
//        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        File dest = new File("screenshots/" + name + ".html");
//        dest.getParentFile().mkdirs();
//        Files.copy(src.toPath(), dest.toPath());
//    }
//}