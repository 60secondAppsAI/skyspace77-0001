import http from "../http-common"; 

class InsuranceService {
  getAllInsurances(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/insurance/insurances`, searchDTO);
  }

  get(insuranceId) {
    return this.getRequest(`/insurance/${insuranceId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/insurance?field=${matchData}`, null);
  }

  addInsurance(data) {
    return http.post("/insurance/addInsurance", data);
  }

  update(data) {
  	return http.post("/insurance/updateInsurance", data);
  }
  
  uploadImage(data,insuranceId) {
  	return http.postForm("/insurance/uploadImage/"+insuranceId, data);
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

export default new InsuranceService();
