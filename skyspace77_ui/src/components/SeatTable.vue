
<template>
  <div class="content">
    <!-- search bar -->
    <div class="row my-3 justify-content-end">
      <div class="col-8"></div>
       <div class="col-auto">
        <!-- Header Search Input -->
        <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Search by BusinessUnit#, Location#, Operator#, City, State, FirstName, LastName…"
          @search="onSearch" :loading='searchLoading' v-model="searchQuery">
          <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z"
              fill="#111827" />
          </svg>
        </a-input-search>
        <!-- / Header Search Input -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <card>
          <template slot="header">
            <div class="row justify-content-between align-items-between mx-3">

              <h5 class="card-title">Seats</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalSeats = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalSeats">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add Seat</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="SeatId" type="text" placeholder="Enter SeatId" v-model="seatToAdd.seatId"></base-input>
  <base-input label="SeatNumber" type="text" placeholder="Enter SeatNumber" v-model="seatToAdd.seatNumber"></base-input>
  <base-input label="ClassType" type="text" placeholder="Enter ClassType" v-model="seatToAdd.classType"></base-input>
  <base-input label="Availability" type="text" placeholder="Enter Availability" v-model="seatToAdd.availability"></base-input>
                  </form>
                </div>
                <template slot="footer">
                  <base-button type="primary" @click="handleAddSubmitted()">Save</base-button>
                </template>
              </modal>
            </div>
          </template>


          <!-- Conditionally render the view based on the 'isTableView' flag -->
          <div v-if="isTableView">
            <!-- Render the existing Table View -->
            <a-table :columns="columns" :data-source="seats" :row-key="record => record.SeatId" :pagination="pagination"
              :loading="searchLoading" @change="onTableChange" :scroll="{ x: 'max-content' }">



             <template slot="lastModified" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="createdOn" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="blackOutStartDate" slot-scope="dataIndex">
              	{{ formatDate(dataIndex) }}
              </template>
            </a-table>
          </div>
          <div v-else>
            <!-- Render the Picture View  -->
            <SeatPictureView :seats="seats" />
          </div>

        </card>
      </div>
    </div>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import BaseButton from "@/components/BaseButton";
import BaseInput from "@/components/BaseInput";
import NotificationTemplate from "@/pages/Notifications/NotificationTemplate";
import { Card } from "@/components/Card";
import SeatService from "../services/SeatService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import SeatPictureView from './SeatPictureView.vue';


const seatsColumns = [
  "seatId",
  "year",
  "date",
  "competitionId",
  "seatId"
]

