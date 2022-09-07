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

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		
		Country co1 = new Country(null, "Rio Grande do Sul");
		Country co2 = new Country(null, "Santa Catarina");
		
		
		City cy1 = new City(null, "Porto Alegre", co1);
		City cy2 = new City(null, "Canoas", co1);
		City cy3 = new City(null, "Florianópolis", co2);
		
		co1.getCitys().addAll(Arrays.asList(cy1, cy2));
		co2.getCitys().addAll(Arrays.asList(cy3));

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
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
		

	}

}
