package com.example.sxd.thanksgivinghall.bean;

public class FinGood {

    private static final long serialVersionUID = 1L;
    private String id;
    private String goodName;		// 商品名称
    private Double price;		// 单价
    private Double quantity;		// 数量
    private String unit;		// 单位
    private String supplier;		// 供应商
    private String spAddress;		// 供应地址
    private String buyer;		// 采购人

    public FinGood() {
        super();
    }

    public FinGood(String id){

    }

    public FinGood(String name, String priceStr, String amountStr, String unit, String address, String supplier, String buyer) {
        this.goodName = name;
        this.price = Double.parseDouble(priceStr);
        this.quantity = Double.parseDouble(amountStr);
        this.unit = unit;
        this.spAddress = address;
        this.supplier = supplier;
        this.buyer = buyer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSpAddress() {
        return spAddress;
    }

    public void setSpAddress(String spAddress) {
        this.spAddress = spAddress;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

}