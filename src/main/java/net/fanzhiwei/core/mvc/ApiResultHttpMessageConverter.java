package net.fanzhiwei.core.mvc;

import java.io.IOException;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

/**
 * @author fanzhiwei
 */
@Component
public class ApiResultHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    private static final Logger LOG = LoggerFactory.getLogger(ApiResultHttpMessageConverter.class);

    /**
     * 转化rpc返回的对象为应用统一的ajax渲染对象(public方法,单测覆盖率)
     *
     * @param object
     * @return ApiResult or DataTableApiResult
     */
    public Object getAppReturnObj(Object object) {
        // 非jsonp请求
        if (!(object instanceof MappingJacksonValue)) {
            return convertToAppReturnTypeObj(object);
        }

        // jsonp请求被jackson封装成MappingJacksonValue对象,真正的rpc返回值在成员对象value里面
        MappingJacksonValue mappingJacksonValue = (MappingJacksonValue)object;
        mappingJacksonValue.setValue(convertToAppReturnTypeObj(mappingJacksonValue.getValue()));
        return mappingJacksonValue;
    }

    /**
     * 转化rpc返回的对象为统一的ajax渲染对象
     *
     * @param object
     * @return ApiResult
     */
    private Object convertToAppReturnTypeObj(Object object) {

        // 如果rpc返回值显示的设置为ApiResult,直接返回
        // 支持rpc代码业务开发人员自定义ApiResult中的值，很灵活有木有
        if (object instanceof ApiResult) {
            return object;
        }

        // 如果有其他类型的统一封装对象, add up here

        // 默认封装为ApiResult
        return ApiResult.createSuccessInstance(object);
    }

    /**
     * 写对象到前台(重写父类的writeInternal方法,进行hack)
     *
     * @param object
     * @param type
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        // wing hack here : 转化rpc返回的对象为应用统一的ajax渲染对象
        object = getAppReturnObj(object);

        // 以下执行逻辑为jackson原来的代码逻辑
        super.writeInternal(object, type, outputMessage);
    }

}
