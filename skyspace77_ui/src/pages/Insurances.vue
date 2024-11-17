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
            <insurance-table
            v-if="insurances && insurances.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:insurances="insurances"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-insurances="getAllInsurances"
             >

            </insurance-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import InsuranceTable from "@/components/InsuranceTable";
import InsuranceService from "../services/InsuranceService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    InsuranceTable,
  },
  data() {
    return {
      insurances: [],
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
    async getAllInsurances(sortBy='insuranceId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await InsuranceService.getAllInsurances(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.insurances.length) {
					this.insurances = response.data.insurances;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching insurances:", error);
        }
        
      } catch (error) {
        console.error("Error fetching insurance details:", error);
      }
    },
  },
  mounted() {
    this.getAllInsurances();
  },
  created() {
    this.$root.$on('searchQueryForInsurancesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllInsurances();
    })
  }
};
</script>
<style></style>
