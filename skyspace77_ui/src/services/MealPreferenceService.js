import http from "../http-common"; 

class MealPreferenceService {
  getAllMealPreferences(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/mealPreference/mealPreferences`, searchDTO);
  }

  get(mealPreferenceId) {
    return this.getRequest(`/mealPreference/${mealPreferenceId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/mealPreference?field=${matchData}`, null);
  }

  addMealPreference(data) {
    return http.post("/mealPreference/addMealPreference", data);
  }

  update(data) {
  	return http.post("/mealPreference/updateMealPreference", data);
  }
  
  uploadImage(data,mealPreferenceId) {
  	return http.postForm("/mealPreference/uploadImage/"+mealPreferenceId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new MealPreferenceService();
