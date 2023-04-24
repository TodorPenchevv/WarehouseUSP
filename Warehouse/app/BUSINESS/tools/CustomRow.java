package app.business.tools;

import app.orm.Invoice_Good;

import java.util.List;

public class CustomRow {
    private String userName;
    private String partnerName;
    private String role;
    private String good;
    private String date;
    private String transaction;
    private String details;
    private double price;
    private double totalPrice;
    private int quantity;
    private int minQuantity;

    public String getUserName() {
        return userName;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public String getRole() {
        return role;
    }

    public String getGood() {
        return good;
    }

    public String getDate() {
        return date;
    }

    public String getTransaction() {
        return transaction;
    }

    public String getDetails(){
        return details;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    private CustomRow(Builder builder){
        this.userName = builder.userName;
        this.partnerName = builder.partnerName;
        this.role = builder.role;
        this.good = builder.good;
        this.date = builder.date;
        this.transaction = builder.transaction;
        this.details = builder.details;
        this.price = builder.price;
        this.totalPrice = builder.totalPrice;
        this.quantity = builder.quantity;
        this.minQuantity = builder.minQuantity;
    }


    public static class Builder {
        private String userName;
        private String partnerName;
        private String role;
        private String good;
        private String date;
        private String transaction;
        private String details;
        private double price;
        private double totalPrice;
        private int quantity;
        private int minQuantity;

        public Builder withUserName(String userName){
            this.userName = userName;
            return this;
        }

        public Builder withPartnerName(String partnerName){
            this.partnerName = partnerName;
            return this;
        }

        public Builder withRole(String role){
            this.role = role;
            return this;
        }

        public Builder withGood(String good){
            this.good = good;
            return this;
        }

        public Builder withDate(String date){
            this.date = date;
            return this;
        }

        public Builder withTransaction(String transaction){
            this.transaction = transaction;
            return this;
        }

        public Builder withDetails(String details){
            this.details = details;
            return this;
        }

        public Builder withPrice(Double price){
            this.price = price;
            return this;
        }

        public Builder withTotalPrice(Double totalPrice){
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder withQuantity(int quantity, int minQuantity){
            this.quantity = quantity;
            this.minQuantity = minQuantity;
            return this;
        }

       public CustomRow build(){
            return new CustomRow(this);
       }
    }

    public static String processDetails(List<Invoice_Good> list){
        StringBuilder builder = new StringBuilder();
        for (Invoice_Good ig : list)
            builder.append(ig.getQuantity()).append("x ").append(ig.getGood().getGood()).append(", ").append(ig.getPrice()).append("лв.\n");
        return builder.toString();
    }
}