package com.wagnerstack.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.wagnerstack.entities.Address;
import com.wagnerstack.entities.Category;
import com.wagnerstack.entities.City;
import com.wagnerstack.entities.Client;
import com.wagnerstack.entities.Country;
import com.wagnerstack.entities.OrderItem;
import com.wagnerstack.entities.Payment;
import com.wagnerstack.entities.PaymentWithCard;
import com.wagnerstack.entities.Pedido;
import com.wagnerstack.entities.Product;
import com.wagnerstack.entities.TicketPayment;
import com.wagnerstack.entities.enums.ClientType;
import com.wagnerstack.entities.enums.StatePayment;
import com.wagnerstack.repositories.AddressRepository;
import com.wagnerstack.repositories.CategoryRepository;
import com.wagnerstack.repositories.CityRepository;
import com.wagnerstack.repositories.ClientRepository;
import com.wagnerstack.repositories.CountryRepository;
import com.wagnerstack.repositories.OrderItemRepository;
import com.wagnerstack.repositories.PaymentRepository;
import com.wagnerstack.repositories.PedidoRepository;
import com.wagnerstack.repositories.ProductRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Cama mesa e banho");
		Category cat4 = new Category(null, "Eletronicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Mesa de Escritório", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "TV true color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);
		
		
		
		
		Country co1 = new Country(null, "Rio Grande do Sul");
		Country co2 = new Country(null, "Santa Catarina");
		
		
		City cy1 = new City(null, "Porto Alegre", co1);
		City cy2 = new City(null, "Canoas", co1);
		City cy3 = new City(null, "Florianópolis", co2);
		
		co1.getCitys().addAll(Arrays.asList(cy1, cy2));
		co2.getCitys().addAll(Arrays.asList(cy3));

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));
		

		p1.getCategories().addAll(Arrays.asList(cat1, cat4) );
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));
		

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Client cli1 = new Client(null, "Wagner Geroldi", "wagnergeroldi@gmail.com", "02221973070", ClientType.PESSOAFISICA);
		
		cli1.getPhones().addAll(Arrays.asList("996366433","999775753"));
		
		Address ad1 = new Address(null, "Av Arthur Oscar", "Centro", "2210", "Apto 502", "99250000", cli1, cy1);
		Address ad2 = new Address(null, "Av Arthur Oscar", "Centro", "2210", "Apto 402", "99250000", cli1, cy2);
		
		cli1.getAdresses().addAll(Arrays.asList(ad1,ad2));
		
		
		
		countryRepository.saveAll(Arrays.asList(co1, co2));
		cityRepository.saveAll(Arrays.asList(cy1, cy2, cy3));
		
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(ad1, ad2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, ad1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 11:02"), cli1, ad2);
		
		Payment pay1 = new PaymentWithCard(null, StatePayment.PAID, ped1, 6);
		ped1.setPayment(pay1);	
		Payment pay2 = new TicketPayment(null, StatePayment.PENDING, ped2, sdf.parse("20/10/2017 10:00"), null);
		ped2.setPayment(pay2);
		 
		cli1.getOrders().addAll(Arrays.asList(ped1, ped2));
		
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
		
		
		OrderItem ip1 = new OrderItem(ped1, p1, 0.0, 1, 2000.00);
		OrderItem ip2 = new OrderItem(ped1, p3, 0.0, 2, 80.00);
		OrderItem ip3 = new OrderItem(ped2, p2, 100.0, 1, 800.00);
		
		ped1.getItems().addAll(Arrays.asList(ip1, ip2));
		ped2.getItems().addAll(Arrays.asList(ip3));
		
		
		p1.getItems().addAll(Arrays.asList(ip1));
		p2.getItems().addAll(Arrays.asList(ip3));
		p3.getItems().addAll(Arrays.asList(ip2));
		
		orderItemRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
