import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Flights from  '@/pages/Flights.vue';
import FlightDetail from  '@/pages/FlightDetail.vue';
import Passengers from  '@/pages/Passengers.vue';
import PassengerDetail from  '@/pages/PassengerDetail.vue';
import Bookings from  '@/pages/Bookings.vue';
import BookingDetail from  '@/pages/BookingDetail.vue';
import Tickets from  '@/pages/Tickets.vue';
import TicketDetail from  '@/pages/TicketDetail.vue';
import Payments from  '@/pages/Payments.vue';
import PaymentDetail from  '@/pages/PaymentDetail.vue';
import Baggages from  '@/pages/Baggages.vue';
import BaggageDetail from  '@/pages/BaggageDetail.vue';
import Airlines from  '@/pages/Airlines.vue';
import AirlineDetail from  '@/pages/AirlineDetail.vue';
import Airports from  '@/pages/Airports.vue';
import AirportDetail from  '@/pages/AirportDetail.vue';
import CrewMembers from  '@/pages/CrewMembers.vue';
import CrewMemberDetail from  '@/pages/CrewMemberDetail.vue';
import FlightSchedules from  '@/pages/FlightSchedules.vue';
import FlightScheduleDetail from  '@/pages/FlightScheduleDetail.vue';
import Seats from  '@/pages/Seats.vue';
import SeatDetail from  '@/pages/SeatDetail.vue';
import Services from  '@/pages/Services.vue';
import ServiceDetail from  '@/pages/ServiceDetail.vue';
import Itinerarys from  '@/pages/Itinerarys.vue';
import ItineraryDetail from  '@/pages/ItineraryDetail.vue';
import LoyaltyPrograms from  '@/pages/LoyaltyPrograms.vue';
import LoyaltyProgramDetail from  '@/pages/LoyaltyProgramDetail.vue';
import BoardingPasss from  '@/pages/BoardingPasss.vue';
import BoardingPassDetail from  '@/pages/BoardingPassDetail.vue';
import EmergencyContacts from  '@/pages/EmergencyContacts.vue';
import EmergencyContactDetail from  '@/pages/EmergencyContactDetail.vue';
import FlightCrews from  '@/pages/FlightCrews.vue';
import FlightCrewDetail from  '@/pages/FlightCrewDetail.vue';
import Visas from  '@/pages/Visas.vue';
import VisaDetail from  '@/pages/VisaDetail.vue';
import MealPreferences from  '@/pages/MealPreferences.vue';
import MealPreferenceDetail from  '@/pages/MealPreferenceDetail.vue';
import Insurances from  '@/pages/Insurances.vue';
import InsuranceDetail from  '@/pages/InsuranceDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/flights',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/flights',
		name: 'Flights',
		layout: DefaultLayout,
		component: Flights,
	},
	{
	    path: '/flight/:flightId', 
	    name: 'FlightDetail',
		layout: DefaultLayout,
	    component: FlightDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/passengers',
		name: 'Passengers',
		layout: DefaultLayout,
		component: Passengers,
	},
	{
	    path: '/passenger/:passengerId', 
	    name: 'PassengerDetail',
		layout: DefaultLayout,
	    component: PassengerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bookings',
		name: 'Bookings',
		layout: DefaultLayout,
		component: Bookings,
	},
	{
	    path: '/booking/:bookingId', 
	    name: 'BookingDetail',
		layout: DefaultLayout,
	    component: BookingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/tickets',
		name: 'Tickets',
		layout: DefaultLayout,
		component: Tickets,
	},
	{
	    path: '/ticket/:ticketId', 
	    name: 'TicketDetail',
		layout: DefaultLayout,
	    component: TicketDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/payments',
		name: 'Payments',
		layout: DefaultLayout,
		component: Payments,
	},
	{
	    path: '/payment/:paymentId', 
	    name: 'PaymentDetail',
		layout: DefaultLayout,
	    component: PaymentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/baggages',
		name: 'Baggages',
		layout: DefaultLayout,
		component: Baggages,
	},
	{
	    path: '/baggage/:baggageId', 
	    name: 'BaggageDetail',
		layout: DefaultLayout,
	    component: BaggageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airlines',
		name: 'Airlines',
		layout: DefaultLayout,
		component: Airlines,
	},
	{
	    path: '/airline/:airlineId', 
	    name: 'AirlineDetail',
		layout: DefaultLayout,
	    component: AirlineDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airports',
		name: 'Airports',
		layout: DefaultLayout,
		component: Airports,
	},
	{
	    path: '/airport/:airportId', 
	    name: 'AirportDetail',
		layout: DefaultLayout,
	    component: AirportDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/crewMembers',
		name: 'CrewMembers',
		layout: DefaultLayout,
		component: CrewMembers,
	},
	{
	    path: '/crewMember/:crewMemberId', 
	    name: 'CrewMemberDetail',
		layout: DefaultLayout,
	    component: CrewMemberDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/flightSchedules',
		name: 'FlightSchedules',
		layout: DefaultLayout,
		component: FlightSchedules,
	},
	{
	    path: '/flightSchedule/:flightScheduleId', 
	    name: 'FlightScheduleDetail',
		layout: DefaultLayout,
	    component: FlightScheduleDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/seats',
		name: 'Seats',
		layout: DefaultLayout,
		component: Seats,
	},
	{
	    path: '/seat/:seatId', 
	    name: 'SeatDetail',
		layout: DefaultLayout,
	    component: SeatDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/services',
		name: 'Services',
		layout: DefaultLayout,
		component: Services,
	},
	{
	    path: '/service/:serviceId', 
	    name: 'ServiceDetail',
		layout: DefaultLayout,
	    component: ServiceDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/itinerarys',
		name: 'Itinerarys',
		layout: DefaultLayout,
		component: Itinerarys,
	},
	{
	    path: '/itinerary/:itineraryId', 
	    name: 'ItineraryDetail',
		layout: DefaultLayout,
	    component: ItineraryDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/loyaltyPrograms',
		name: 'LoyaltyPrograms',
		layout: DefaultLayout,
		component: LoyaltyPrograms,
	},
	{
	    path: '/loyaltyProgram/:loyaltyProgramId', 
	    name: 'LoyaltyProgramDetail',
		layout: DefaultLayout,
	    component: LoyaltyProgramDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/boardingPasss',
		name: 'BoardingPasss',
		layout: DefaultLayout,
		component: BoardingPasss,
	},
	{
	    path: '/boardingPass/:boardingPassId', 
	    name: 'BoardingPassDetail',
		layout: DefaultLayout,
	    component: BoardingPassDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/emergencyContacts',
		name: 'EmergencyContacts',
		layout: DefaultLayout,
		component: EmergencyContacts,
	},
	{
	    path: '/emergencyContact/:emergencyContactId', 
	    name: 'EmergencyContactDetail',
		layout: DefaultLayout,
	    component: EmergencyContactDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/flightCrews',
		name: 'FlightCrews',
		layout: DefaultLayout,
		component: FlightCrews,
	},
	{
	    path: '/flightCrew/:flightCrewId', 
	    name: 'FlightCrewDetail',
		layout: DefaultLayout,
	    component: FlightCrewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/visas',
		name: 'Visas',
		layout: DefaultLayout,
		component: Visas,
	},
	{
	    path: '/visa/:visaId', 
	    name: 'VisaDetail',
		layout: DefaultLayout,
	    component: VisaDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/mealPreferences',
		name: 'MealPreferences',
		layout: DefaultLayout,
		component: MealPreferences,
	},
	{
	    path: '/mealPreference/:mealPreferenceId', 
	    name: 'MealPreferenceDetail',
		layout: DefaultLayout,
	    component: MealPreferenceDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/insurances',
		name: 'Insurances',
		layout: DefaultLayout,
		component: Insurances,
	},
	{
	    path: '/insurance/:insuranceId', 
	    name: 'InsuranceDetail',
		layout: DefaultLayout,
	    component: InsuranceDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