export default {
  props: {
    seats: {
      type: Array,
      required: true,
    },
    totalElements: {
      type: Number,
      required: true,
    },
    page: {
      type: Number,
      required: true,
    },
  },
  components: {
    Card,
    Modal,
    BaseButton,
    BaseInput,
    Table,

    Pagination,
    SeatPictureView
  },

  data() {
    return {
      modalSeats: false,
        isTableView: true,

      columns: [
        {
          title: 'Seat Id',
		dataIndex: 'seatId',
          visible: true,
          scopedSlots: { customRender: 'seatId' },
          sorter: true
          //sorter: (a, b) => a.seatId - b.seatId,
          //sorter: (a, b) => a.seatId.localeCompare(b.seatId),
        },
        {
          title: 'Seat Number',
		dataIndex: 'seatNumber',
          visible: true,
          scopedSlots: { customRender: 'seatNumber' },
          sorter: true
          //sorter: (a, b) => a.seatNumber - b.seatNumber,
          //sorter: (a, b) => a.seatNumber.localeCompare(b.seatNumber),
        },
        {
          title: 'Class Type',
		dataIndex: 'classType',
          visible: true,
          scopedSlots: { customRender: 'classType' },
          sorter: true
          //sorter: (a, b) => a.classType - b.classType,
          //sorter: (a, b) => a.classType.localeCompare(b.classType),
        },
        {
          title: 'Availability',
		dataIndex: 'availability',
          visible: true,
          scopedSlots: { customRender: 'availability' },
          sorter: true
          //sorter: (a, b) => a.availability - b.availability,
          //sorter: (a, b) => a.availability.localeCompare(b.availability),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} seats`,
      },

      seats: [],
      seatToAdd: {},

      seatsTable: {
        columns: [...seatsColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'seatId',           // Column to sort by
      sortOrder: 'asc',     // Sort order (asc or desc)
      searchQuery: '',      // Search query
      searchLoading: false, // Initialize searchLoading property


    };
  },
  watch: {
    searchQuery: {
      handler: "handleSearchQueryChanged", // Call the fetchData method when searchQuery changes
      immediate: true, // Trigger immediately when the component is mounted
    },
  },

  methods: {

    async renderSeatsTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let seatsTableData = [];
      for (let i = 0; i < this.seats.length; i++) {
        seatsTableData.push({
          id: i,
          seatId: this.seats[i].seatId,
          year: this.seats[i].year,
          date: this.seats[i].date,
          competitionId: this.seats[i].competitionId,
          seatId: this.seats[i].seatId,
        });

      }
      this.searchLoading = false;
    },

    async onTableChange(pagination, filters, sorter) {
      if (sorter && sorter.field && sorter.order) {
        this.sortBy = sorter.field;
        if (sorter.order == "ascend") {
            this.sortOrder = "asc";
        } else {
            this.sortOrder = "desc";
        }
      }
      if (pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
      }

	  this.$emit('get-all-seats',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
      this.handleTableChanged()
    },
	
	initializeColumns() {
        this.columns = this.columns.filter(item => item.visible);
    },

    routingToFlightDetail(id) {
      this.$router.push({ name: 'FlightDetail', params: { flightId: id.toString() }})
    },
    routingToPassengerDetail(id) {
      this.$router.push({ name: 'PassengerDetail', params: { passengerId: id.toString() }})
    },
    routingToBookingDetail(id) {
      this.$router.push({ name: 'BookingDetail', params: { bookingId: id.toString() }})
    },
    routingToTicketDetail(id) {
      this.$router.push({ name: 'TicketDetail', params: { ticketId: id.toString() }})
    },
    routingToPaymentDetail(id) {
      this.$router.push({ name: 'PaymentDetail', params: { paymentId: id.toString() }})
    },
    routingToBaggageDetail(id) {
      this.$router.push({ name: 'BaggageDetail', params: { baggageId: id.toString() }})
    },
    routingToAirlineDetail(id) {
      this.$router.push({ name: 'AirlineDetail', params: { airlineId: id.toString() }})
    },
    routingToAirportDetail(id) {
      this.$router.push({ name: 'AirportDetail', params: { airportId: id.toString() }})
    },
    routingToCrewMemberDetail(id) {
      this.$router.push({ name: 'CrewMemberDetail', params: { crewMemberId: id.toString() }})
    },
    routingToFlightScheduleDetail(id) {
      this.$router.push({ name: 'FlightScheduleDetail', params: { flightScheduleId: id.toString() }})
    },
    routingToSeatDetail(id) {
      this.$router.push({ name: 'SeatDetail', params: { seatId: id.toString() }})
    },
    routingToServiceDetail(id) {
      this.$router.push({ name: 'ServiceDetail', params: { serviceId: id.toString() }})
    },
    routingToItineraryDetail(id) {
      this.$router.push({ name: 'ItineraryDetail', params: { itineraryId: id.toString() }})
    },
    routingToLoyaltyProgramDetail(id) {
      this.$router.push({ name: 'LoyaltyProgramDetail', params: { loyaltyProgramId: id.toString() }})
    },
    routingToBoardingPassDetail(id) {
      this.$router.push({ name: 'BoardingPassDetail', params: { boardingPassId: id.toString() }})
    },
    routingToEmergencyContactDetail(id) {
      this.$router.push({ name: 'EmergencyContactDetail', params: { emergencyContactId: id.toString() }})
    },
    routingToFlightCrewDetail(id) {
      this.$router.push({ name: 'FlightCrewDetail', params: { flightCrewId: id.toString() }})
    },
    routingToVisaDetail(id) {
      this.$router.push({ name: 'VisaDetail', params: { visaId: id.toString() }})
    },
    routingToMealPreferenceDetail(id) {
      this.$router.push({ name: 'MealPreferenceDetail', params: { mealPreferenceId: id.toString() }})
    },
    routingToInsuranceDetail(id) {
      this.$router.push({ name: 'InsuranceDetail', params: { insuranceId: id.toString() }})
    },
    
    handleSearchQueryChanged() {
    	console.log("handleSearchQueryChanged CALLED FROM @search")
    	this.$root.$emit('searchQueryForSeatsChanged', this.searchQuery);
		//this.renderSeatsTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalSeats = false;

      const currentDate = new Date().getTime();
      this.seatToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.seatToAdd);
      console.log(jsonData);
      
      const res = await SeatService.addSeat(jsonData);

      if (res.status === 200) {
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          type: "success",
          timeout: 3000,
        });
      }
	},
	
	handleTableChanged() {
	},
	
	formatTime(dateString) {
      if(dateString !== null){
        const date = new Date(dateString);
      return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}-${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} EST`;
      }
      // Format the date here as needed, for example:
    },  
    
 formatDate(dateString) {
    if (dateString !== null) {
	    const date = new Date(dateString);
	    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	    const day = String(date.getDate()).padStart(2, '0');
	    const monthAbbreviation = months[date.getMonth()];
	    const year = date.getFullYear();
	    return `${day} ${monthAbbreviation} ${year}`;
  	}
  	// Handle the case when dateString is null or invalid
  	return '';
    
   },

  },
  mounted() {
  	this.initializeColumns();
    this.renderSeatsTable();
  }
};
</script>

<style>
.modal-dialog {
  margin-top: -300px;
}
.ant-table-row  {
	text-align: center;
}
.ant-table-thead th span {
	text-align: center;
	width: 100%
}
.ant-table-fixed td span {
    text-align: center;
}
.header-search {
  width: 300px !important;
  margin-left: auto !important;
}
</style>
