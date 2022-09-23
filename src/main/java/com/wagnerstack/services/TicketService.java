package com.wagnerstack.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.wagnerstack.entities.TicketPayment;

@Service
public class TicketService {

	public void preencerPagamentoComBoleto(TicketPayment pagto, Date instantOrder) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(instantOrder);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setPaymentDate(cal.getTime());

	}

}
