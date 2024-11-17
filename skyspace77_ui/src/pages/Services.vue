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
            <service-table
            v-if="services && services.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:services="services"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-services="getAllServices"
             >

            </service-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ServiceTable from "@/components/ServiceTable";
import ServiceService from "../services/ServiceService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ServiceTable,
  },
  data() {
    return {
      services: [],
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
    async getAllServices(sortBy='serviceId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ServiceService.getAllServices(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.services.length) {
					this.services = response.data.services;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching services:", error);
        }
        
      } catch (error) {
        console.error("Error fetching service details:", error);
      }
    },
  },
  mounted() {
    this.getAllServices();
  },
  created() {
    this.$root.$on('searchQueryForServicesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllServices();
    })
  }
};
</script>
<style></style>
