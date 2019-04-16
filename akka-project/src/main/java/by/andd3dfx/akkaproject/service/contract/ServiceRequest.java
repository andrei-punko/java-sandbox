package by.andd3dfx.akkaproject.service.contract;

import by.andd3dfx.akkaproject.model.DataItem;

public class ServiceRequest {
    private DataItem dataItem;

    public DataItem getDataItem() {
        return dataItem;
    }

    public void setDataItem(DataItem dataItem) {
        this.dataItem = dataItem;
    }
}
