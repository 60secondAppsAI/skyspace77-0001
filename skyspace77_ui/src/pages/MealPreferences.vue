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
            <mealPreference-table
            v-if="mealPreferences && mealPreferences.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:mealPreferences="mealPreferences"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-meal-preferences="getAllMealPreferences"
             >

            </mealPreference-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import MealPreferenceTable from "@/components/MealPreferenceTable";
import MealPreferenceService from "../services/MealPreferenceService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    MealPreferenceTable,
  },
  data() {
    return {
      mealPreferences: [],
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
    async getAllMealPreferences(sortBy='mealPreferenceId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await MealPreferenceService.getAllMealPreferences(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.mealPreferences.length) {
					this.mealPreferences = response.data.mealPreferences;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching mealPreferences:", error);
        }
        
      } catch (error) {
        console.error("Error fetching mealPreference details:", error);
      }
    },
  },
  mounted() {
    this.getAllMealPreferences();
  },
  created() {
    this.$root.$on('searchQueryForMealPreferencesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllMealPreferences();
    })
  }
};
</script>
<style></style>
