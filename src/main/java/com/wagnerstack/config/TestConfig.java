package com.wagnerstack.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.wagnerstack.entities.Category;
import com.wagnerstack.entities.City;
import com.wagnerstack.entities.Country;
import com.wagnerstack.entities.Product;
import com.wagnerstack.repositories.CategoryRepository;
import com.wagnerstack.repositories.CityRepository;
import com.wagnerstack.repositories.CountryRepository;
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
	CityRepository cityRepository;

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
		countryRepository.saveAll(Arrays.asList(co1, co2));
		cityRepository.saveAll(Arrays.asList(cy1, cy2, cy3));

	}

}
