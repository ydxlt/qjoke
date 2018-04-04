package com.lt.qjoke.retrofit;


import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by admin on 2018/4/4.
 */

public interface AmuseService {

    @GET("255-1")
    Observable<Amuse> getAmuseList(@QueryMap Map<String,String> params);

    class Amuse{

        public static final String TYPE_EPISODE = "29";
        public static final String TYPE_IMAGE = "10";
        public static final String TYPE_VOICE = "31";
        public static final String TYPE_VIDEO = "41";

        private int showapi_res_code;
        private String showapi_res_error;
        private ResBody showapi_res_body;

        public int getShowapi_res_code() {
            return showapi_res_code;
        }

        public void setShowapi_res_code(int showapi_res_code) {
            this.showapi_res_code = showapi_res_code;
        }

        public String getShowapi_res_error() {
            return showapi_res_error;
        }

        public void setShowapi_res_error(String showapi_res_error) {
            this.showapi_res_error = showapi_res_error;
        }

        public ResBody getShowapi_res_body() {
            return showapi_res_body;
        }

        public void setShowapi_res_body(ResBody showapi_res_body) {
            this.showapi_res_body = showapi_res_body;
        }

        public static class ResBody{
            private int ret_code;
            private PageBean pagebean;

            public int getRet_code() {
                return ret_code;
            }

            public void setRet_code(int ret_code) {
                this.ret_code = ret_code;
            }

            public PageBean getPagebean() {
                return pagebean;
            }

            public void setPagebean(PageBean pagebean) {
                this.pagebean = pagebean;
            }

            public static class PageBean{
                private int allPages;
                private List<Content> contentlist;

                public int getAllPages() {
                    return allPages;
                }

                public void setAllPages(int allPages) {
                    this.allPages = allPages;
                }

                public List<Content> getContentlist() {
                    return contentlist;
                }

                public void setContentlist(List<Content> contentlist) {
                    this.contentlist = contentlist;
                }

                public static class Content{
                    private String text;
                    private String hate;
                    private String videotime;
                    private String voicetime;
                    private String weixin_url;
                    private String profile_image;
                    private String width;
                    private String voiceuri;
                    private String type;
                    private String ct;
                    private String id;
                    private String love;
                    private String height;
                    private String _id;
                    private String voicelength;
                    private String name;
                    private String create_time;
                    private String cdn_img;

                    public String getText() {
                        return text;
                    }

                    public void setText(String text) {
                        this.text = text;
                    }

                    public String getHate() {
                        return hate;
                    }

                    public void setHate(String hate) {
                        this.hate = hate;
                    }

                    public String getVideotime() {
                        return videotime;
                    }

                    public void setVideotime(String videotime) {
                        this.videotime = videotime;
                    }

                    public String getVoicetime() {
                        return voicetime;
                    }

                    public void setVoicetime(String voicetime) {
                        this.voicetime = voicetime;
                    }

                    public String getWeixin_url() {
                        return weixin_url;
                    }

                    public void setWeixin_url(String weixin_url) {
                        this.weixin_url = weixin_url;
                    }

                    public String getProfile_image() {
                        return profile_image;
                    }

                    public void setProfile_image(String profile_image) {
                        this.profile_image = profile_image;
                    }

                    public String getWidth() {
                        return width;
                    }

                    public void setWidth(String width) {
                        this.width = width;
                    }

                    public String getVoiceuri() {
                        return voiceuri;
                    }

                    public void setVoiceuri(String voiceuri) {
                        this.voiceuri = voiceuri;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public String getCt() {
                        return ct;
                    }

                    public void setCt(String ct) {
                        this.ct = ct;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getLove() {
                        return love;
                    }

                    public void setLove(String love) {
                        this.love = love;
                    }

                    public String getHeight() {
                        return height;
                    }

                    public void setHeight(String height) {
                        this.height = height;
                    }

                    public String get_id() {
                        return _id;
                    }

                    public void set_id(String _id) {
                        this._id = _id;
                    }

                    public String getVoicelength() {
                        return voicelength;
                    }

                    public void setVoicelength(String voicelength) {
                        this.voicelength = voicelength;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getCreate_time() {
                        return create_time;
                    }

                    public void setCreate_time(String create_time) {
                        this.create_time = create_time;
                    }

                    public String getCdn_img() {
                        return cdn_img;
                    }

                    public void setCdn_img(String cdn_img) {
                        this.cdn_img = cdn_img;
                    }
                }
            }
        }
    }
}
