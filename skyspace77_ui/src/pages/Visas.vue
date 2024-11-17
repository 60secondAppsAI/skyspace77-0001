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
            <visa-table
            v-if="visas && visas.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:visas="visas"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-visas="getAllVisas"
             >

            </visa-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import VisaTable from "@/components/VisaTable";
import VisaService from "../services/VisaService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    VisaTable,
  },
  data() {
    return {
      visas: [],
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
    async getAllVisas(sortBy='visaId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await VisaService.getAllVisas(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.visas.length) {
					this.visas = response.data.visas;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching visas:", error);
        }
        
      } catch (error) {
        console.error("Error fetching visa details:", error);
      }
    },
  },
  mounted() {
    this.getAllVisas();
  },
  created() {
    this.$root.$on('searchQueryForVisasChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllVisas();
    })
  }
};
</script>
<style></style>
