package com.example.projectmvp.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedResponse {

private Record[] records;
public Record[] getRecords(){
    return records;
    }
    public void setRecords(Record[] records){
    this.records=records;

    }




//    private JsonModel[] data;
//    public JsonModel[] getData(){
//        return data;
//    }
//    public void setData(JsonModel[] data){
//        this.data=data;
//    }
//        @SerializedName("records")
//        @Expose
//        private List<Record> records = null;
//
//        public List<Record> getRecords() {
//            return records;
//        }
//
//        public void setRecords(List<Record> records) {
//            this.records = records;
//        }


}
