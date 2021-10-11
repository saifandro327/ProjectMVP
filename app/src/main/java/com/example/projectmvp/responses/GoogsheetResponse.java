package com.example.projectmvp.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoogsheetResponse {


      private JsonModel[] data;
      public JsonModel[] getData(){
          return data;
    }
    public void setData(JsonModel[] data){
           this.data=data;
    }
}
