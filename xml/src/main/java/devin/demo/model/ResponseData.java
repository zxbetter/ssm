package devin.demo.model;

import java.util.List;

/**
 * Created by devin on 2017/1/4.
 */
public class ResponseData<T> {
    private boolean status;
    private List<T> dataList;
    private T data;
    private String desc;

    public boolean isStatus() {
        return status;
    }

    public ResponseData setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public ResponseData setDataList(List<T> dataList) {
        this.dataList = dataList;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseData setData(T data) {
        this.data = data;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public ResponseData setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
