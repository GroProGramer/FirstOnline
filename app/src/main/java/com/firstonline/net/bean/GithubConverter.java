package com.firstonline.net.bean;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/8/16 0016.
 */
public class GithubConverter implements Converter<ResponseBody,String> {

    public static final GithubConverter.Factory factory=new GithubConverter.Factory(){
        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            //return super.responseBodyConverter(type, annotations, retrofit);
            /*if(type==Contributor.class){
                GithubConverter converter=new GithubConverter();
                return  converter;
            }*/

            return  new GithubConverter();
        }
    };
    @Override
    public String convert(ResponseBody responseBody) throws IOException {

        return responseBody.string();
    }
}
