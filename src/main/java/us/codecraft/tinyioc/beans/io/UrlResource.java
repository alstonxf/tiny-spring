package us.codecraft.tinyioc.beans.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 这段代码实现了Resource接口的一个实现类UrlResource，用于获取指定URL对应的输入流。当需要获取指定URL资源的输入流时，可以通过创建一个UrlResource对象，将该URL传入构造函数来实现。调用getInputStream()方法返回该URL对应资源的输入流。
 *
 * 具体而言，UrlResource类的构造函数接受一个URL参数，表示需要获取输入流的资源URL，然后在getInputStream()方法中，通过该URL创建一个URLConnection对象，连接该URL，并获取该URL对应的输入流并返回。
 *//**
 * 实现了Resource接口，封装了URL，用于读取URL对应资源的输入流
 */
public class UrlResource implements Resource {

    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    /**
     * 打开连接获取输入流
     * @return URL对应资源的输入流
     * @throws IOException 读取过程中出现的IO异常
     */
    @Override
    public InputStream getInputStream() throws IOException{
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();
    }
}
