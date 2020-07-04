//package br.com.sms.smsservice.model;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//import org.junit.jupiter.api.Test;
//
//import br.com.sms.model.ProductSale;
//
//public class ProductSaleTest {
//
//    @Test
//    public void getProduct() {
//
//	Integer PACKAGE_10 = ProductSale.getProduct("c0999018-dd9f-40a8-8165-c0cb695cdb5f", 2.50);
//	Integer PACKAGE_50 = ProductSale.getProduct("b5d9916e-4e4a-4b14-b3d0-fd0c8415c411", 5.50);
//	Integer PACKAGE_100 = ProductSale.getProduct("1dac36e3-a362-4d1c-b8c6-bef40b4f4195", 10.50);
//	Integer PACKAGE_500 = ProductSale.getProduct("a3e68425-2835-4dfb-a1c2-ef5a5785d73b", 15.00);
//	Integer PACKAGE_1000 = ProductSale.getProduct("0c0c23b7-2618-4589-bcfc-5f3eecc912e4", 40.00);
//
//	assertThat(PACKAGE_10).isEqualTo(10);
//	assertThat(PACKAGE_50).isEqualTo(50);
//	assertThat(PACKAGE_100).isEqualTo(100);
//	assertThat(PACKAGE_500).isEqualTo(500);
//	assertThat(PACKAGE_1000).isEqualTo(1000);
//    }
//
//    @Test
//    public void getProductError() {
//
//	assertThatThrownBy(() -> ProductSale.getProduct("0c0c23b7-2618-4589-bcfc-5f3eecc912e4", 15.00))
//		.hasMessage("Houve um erro ao obter pacotes de SMS!").isInstanceOf(RuntimeException.class);
//    }
//
//    @Test
//    public void getProductError2() {
//
//	assertThatThrownBy(() -> ProductSale.getProduct("0c0c23b7-2618-4589-bcfc-5f3eecc912e4", 2.50))
//		.hasMessage("Houve um erro ao obter pacotes de SMS!").isInstanceOf(RuntimeException.class);
//    }
//
//}
