package com.xiyang.xiyang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/7/2.
 */
public class InformationModel implements Serializable {
    private List<NoticeVOListBean> noticeVOList;

    public List<NoticeVOListBean> getNoticeVOList() {
        return noticeVOList;
    }

    public void setNoticeVOList(List<NoticeVOListBean> noticeVOList) {
        this.noticeVOList = noticeVOList;
    }

    public static class NoticeVOListBean {
        /**
         * id : 1402917753999986688
         * image : a.png
         * title : test1
         * content : <div target="_blank" data-visited="off" data-a-5fa5e84a="" class=""><span class="wenda-abstract-listnum" data-a-5fa5e84a="">2.</span><span data-a-5fa5e84a="">第二步:查看本地分支和远程分支 1、cd PrettyGirls 到工程目录下; 2、git branch -al 查看本地和远程的所有分支。 这里成功看到了所有的分支:其中master是本地分支,前面的星号...</span></div>
         * createTime : 2021-07-03 12:35:22
         * updateTime : 2021-07-03 12:35:22
         */

        private String id;
        private String image;
        private String title;
        private String content;
        private String createTime;
        private String updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
