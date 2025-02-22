package us.codecraft.tinyioc.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Resource 是 Spring 内部定位资源的接口。
 *
 * author yihua.huang@dianping.com
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
