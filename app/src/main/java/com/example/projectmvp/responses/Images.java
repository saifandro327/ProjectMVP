package com.example.projectmvp.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("width")
        @Expose
        private Integer width;
        @SerializedName("height")
        @Expose
        private Integer height;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("filename")
        @Expose
        private String filename;
        @SerializedName("size")
        @Expose
        private Integer size;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("thumbnails")
        @Expose
        private Thumbnails__1 thumbnails;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Thumbnails__1 getThumbnails() {
            return thumbnails;
        }

        public void setThumbnails(Thumbnails__1 thumbnails) {
            this.thumbnails = thumbnails;
        }

    }
