package com.wagnerstack.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wagnerstack.entities.OrderItem;
import com.wagnerstack.entities.Pedido;
import com.wagnerstack.entities.TicketPayment;
import com.wagnerstack.entities.enums.StatePayment;
import com.wagnerstack.repositories.OrderItemRepository;
import com.wagnerstack.repositories.PaymentRepository;
import com.wagnerstack.repositories.PedidoRepository;

@Service
public class PedidoServices {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductServices productService;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> item = pedidoRepository.findById(id);
		return item.orElse(null);
	}
	
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();

	}
	
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstant(new Date());
		pedido.getPayment().setStatePaymentTest(StatePayment.PENDING);
		pedido.getPayment().setPedido(pedido);
		
		if(pedido.getPayment() instanceof TicketPayment ) {
			TicketPayment pagto = (TicketPayment) pedido.getPayment();
			ticketService.preencerPagamentoComBoleto(pagto, pedido.getInstant());
		}
		
		pedido = pedidoRepository.save(pedido);
		paymentRepository.save(pedido.getPayment());
		
		for(OrderItem oi : pedido.getItems()) {
			
			oi.setDiscount(0.0);
			oi.setPrice(productService.find(oi.getProduct().getId()).getPrice());
			oi.setPedido(pedido);
		}
		
		orderItemRepository.saveAll(pedido.getItems());
		
		return pedido;	
		
	}

}
