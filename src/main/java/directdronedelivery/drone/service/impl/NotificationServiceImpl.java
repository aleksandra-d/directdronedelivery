package directdronedelivery.drone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import directdrondelivery.entity.Cargo;
import directdrondelivery.entity.Drone;
import directdrondelivery.entity.Flight;
import directdrondelivery.entity.enumerators.DroneStatus;
import directdronedelivery.drone.service.MailService;
import directdronedelivery.drone.service.NotificationService;

/**
*
* @author Marcin Stachowiak, Capgemini(marcin.stachowiak@capgemini.com)
*
*/

@Service
@Scope("singleton")
public class NotificationServiceImpl implements NotificationService{
	private static final String CARGO_OUT_FOR_DELIVERY = "Shipment number: %d is ready to fly. Recipient: %s.";
	private static final String DRONE_IS_LANDING = "Drone %s with your Shipment is landing";
	private static final String TOPIC = "Shipment Status";

	private MailService mailService=null;

	@Autowired
	public NotificationServiceImpl(MailService mailService){
		this.mailService=mailService;
	}

	@Override
	public void nofifyAboutShipmentStatus(Flight flight) {
		String message = prepareRightMessage(flight);
		mailService.sendEmail(TOPIC, message, flight.getCargo().getRecipientEmail());
	}

	@Override
	public String prepareRightMessage(Flight flight) {
		Drone drone = flight.getDrone();
		Cargo cargo = flight.getCargo();
		String result=null;
		if(drone.getStatus()==DroneStatus.AIRBORNE){
			result=String.format(CARGO_OUT_FOR_DELIVERY, cargo.getId(),cargo.getRecipientName());
		}
		if(drone.getStatus()==DroneStatus.LANDING_AT_THE_DESTINATION){
			result=String.format(DRONE_IS_LANDING, cargo.getId(),cargo.getRecipientName());
		}
		return result;
	}


}
