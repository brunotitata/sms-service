//package br.com.sms.model;
//
//import java.util.Arrays;
//
//public enum ProductSale {
//
//    PACKAGE_10("c0999018-dd9f-40a8-8165-c0cb695cdb5f", 2.50, 10),
//    PACKAGE_50("b5d9916e-4e4a-4b14-b3d0-fd0c8415c411", 5.50, 50),
//    PACKAGE_100("1dac36e3-a362-4d1c-b8c6-bef40b4f4195", 10.50, 100),
//    PACKAGE_500("a3e68425-2835-4dfb-a1c2-ef5a5785d73b", 15.00, 500),
//    PACKAGE_1000("0c0c23b7-2618-4589-bcfc-5f3eecc912e4", 40.00, 1000);
//
//    private String uuid;
//    private Double price;
//    private Integer quantity;
//
//    private ProductSale(String uuid, Double price, Integer quantity) {
//	this.uuid = uuid;
//	this.price = price;
//	this.quantity = quantity;
//    }
//
//    public String getUuid() {
//	return uuid;
//    }
//
//    public void setUuid(String uuid) {
//	this.uuid = uuid;
//    }
//
//    public Double getPrice() {
//	return price;
//    }
//
//    public void setPrice(Double price) {
//	this.price = price;
//    }
//
//    public Integer getQuantity() {
//	return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//	this.quantity = quantity;
//    }
//    
//    public static Integer getProduct(String uuid, Double price) {
//	return Arrays.asList(ProductSale.values())
//		.stream()
//		.filter(product -> product.getUuid().equals(uuid) && product.getPrice().equals(price))
//		.map(ProductSale::getQuantity)
//		.findFirst()
//		.orElseThrow(() -> new RuntimeException("Houve um erro ao obter pacotes de SMS!"));
//    }
//
//}
