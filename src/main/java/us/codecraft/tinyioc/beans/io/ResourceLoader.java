package us.codecraft.tinyioc.beans.io;

import java.net.URL;

/**
 * ResourceLoader 类，用于加载资源。
 * 它通过类加载器获取资源的 URL，然后返回一个 UrlResource 对象。
 *
 * author yihua.huang@dianping.com
 */
public class ResourceLoader {

    public Resource getResource(String location){
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
