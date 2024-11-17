<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <emergencyContact-table
            v-if="emergencyContacts && emergencyContacts.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:emergencyContacts="emergencyContacts"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-emergency-contacts="getAllEmergencyContacts"
             >

            </emergencyContact-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import EmergencyContactTable from "@/components/EmergencyContactTable";
import EmergencyContactService from "../services/EmergencyContactService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    EmergencyContactTable,
  },
  data() {
    return {
      emergencyContacts: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllEmergencyContacts(sortBy='emergencyContactId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await EmergencyContactService.getAllEmergencyContacts(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.emergencyContacts.length) {
					this.emergencyContacts = response.data.emergencyContacts;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching emergencyContacts:", error);
        }
        
      } catch (error) {
        console.error("Error fetching emergencyContact details:", error);
      }
    },
  },
  mounted() {
    this.getAllEmergencyContacts();
  },
  created() {
    this.$root.$on('searchQueryForEmergencyContactsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllEmergencyContacts();
    })
  }
};
</script>
<style></style>
